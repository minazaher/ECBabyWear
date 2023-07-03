package com.example.ecbabywear.UI.HomePage;


import static com.example.ecbabywear.ApplicationClass.firebaseAuth;
import static com.example.ecbabywear.ApplicationClass.navigateToActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecbabywear.R;
import com.example.ecbabywear.Repositories.PiecesRepository;
import com.example.ecbabywear.UI.Cart;
import com.example.ecbabywear.UI.OrderHistory.OrderHistory;
import com.example.ecbabywear.UI.Profile;
import com.example.ecbabywear.UI.SignIn;
import com.example.ecbabywear.Utilis.CategoriesAdapter;
import com.example.ecbabywear.Utilis.StoreAdapter;
import com.example.ecbabywear.databinding.ActivityHomePageBinding;
import com.google.android.material.navigation.NavigationView;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView newArrivals, Categories;
    PiecesRepository piecesRepository;
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
        piecesRepository = new PiecesRepository();
        initializeDrawerLayout();
        initializeCategoriesRecycler();
        initializeNewArrivalsRecycler();

        HomePageBinding.fabCart.setOnClickListener((View view) -> {
            navigateToActivity(this, Cart.class);
        });

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            showSignOutMessage();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_orders) {
            navigateToActivity(this, OrderHistory.class);
        }
        else if (item.getItemId() ==  R.id.menu_profile){
            navigateToActivity(this, Profile.class);
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



    private void initializeDrawerLayout(){
        drawerLayout = HomePageBinding.drawerLayout;
        navigationView = HomePageBinding.navView;
        setSupportActionBar(HomePageBinding.toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,HomePageBinding.toolbar, R.string.nav_open, R.string.nav_close);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void initializeCategoriesRecycler() {
        Categories = HomePageBinding.catsRecview;
        Categories.setAdapter(new CategoriesAdapter(this));
        Categories.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
    }

    private void initializeNewArrivalsRecycler() {
        newArrivals = HomePageBinding.storeRecview;
        newArrivals.setNestedScrollingEnabled(false);
        newArrivals.setLayoutManager(CustomGridLayout());
        piecesRepository.getPieceMutableLiveData(
                newArrivalList -> {
                    StoreAdapter storeAdapter = new StoreAdapter(this,newArrivalList);
                    newArrivals.setAdapter(storeAdapter);
                });
    }


    private GridLayoutManager CustomGridLayout(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        if (getScreenWidth()  >= 1600){
            gridLayoutManager.setSpanCount(4);
        }
        return gridLayoutManager;
    }

    private int getScreenWidth(){
        Display display = getWindowManager(). getDefaultDisplay();
        Point size = new Point();
        display. getSize(size);
        return size.x;
    }

    private void showSignOutMessage(){
        new AlertDialog.Builder(this)
                .setTitle("Sign Out")
                .setMessage("Are you sure you want to sign out?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, (arg0, arg1) -> {
                    firebaseAuth.signOut();
                    navigateToActivity(HomePage.this, SignIn.class);
                }).create().show();
    }


}


