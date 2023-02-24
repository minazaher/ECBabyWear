package com.example.ecbabywear.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecbabywear.R;
import com.example.ecbabywear.UI.HomePage.HomePage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {
    Button SignIn;
    EditText Email, Password;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        SignIn = findViewById(R.id.btn_signIn);
        Email = findViewById(R.id.et_email_signin);
        Password = findViewById(R.id.et_password_signin);
        mAuth = FirebaseAuth.getInstance();


        SignIn.setOnClickListener(view -> {
            String CurrentEmail = Email.getText().toString();
            String CurrentPassword = Password.getText().toString();

            if(TextUtils.isEmpty(CurrentEmail)){
                Email.setError("Please Enter a valid Email");
                return;
            }

            if(TextUtils.isEmpty(CurrentPassword) || CurrentPassword.length() < 6){
                Password.setError("Please Enter a valid Password");
                return;
            }


            mAuth.signInWithEmailAndPassword(CurrentEmail, CurrentPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Intent i = new Intent();
                        i.setClass(SignIn.this, HomePage.class);
                        startActivity(i);
                    }
                    else
                        Toast.makeText(SignIn.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            });

        });

    }
}