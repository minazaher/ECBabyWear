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

import com.example.ecbabywear.CartAdapter;
import com.example.ecbabywear.Model.Piece;
import com.example.ecbabywear.PiecesViewModel;
import com.example.ecbabywear.R;
import com.example.ecbabywear.UI.HomePage.HomePage;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {
    RecyclerView recyclerView;
    AppCompatButton Checkout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        recyclerView = findViewById(R.id.cart_recview);
        Checkout = findViewById(R.id.btn_checkout);


        Checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HomePage.class));
                cart.clear();
                Toast.makeText(getApplicationContext(), "Checkout Done Sucessfully", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(new CartAdapter(this, cart,1));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));



    }
}