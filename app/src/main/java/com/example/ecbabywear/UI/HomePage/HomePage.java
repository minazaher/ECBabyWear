package com.example.ecbabywear.UI.HomePage;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ecbabywear.Model.Piece;
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

public class HomePage extends AppCompatActivity {

    RecyclerView recyclerView ;
    LinearLayout btn_profile, btn_swap;
    FloatingActionButton fab_cart;
    ImageView noData;
    MutableLiveData<List<Piece>> ceList ;

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        fab_cart = findViewById(R.id.fab_cart);
        btn_profile = findViewById(R.id.btn_profile);
        btn_swap = findViewById(R.id.btn_swap);


        initalizeRecView();

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



    private void initalizeRecView(){
        recyclerView = findViewById(R.id.store_recview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Pieces");

        ArrayList <Piece> pieceArrayList = new ArrayList<>();
        StoreAdapter storeAdapter = new StoreAdapter(this, pieceArrayList);
        recyclerView.setAdapter(storeAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Piece piece = ds.getValue(Piece.class);
                    pieceArrayList.add(piece);
                    Toast.makeText(HomePage.this, ds.toString(), Toast.LENGTH_SHORT).show();
                }
                storeAdapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(HomePage.this, "No Data!"+ databaseError, Toast.LENGTH_SHORT).show();
            }
        });


    }
//    private void initalizeRecView(){
//         PiecesViewModel piecesViewModel= new ViewModelProvider(this).get(PiecesViewModel.class);
//
//
//
//
//        piecesViewModel.getLivePiecesData().observe(this, pieces -> {
//            StoreAdapter storeAdapter = new StoreAdapter(this, pieces);
//            recyclerView.setVisibility(View.VISIBLE);
//        });
//
//    }
//
//    private MutableLiveData<List<Piece>> getPieces(){
//
//
//
//        MutableLiveData<List<Piece>> piecesLiveData;
//        PiecesViewModel piecesViewModel = new PiecesViewModel();
//        piecesLiveData = piecesViewModel.getLivePiecesData();
//        return piecesLiveData;
//    }
}