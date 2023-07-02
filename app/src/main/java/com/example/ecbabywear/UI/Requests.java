package com.example.ecbabywear.UI;

import static com.example.ecbabywear.ApplicationClass.cart;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecbabywear.R;
import com.example.ecbabywear.Utilis.CartAdapter;

public class Requests extends AppCompatActivity {
    RecyclerView recyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);


        recyclerView = findViewById(R.id.request_recview);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, RecyclerView.VERTICAL, false);
        CartAdapter storeAdapter = new CartAdapter(this, cart);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(storeAdapter);
    }
}