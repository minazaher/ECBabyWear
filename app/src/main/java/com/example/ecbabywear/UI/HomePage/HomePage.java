package com.example.ecbabywear.UI.HomePage;


import static com.example.ecbabywear.ApplicationClass.navigateToActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
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
import com.example.ecbabywear.UI.OrderHistory.OrderHistory;
import com.example.ecbabywear.R;
import com.example.ecbabywear.UI.Cart.Cart;
import com.example.ecbabywear.databinding.ActivityHomePageBinding;
import com.example.ecbabywear.databinding.ActivitySignUpBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class HomePage extends AppCompatActivity implements LifecycleOwner, NavigationView.OnNavigationItemSelectedListener {

    RecyclerView NewArrivals, Categories  ;
    PiecesViewModel piecesViewModel;
    NavigationView navigationView;
    ActivityHomePageBinding HomePageBinding;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        HomePageBinding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(HomePageBinding.getRoot());

        Categories = HomePageBinding.catsRecview;
        drawerLayout = HomePageBinding.drawerLayout;
        navigationView = HomePageBinding.navView;

        setSupportActionBar(HomePageBinding.toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,HomePageBinding.toolbar, R.string.nav_open, R.string.nav_close);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        Categories.setAdapter(new CategoriesAdapter(this));
        Categories.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        initRecView();

        HomePageBinding.fabCart.setOnClickListener((View view) -> {
            navigateToActivity(this, Cart.class);
        });

    }



    private void initRecView() {
        NewArrivals = HomePageBinding.storeRecview;
        NewArrivals.setNestedScrollingEnabled(false);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        if (getScreenWidth()  >= 1600){
                gridLayoutManager.setSpanCount(4);
        }
        NewArrivals.setLayoutManager(gridLayoutManager);

        StoreAdapter storeAdapter = new StoreAdapter(this, ApplicationClass.FinalPieces);
        NewArrivals.setAdapter(storeAdapter);

//        piecesViewModel = ViewModelProviders.of(this).get(PiecesViewModel.class);
//        piecesViewModel.getLivePiecesData().observe(this, storeAdapter::updatePiecesList);
    }

    private int getScreenWidth(){
        Display display = getWindowManager(). getDefaultDisplay();
        Point size = new Point();
        display. getSize(size);
        return size.x;
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
             super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_orders) {
            navigateToActivity(this, OrderHistory.class);
            return true;
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}


