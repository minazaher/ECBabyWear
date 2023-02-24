package com.example.ecbabywear.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.example.ecbabywear.PiecesRepository;
import com.example.ecbabywear.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    Button SignUp;
    EditText Name, Email, Password, ConfirmPassword;
    FirebaseAuth mAuth;
    boolean valid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        SignUp = findViewById(R.id.btn_signUp);
        Name = findViewById(R.id.et_name_signup);
        Email = findViewById(R.id.et_email_signup);
        Password = findViewById(R.id.et_password_signup);
        ConfirmPassword = findViewById(R.id.et_password_signup_c);
        mAuth = FirebaseAuth.getInstance();
        valid = false;


        if (mAuth.getCurrentUser() != null){
            Intent i = new Intent();
            i.setClass(SignUp.this, SignIn.class);
            startActivity(i);
            finish();
        }

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String CurrentEmail = Email.getText().toString();
                String CurrentPassword = Password.getText().toString();
                String Confirmation = ConfirmPassword.getText().toString();
                String CurrentName = Name.getText().toString();

                if (TextUtils.isEmpty(CurrentName)){
                    Name.setError("Please Enter a Name");
                    return;
                }

                if(TextUtils.isEmpty(CurrentEmail)){
                    Email.setError("Please Enter a valid Email");
                    return;
                }

                if(TextUtils.isEmpty(CurrentPassword) || CurrentPassword.length() < 6){
                    Password.setError("Please Enter a valid Password");
                    return;
                }

                if (!CurrentPassword.equals(Confirmation)){
                    Toast.makeText(SignUp.this, "Passwords are not equal", Toast.LENGTH_SHORT).show();
                    return;
                }


                mAuth.createUserWithEmailAndPassword(CurrentEmail, CurrentPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(SignUp.this, "User Created!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent();
                            i.setClass(SignUp.this, SignIn.class);
                            startActivity(i);
                            finish();
                        }
                        else
                            Toast.makeText(SignUp.this, "Failed! Try Again Later", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });


//        PiecesRepository piecesRepository = new PiecesRepository();
//        piecesRepository.getPieceMutableLiveData(this);
    }
}