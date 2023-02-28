package com.example.ecbabywear.UI.HomePage;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MenuItem;
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
import com.google.android.material.navigation.NavigationView;
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
    Toolbar toolbar;
    NavigationView navigationView;
    ConstraintLayout trans;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        fab_cart = findViewById(R.id.fab_cart);
        btn_profile = findViewById(R.id.btn_profile);
        btn_swap = findViewById(R.id.btn_swap);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar= findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


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


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private void initRecView() {
        recyclerView = findViewById(R.id.store_recview);
        Display display = getWindowManager(). getDefaultDisplay();
        Point size = new Point();
        display. getSize(size);
        int width = size.x;

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        if (width  == 1600){
                gridLayoutManager.setSpanCount(4);
        }
        recyclerView.setLayoutManager(gridLayoutManager);

        StoreAdapter storeAdapter = new StoreAdapter(this, ApplicationClass.FinalPieces);

        piecesViewModel = ViewModelProviders.of(this).get(PiecesViewModel.class);
        piecesViewModel.getLivePiecesData().observe(this, pieces -> storeAdapter.updatePiecesList(pieces));

        recyclerView.setAdapter(storeAdapter);
        System.out.println("ITEMS : " + PiecesViewModel.pieceList.toString());
        recyclerView.setVisibility(View.VISIBLE);
    }



}


