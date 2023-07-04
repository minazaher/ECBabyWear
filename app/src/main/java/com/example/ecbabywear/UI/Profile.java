package com.example.ecbabywear.UI;

import static com.example.ecbabywear.ApplicationClass.firebaseAuth;
import static com.example.ecbabywear.ApplicationClass.navigateToActivity;

import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.ecbabywear.R;
import com.example.ecbabywear.Repositories.UserRepository;
import com.example.ecbabywear.UI.OrderHistory.OrderHistory;
import com.example.ecbabywear.databinding.ActivityProfileBinding;

public class Profile extends AppCompatActivity {
    ActivityProfileBinding activityProfileBinding ;
    UserRepository userRepository;
    ImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityProfileBinding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(activityProfileBinding.getRoot());

        profile = findViewById(R.id.profile_picture_inner);
        userRepository = new UserRepository();

        initializeUserData();

        activityProfileBinding.backIcon.setOnClickListener(view ->navigateToActivity(Profile.this, HomePage.class));

        activityProfileBinding.btnChangePassword.setOnClickListener(view -> verifyPasswordReset());

        activityProfileBinding.btnChangeName.setOnClickListener(view -> showNameDialog());

        activityProfileBinding.btnChangeAddress.setOnClickListener(view -> showAddressDialog());

        activityProfileBinding.btnDeleteAccount.setOnClickListener(view -> showDeleteAccountMessage());

        activityProfileBinding.ordersButton.setOnClickListener(view -> navigateToActivity(Profile.this, OrderHistory.class));

        activityProfileBinding.wishlistButton.setOnClickListener(view -> navigateToActivity(Profile.this, WishlistActivity.class));

    }

    private void showAddressDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter new Address");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("OK", (dialog, which) -> {
            String Address = input.getText().toString();
            userRepository.updateAddress(firebaseAuth.getCurrentUser().getUid(), Address);
            activityProfileBinding.tvChangeAddress.setText(Address);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void initializeUserData(){
        userRepository.getCurrentUser(firebaseAuth.getCurrentUser().getEmail(), user -> {
            activityProfileBinding.tvUserName.setText(user.getName());
            activityProfileBinding.tvChangeName.setText((user.getName()));
            activityProfileBinding.tvUserEmail.setText(user.getEmail());
            activityProfileBinding.tvChangeAddress.setText(user.getAddress());
            Glide.with(getApplicationContext()).asBitmap().load(user.getProfilePicture()).into(profile);
        });

    }
    private void verifyPasswordReset(){
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure you want to change your password?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, (arg0, arg1) -> {
                    firebaseAuth.sendPasswordResetEmail(firebaseAuth.getCurrentUser().getEmail());
                    showPasswordResetNotification();
                }).create().show();
    }
    private void showPasswordResetNotification(){
        new AlertDialog.Builder(this)
                .setMessage("check Your email")
                .setPositiveButton(android.R.string.ok, null).create().show();
    }

    private void showDeleteAccountMessage(){
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to Delete this Account?")
                .setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
                    firebaseAuth.signOut();
                    firebaseAuth.getCurrentUser().delete();
                    navigateToActivity(this, SplashScreen.class);
                }).setNegativeButton(android.R.string.no, (dialog, which) -> dialog.cancel() ).create().show();
    }

    private void showNameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter new name");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("OK", (dialog, which) -> {
            String Name = input.getText().toString();
            userRepository.updateUsername(firebaseAuth.getCurrentUser().getUid(), Name);
            activityProfileBinding.tvUserName.setText(Name);
            activityProfileBinding.tvChangeName.setText(Name);
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }

}