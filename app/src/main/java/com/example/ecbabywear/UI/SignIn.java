package com.example.ecbabywear.UI;

import static com.example.ecbabywear.ApplicationClass.firebaseAuth;
import static com.example.ecbabywear.ApplicationClass.navigateToActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecbabywear.R;
import com.example.ecbabywear.UI.HomePage.HomePage;
import com.example.ecbabywear.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {
    String CurrentEmail, CurrentPassword;
    ActivitySignInBinding SignInBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SignInBinding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(SignInBinding.getRoot());



        SignInBinding.tvForgotPassword.setOnClickListener(view -> {
            CurrentEmail  = SignInBinding.etEmailSignin.getText().toString().trim();
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
        CurrentEmail  = SignInBinding.etEmailSignin.getText().toString().trim();
        CurrentPassword = SignInBinding.etPasswordSignin.getText().toString().trim();
    }
    private boolean isDataValid(){
        if (validateTextField(CurrentEmail, SignInBinding.etEmailSignin) && validateTextField(CurrentPassword, SignInBinding.etPasswordSignin))
            return true;
        return false;
    }
    private boolean validateTextField(String text, EditText editText){
        if (TextUtils.isEmpty(text)){
            editText.setError("Please Enter a Valid Value");
            return false;
        }
        return true;
    }

}