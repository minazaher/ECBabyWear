package com.example.ecbabywear;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ecbabywear.Model.Piece;

import java.util.ArrayList;

public class ApplicationClass extends Application {

    public static Piece currentPiece;
//    public static MutableLiveData<ArrayList<Piece>> pieces;
    public static ArrayList<Piece> cart;
    public static ArrayList<Piece> approved;

    @Override
    public void onCreate() {
        super.onCreate();

//        pieces = new MutableLiveData<ArrayList<Piece>>();
        cart = new ArrayList<>();
        approved = new ArrayList<>();


//        pieces.add(new Piece("Girl Winter Suit", "https://m.media-amazon.com/images/I/51dzdIMH-kL._AC_SX679_.jpg", "350.0 L.E", "Zipper Plush Onesie", "Papillon Basic Snap Closure Bodysuit for Kids - White, 9 Months", "Pending"));
//        pieces.add(new Piece("Long Sleeve Suit", "https://m.media-amazon.com/images/I/410YJpaLQWS._AC_SX679_.jpg", "50.0 L.E", "Body Suit", "Elsayaad Cotton Long Sleeves Bodysuit for Kids - White, 6-9 Months","Approved"));
//        pieces.add(new Piece("Printed Bodysuit", "https://m.media-amazon.com/images/I/41O0ELB5PLL._AC_.jpg", "150.0 L.E", "Full Body Suit", "Papillon Basic Snap Closure Bodysuit for Kids - White, 9 Months", "Pending"));
//        pieces.add(new Piece("Printed Bodysuit", "https://m.media-amazon.com/images/I/41O0ELB5PLL._AC_.jpg", "150.0 L.E", "Full Body Suit", "Papillon Basic Snap Closure Bodysuit for Kids - White, 9 Months", "Pending"));
//        pieces.add(new Piece("Girl Winter Suit", "https://m.media-amazon.com/images/I/51dzdIMH-kL._AC_SX679_.jpg", "350.0 L.E", "Zipper Plush Onesie", "Papillon Basic Snap Closure Bodysuit for Kids - White, 9 Months", "Pending"));

        cart.add(new Piece("Printed Bodysuit", "https://m.media-amazon.com/images/I/41O0ELB5PLL._AC_.jpg", "150.0 L.E", "Full Body Suit", "Papillon Basic Snap Closure Bodysuit for Kids - White, 9 Months", "Pending"));
        cart.add(new Piece("Girl Winter Suit", "https://m.media-amazon.com/images/I/51dzdIMH-kL._AC_SX679_.jpg", "350.0 L.E", "Zipper Plush Onesie", "Papillon Basic Snap Closure Bodysuit for Kids - White, 9 Months", "Pending"));
        cart.add(new Piece("Printed Bodysuit", "https://m.media-amazon.com/images/I/41O0ELB5PLL._AC_.jpg", "150.0 L.E", "Full Body Suit", "Papillon Basic Snap Closure Bodysuit for Kids - White, 9 Months", "Pending"));


    }




}
