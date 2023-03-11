package com.example.ecbabywear.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.ecbabywear.PiecesRepository;
import com.example.ecbabywear.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class SignUp extends AppCompatActivity {
    Button SignUp;
    TextView SignIn;
    EditText Name, Email, Password, ConfirmPassword;
    FirebaseAuth mAuth;
    FirebaseFirestore mStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        SignUp = findViewById(R.id.btn_signUp);
        Name = findViewById(R.id.et_name_signup);
        Email = findViewById(R.id.et_email_signup);
        Password = findViewById(R.id.et_password_signup);
        SignIn = findViewById(R.id.tv_signInHere);
        ConfirmPassword = findViewById(R.id.et_password_signup_c);
        mAuth = FirebaseAuth.getInstance();
        AtomicReference<String> UserID = null;
        mStore = FirebaseFirestore.getInstance();

//
//        if (mAuth.getCurrentUser() != null){
//            GoToSignIn();
//        }

        SignIn.setOnClickListener(view -> {
            GoToSignIn();
        });

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


                mAuth.createUserWithEmailAndPassword(CurrentEmail, CurrentPassword).addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        Toast.makeText(SignUp.this, "User Created!", Toast.LENGTH_SHORT).show();
                        String UserID = mAuth.getCurrentUser().getUid();
                        if (!UserID.equals(null)){
                            DocumentReference documentReference = mStore.collection("Users").document(UserID);
                            HashMap<String, Object> user = new HashMap<>();
                            user.put("Email", CurrentEmail);
                            user.put("Password", CurrentPassword);
                            user.put("Name", CurrentName);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    System.out.println("Account Created for User " + UserID);
                                    GoToSignIn();
                                }

                            });
                        }
                    }else
                        Toast.makeText(SignUp.this, "Task Not Successful", Toast.LENGTH_SHORT).show();
                });
            }
        });





//        PiecesRepository piecesRepository = new PiecesRepository();
//        piecesRepository.getPieceMutableLiveData(this);
    }

    private void GoToSignIn(){
        Intent i = new Intent();
        i.setClass(SignUp.this, SignIn.class);
        startActivity(i);
        finish();
    }
}