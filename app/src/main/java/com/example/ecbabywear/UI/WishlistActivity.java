package com.example.ecbabywear.UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecbabywear.Piece;
import com.example.ecbabywear.R;
import com.example.ecbabywear.Utilis.WishlistAdapter;
import com.example.ecbabywear.ViewModel.WishlistViewModel;
import com.example.ecbabywear.databinding.ActivityWishlistBinding;

import java.util.ArrayList;
import java.util.List;

public class WishlistActivity extends AppCompatActivity {

    ActivityWishlistBinding wishlistBinding;
    RecyclerView wishlistRecyclerView;
    ArrayList<Piece> wishlist;
    private WishlistViewModel viewModel ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wishlistBinding = DataBindingUtil.setContentView(this, R.layout.activity_wishlist);
        viewModel = new ViewModelProvider(this).get(WishlistViewModel.class);
        getWishlist();
        wishlistBinding.toolbar.textView11.setText("My Wishlist");
        wishlistBinding.toolbar.back.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void initializeNewArrivalsRecycler(List<Piece> wishlist) {
        wishlistRecyclerView = wishlistBinding.wishlistRecview;
        wishlistRecyclerView.setNestedScrollingEnabled(false);
        wishlistRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false));
        WishlistAdapter storeAdapter = new WishlistAdapter(wishlist);
                    wishlistRecyclerView.setAdapter(storeAdapter);
    }

    private void getWishlist(){
        wishlist = new ArrayList<>();
        viewModel.getWishlist().observe(this, this::initializeNewArrivalsRecycler
        );
    }



}
