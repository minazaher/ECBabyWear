package com.example.ecbabywear.UI;

import static com.example.ecbabywear.ApplicationClass.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ecbabywear.ApplicationClass;
import com.example.ecbabywear.CartAdapter;
import com.example.ecbabywear.R;
import com.example.ecbabywear.UI.HomePage.CategoriesAdapter;
import com.example.ecbabywear.UI.HomePage.HomePage;
import com.example.ecbabywear.databinding.ActivityCartBinding;

public class Cart extends AppCompatActivity {
    ActivityCartBinding activityCartBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCartBinding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(activityCartBinding.getRoot());



        activityCartBinding.btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HomePage.class));
                cart.clear();
                Toast.makeText(getApplicationContext(), "Checkout Done Sucessfully", Toast.LENGTH_SHORT).show();
            }
        });

        activityCartBinding.cartRecview.setAdapter(new CartAdapter(getApplicationContext(), cart, 1));
        activityCartBinding.cartRecview.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));



    }
}