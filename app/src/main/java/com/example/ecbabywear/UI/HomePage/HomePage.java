package com.example.ecbabywear.UI.HomePage;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ecbabywear.ApplicationClass;
import com.example.ecbabywear.Model.Piece;
import com.example.ecbabywear.PiecesViewModel;
import com.example.ecbabywear.R;
import com.example.ecbabywear.UI.Cart;
import com.example.ecbabywear.UI.Requests;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity implements LifecycleOwner {

    RecyclerView recyclerView ;
    LinearLayout btn_profile, btn_swap;
    FloatingActionButton fab_cart;
    ImageView noData;
    MutableLiveData<List<Piece>> ceList ;
    PiecesViewModel piecesViewModel;
    ConstraintLayout trans;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        fab_cart = findViewById(R.id.fab_cart);
        btn_profile = findViewById(R.id.btn_profile);
        btn_swap = findViewById(R.id.btn_swap);
        animation = AnimationUtils.loadAnimation(this, R.anim.transation_anim);
        initRecView();



        fab_cart.setOnClickListener((View view) -> {
         Intent intent = new Intent(HomePage.this, Cart.class);
         startActivity(intent);});

        btn_profile.setOnClickListener(view -> {
            Intent intent = new Intent(HomePage.this, Requests.class);
            startActivity(intent);
        });

        btn_swap.setOnClickListener(view -> {
            noData = findViewById(R.id.img_noData);
            recyclerView.setVisibility(View.GONE);
            noData.setVisibility(View.VISIBLE);
            fab_cart.setImageResource(R.drawable.ic_baseline_post_add_24);
        });

    }



    private void initRecView() {
        recyclerView = findViewById(R.id.store_recview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        StoreAdapter storeAdapter = new StoreAdapter(this, ApplicationClass.FinalPieces);

        piecesViewModel = ViewModelProviders.of(this).get(PiecesViewModel.class);
        piecesViewModel.getLivePiecesData().observe(this, pieces -> storeAdapter.updatePiecesList(pieces));

        recyclerView.setAdapter(storeAdapter);
        System.out.println("ITEMS : " + PiecesViewModel.pieceList.toString());
        recyclerView.setVisibility(View.VISIBLE);
    }



}


