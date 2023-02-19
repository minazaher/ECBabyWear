package com.example.ecbabywear.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//import com.example.ecbabywear.PiecesRepository;
import com.example.ecbabywear.R;

public class SignUp extends AppCompatActivity {
    Button SignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        SignUp = findViewById(R.id.btn_signUp);


        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(SignUp.this, SignIn.class);
                startActivity(i);
            }
        });
//        PiecesRepository piecesRepository = new PiecesRepository();
//        piecesRepository.getPieceMutableLiveData(this);
    }
}