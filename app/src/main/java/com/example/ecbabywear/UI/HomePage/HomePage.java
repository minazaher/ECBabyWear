package com.example.ecbabywear.UI.HomePage;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity implements LifecycleOwner {

    RecyclerView recyclerView ;
    LinearLayout btn_profile, btn_swap;
    FloatingActionButton fab_cart;
    ImageView noData;
    MutableLiveData<List<Piece>> ceList ;
    PiecesViewModel piecesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        fab_cart = findViewById(R.id.fab_cart);
        btn_profile = findViewById(R.id.btn_profile);
        btn_swap = findViewById(R.id.btn_swap);


        initRecView();

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



    private void initRecView() {
        recyclerView = findViewById(R.id.store_recview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        StoreAdapter storeAdapter = new StoreAdapter(this, PiecesViewModel.pieceList);

        piecesViewModel = ViewModelProviders.of(this).get(PiecesViewModel.class);
        piecesViewModel.getLivePiecesData().observe(this, new Observer<List<Piece>>() {
            @Override
            public void onChanged(List<Piece> pieces) {
                storeAdapter.updatePiecesList(pieces);
            }
        });


        recyclerView.setAdapter(storeAdapter);
        System.out.println("ITEMS : " + PiecesViewModel.pieceList.toString());
        recyclerView.setVisibility(View.VISIBLE);
    }


//    private void initalizeRecView(){
//         PiecesViewModel piecesViewModel= new ViewModelProvider(this).get(PiecesViewModel.class);
//         piecesViewModel.getLivePiecesData().observe(this, pieces -> {
//            StoreAdapter storeAdapter = new StoreAdapter(this, pieces);
//            recyclerView.setVisibility(View.VISIBLE);
//        });
//
//    }
//
//    private MutableLiveData<List<Piece>> getPieces(){
//        MutableLiveData<List<Piece>> piecesLiveData;
//        PiecesViewModel piecesViewModel = new PiecesViewModel();
//        piecesLiveData = piecesViewModel.getLivePiecesData();
//        return piecesLiveData;
//    }
}