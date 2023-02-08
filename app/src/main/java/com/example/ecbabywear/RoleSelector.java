package com.example.ecbabywear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class RoleSelector extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_selector);
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                } finally {
                    finish();
                    Intent i = new Intent();
                    i.setClass(RoleSelector.this, HomePage.class);
                    startActivity(i);
                }
            }
        };
        splashTread.start();
    }
    }
