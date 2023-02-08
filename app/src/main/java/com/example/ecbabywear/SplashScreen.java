package com.example.ecbabywear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                } finally {
                    finish();
                    Intent i = new Intent();
                    i.setClass(SplashScreen.this, SignUp.class);
                    startActivity(i);
                }
            }
        };
        splashTread.start();
    }
}