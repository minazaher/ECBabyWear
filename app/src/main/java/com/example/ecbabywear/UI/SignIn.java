package com.example.ecbabywear.UI;

import static com.example.ecbabywear.ApplicationClass.firebaseAuth;
import static com.example.ecbabywear.ApplicationClass.navigateToActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecbabywear.databinding.ActivitySignInBinding;

public class SignIn extends AppCompatActivity {
    String CurrentEmail, CurrentPassword;
    ActivitySignInBinding SignInBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SignInBinding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(SignInBinding.getRoot());

        SignInBinding.tvForgotPassword.setOnClickListener(view -> {
            getCurrentEmail();
            if(!TextUtils.isEmpty(CurrentEmail))
                firebaseAuth.sendPasswordResetEmail(CurrentEmail);
        });

        SignInBinding.tvRegisterHere.setOnClickListener(view -> {
            navigateToActivity(this, SignUp.class);
            finish();
        });

        SignInBinding.btnSignIn.setOnClickListener(view -> {
            getDataFromEditTexts();
            if (isDataValid())
                signIn();
            else
                Toast.makeText(this, "Credentials are Not Valid!", Toast.LENGTH_SHORT).show();
        });
    }

    private void signIn(){
        firebaseAuth.signInWithEmailAndPassword(CurrentEmail, CurrentPassword).
                addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        navigateToActivity(this, HomePage.class);
                        finish();
                    }
                    else
                        Toast.makeText(SignIn.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                });
    }

    private void getDataFromEditTexts(){
        getCurrentEmail();
        getCurrentPassword();
    }
    private void getCurrentEmail(){
        CurrentEmail  = SignInBinding.etEmailSignin.getText().toString().trim();
    }
    private void getCurrentPassword(){
        CurrentPassword = SignInBinding.etPasswordSignin.getText().toString().trim();
    }
    private boolean isDataValid(){
        return validateTextField(CurrentEmail, SignInBinding.etEmailSignin) &&
                validateTextField(CurrentPassword, SignInBinding.etPasswordSignin);
    }
    private boolean validateTextField(String text, EditText editText){
        if (TextUtils.isEmpty(text)){
            editText.setError("Please Enter a Valid Value");
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        navigateToActivity(SignIn.this, SignUp.class);
    }

}