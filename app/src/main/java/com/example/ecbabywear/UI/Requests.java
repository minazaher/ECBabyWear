package com.example.ecbabywear.UI;

import static com.example.ecbabywear.ApplicationClass.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ecbabywear.CartAdapter;
import com.example.ecbabywear.R;

public class Requests extends AppCompatActivity {
    RecyclerView recyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);


        recyclerView = findViewById(R.id.request_recview);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, RecyclerView.VERTICAL, false);
        CartAdapter storeAdapter = new CartAdapter(this, cart,2);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(storeAdapter);
    }
}