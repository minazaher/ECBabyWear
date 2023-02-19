package com.example.ecbabywear.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ecbabywear.R;
import com.example.ecbabywear.UI.HomePage.HomePage;

public class SignIn extends AppCompatActivity {
Button SignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        SignIn = findViewById(R.id.btn_signIn);


        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(SignIn.this, HomePage.class);
                startActivity(i);
            }
        });

    }
}