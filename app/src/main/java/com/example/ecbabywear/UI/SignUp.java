package com.example.ecbabywear.UI;

import static com.example.ecbabywear.ApplicationClass.firebaseAuth;
import static com.example.ecbabywear.ApplicationClass.firebaseFirestore;
import static com.example.ecbabywear.ApplicationClass.navigateToActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecbabywear.Model.User;
import com.example.ecbabywear.Repositories.WishlistRepository;
import com.example.ecbabywear.databinding.ActivitySignUpBinding;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Objects;

public class SignUp extends AppCompatActivity {
    String CurrentName, CurrentPassword, CurrentEmail, Confirmation;
    ActivitySignUpBinding activitySignUpBinding;
    WishlistRepository wishlistRepository ;
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
            User currentUser = new User(CurrentName, CurrentEmail, CurrentPassword);
            if (ValidateAllData())
                CreateUser(currentUser);
            else
                Toast.makeText(this, "Please, Check Your Input", Toast.LENGTH_SHORT).show();
        });

    }

    private void GetDataFromEditTexts(){
        CurrentName = activitySignUpBinding.etNameSignup.getText().toString();
        CurrentEmail = activitySignUpBinding.etEmailSignup.getText().toString();
        CurrentPassword = activitySignUpBinding.etPasswordSignup.getText().toString();
        Confirmation = activitySignUpBinding.etPasswordSignupC.getText().toString();

    }

    private HashMap<String, Object> UserToMap(User user){
        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("Email", user.getEmail());
        userMap.put("Password", user.getPassword());
        userMap.put("Name", user.getName());
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
}