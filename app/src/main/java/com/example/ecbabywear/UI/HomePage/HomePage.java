package com.example.ecbabywear.UI;

import static com.example.ecbabywear.ApplicationClass.pieces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.ecbabywear.R;
import com.example.ecbabywear.StoreAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomePage extends AppCompatActivity {

    RecyclerView recyclerView ;
    LinearLayout btn_profile, btn_swap;
    FloatingActionButton fab_cart;
    ImageView noData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        recyclerView = findViewById(R.id.store_recview);
        fab_cart = findViewById(R.id.fab_cart);
        btn_profile = findViewById(R.id.btn_profile);
        btn_swap = findViewById(R.id.btn_swap);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        StoreAdapter storeAdapter = new StoreAdapter(this, pieces);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(storeAdapter);
        recyclerView.setVisibility(View.VISIBLE);

        fab_cart.setOnClickListener(view -> {
         Intent intent = new Intent(HomePage.this, Cart.class);
         startActivity(intent);});

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, Requests.class);
                startActivity(intent);
            }
        });

        btn_swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noData = findViewById(R.id.img_noData);
                recyclerView.setVisibility(View.GONE);
                noData.setVisibility(View.VISIBLE);
                fab_cart.setImageResource(R.drawable.ic_baseline_post_add_24);
            }
        });
    }
}