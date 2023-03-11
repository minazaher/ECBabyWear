package com.example.ecbabywear.UI.HomePage;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.ecbabywear.ApplicationClass;
import com.example.ecbabywear.Model.Piece;
import com.example.ecbabywear.R;
import com.example.ecbabywear.UI.Cart;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class HomePage extends AppCompatActivity implements LifecycleOwner {

    RecyclerView NewArrivals, Categories  ;
    LinearLayout btn_profile, btn_swap, linearLayout_recview;
    ImageView img;
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
        Categories = findViewById(R.id.cats_recview);
        img = findViewById(R.id.img_noData);
        linearLayout_recview = findViewById(R.id.linear_recView);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar= findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        Categories.setAdapter(new CategoriesAdapter(this));
        Categories.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));


        initRecView();


        fab_cart.setOnClickListener((View view) -> {
         Intent intent = new Intent(HomePage.this, Cart.class);
         startActivity(intent);
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
        NewArrivals = findViewById(R.id.store_recview);
        Display display = getWindowManager(). getDefaultDisplay();
        Point size = new Point();
        display. getSize(size);
        int width = size.x;


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        NewArrivals.setNestedScrollingEnabled(false);
        if (width  == 1600){
                gridLayoutManager.setSpanCount(4);
        }
        NewArrivals.setLayoutManager(gridLayoutManager);

        StoreAdapter storeAdapter = new StoreAdapter(this, ApplicationClass.FinalPieces);

        piecesViewModel = ViewModelProviders.of(this).get(PiecesViewModel.class);
        piecesViewModel.getLivePiecesData().observe(this, pieces -> storeAdapter.updatePiecesList(pieces));

        NewArrivals.setAdapter(storeAdapter);
        System.out.println("ITEMS : " + PiecesViewModel.pieceList.toString());
        NewArrivals.setVisibility(View.VISIBLE);
    }



}


