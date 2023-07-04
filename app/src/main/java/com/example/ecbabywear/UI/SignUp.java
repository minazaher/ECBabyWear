package com.example.ecbabywear.UI;

import static com.example.ecbabywear.ApplicationClass.firebaseAuth;
import static com.example.ecbabywear.ApplicationClass.firebaseFirestore;
import static com.example.ecbabywear.ApplicationClass.navigateToActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecbabywear.Model.User;
import com.example.ecbabywear.Repositories.WishlistRepository;
import com.example.ecbabywear.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

import javax.annotation.Nullable;

public class SignUp extends AppCompatActivity {
    String CurrentName, CurrentPassword, CurrentEmail, Confirmation,profilePicture, Address;
    ActivitySignUpBinding activitySignUpBinding;
    WishlistRepository wishlistRepository ;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySignUpBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(activitySignUpBinding.getRoot());


        if (firebaseAuth.getCurrentUser() != null){
            navigateToActivity(SignUp.this, SignIn.class);
        }
        activitySignUpBinding.tvSignInHere.setOnClickListener(view -> {
            navigateToActivity(SignUp.this, SignIn.class);
        });

        activitySignUpBinding.btnSignUp.setOnClickListener(view -> {
            GetDataFromEditTexts();
            User currentUser = new User(CurrentName, CurrentEmail, CurrentPassword, profilePicture,Address);
            if (ValidateAllData())
                CreateUser(currentUser);
            else
                Toast.makeText(this, "Please, Check Your Input", Toast.LENGTH_SHORT).show();
        });

        activitySignUpBinding.profilePicture.setOnClickListener(view -> {
            Intent profilePicture = new Intent(Intent.ACTION_PICK);
            profilePicture.setType("image/*");
            startActivityForResult(profilePicture, 1);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data !=null){
            imageUri = data.getData();
            try {
                getImageInImageView();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void getImageInImageView() throws IOException {
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
        activitySignUpBinding.profilePicture.setImageBitmap(bitmap);
        uploadImage();
    }

    private void GetDataFromEditTexts(){
        CurrentName = activitySignUpBinding.etNameSignup.getText().toString();
        CurrentEmail = activitySignUpBinding.etEmailSignup.getText().toString();
        CurrentPassword = activitySignUpBinding.etPasswordSignup.getText().toString();
        Confirmation = activitySignUpBinding.etPasswordSignupC.getText().toString();
        Address = activitySignUpBinding.etAddress.getText().toString();

    }

    private HashMap<String, Object> UserToMap(User user){
        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("Email", user.getEmail());
        userMap.put("Password", user.getPassword());
        userMap.put("Name", user.getName());
        userMap.put("Address", user.getAddress());
        userMap.put("profilePicture", user.getProfilePicture());
        return userMap;
    }

    private void CreateUser(User currentUser){
        firebaseAuth.createUserWithEmailAndPassword(CurrentEmail, CurrentPassword).
                addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                    String UserID = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
                    DocumentReference documentReference = firebaseFirestore.collection("Users").document(UserID);
                    HashMap<String, Object> user = UserToMap(currentUser);
                    wishlistRepository = new WishlistRepository(UserID);
                    wishlistRepository.CreateWishlist(UserID);
                    firebaseAuth.getCurrentUser().sendEmailVerification();
                    documentReference.set(user).addOnSuccessListener(unused -> {
                    navigateToActivity(SignUp.this, SignIn.class);
                });
            }else
                Toast.makeText(SignUp.this, "Error Creating User!", Toast.LENGTH_SHORT).show();
        });

    }

    private boolean ValidateAllData(){
        if (!ValidateTextField(CurrentName,activitySignUpBinding.etNameSignup)
                && !ValidateTextField(CurrentName,activitySignUpBinding.etPasswordSignup)
                && !ValidateTextField(CurrentEmail,activitySignUpBinding.etEmailSignup)) {
            return false;
        }

        if(CurrentPassword.length() < 6){
            activitySignUpBinding.etPasswordSignupC.setError("Please Enter a valid Password");
            return false;
        }

        if (!CurrentPassword.equals(Confirmation)){
            Toast.makeText(SignUp.this, "Passwords are not equal", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean ValidateTextField(String text, EditText editText){
        if (TextUtils.isEmpty(text)){
            editText.setError("Please Enter a Valid Value");
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        showExitMessage();
    }

    private void showExitMessage(){
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, (arg0, arg1) -> {
                    finishAffinity();
                }).create().show();
    }

    private void uploadImage(){
        FirebaseStorage.getInstance().getReference().child("/images" + UUID.randomUUID().toString()).putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()){
                    task.getResult().getStorage().getDownloadUrl().addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()){
                                profilePicture = task1.getResult().toString();
                            }
                    });
                }
                else {

                }

            }
        }).addOnProgressListener(snapshot -> {
            double progress = 100.0 * snapshot.getBytesTransferred()/ snapshot.getTotalByteCount();
            ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
            progressDialog.setProgress((int) progress);
            progressDialog.setMessage("progress" + progress);
        });

    }
}