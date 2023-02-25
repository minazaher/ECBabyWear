package com.example.ecbabywear;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ecbabywear.Model.Piece;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ApplicationClass extends Application {

    public static Piece currentPiece;
    public static ArrayList<Piece> cart;
    public static ArrayList<Piece> approved;
    DatabaseReference databaseReference;
    public static ArrayList<Piece> FinalPieces;
    @Override
    public void onCreate() {
            super.onCreate();

            cart = new ArrayList<>();
            approved = new ArrayList<>();

            FinalPieces = (ArrayList<Piece>) PiecesViewModel.pieceList;



        Piece piece1 = new Piece("Girl Winter Dress", "https://m.media-amazon.com/images/I/41P59oy-MjL._AC_.jpg", "150.0 L.E", "Full Body Suit", "Papillon Basic Snap Closure Bodysuit for Kids - White, 9 Months", "Pending")         ;
        Piece piece2= new Piece("Baby Cadeau", "https://m.media-amazon.com/images/I/815l8Bpl2RL._AC_SX679_.jpg", "450.0 L.E", "Full Body Suit", "Papillon Basic Snap Closure Bodysuit for Kids - White, 9 Months", "Pending")         ;
        Piece piece3 = new Piece("Spring Dress" , "https://m.media-amazon.com/images/I/61-BoU+DFaL._AC_SX569_.jpg", "300.0 L.E", "Full Body Suit", "Papillon Basic Snap Closure Bodysuit for Kids - White, 9 Months", "Pending")         ;
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Pieces").child("PFour").setValue(piece1);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Pieces").child("PFive").setValue(piece2);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Pieces").child("PSix").setValue(piece3);
//
//            cart.add(new Piece(" Printed Bodysuit", "https://m.media-amazon.com/images/I/41O0ELB5PLL._AC_.jpg", "150.0 L.E", "Full Body Suit", "Papillon Basic Snap Closure Bodysuit for Kids - White, 9 Months", "Pending"));
//            cart.add(new Piece("Girl Winter Suit", "https://m.media-amazon.com/images/I/51dzdIMH-kL._AC_SX679_.jpg", "350.0 L.E", "Zipper Plush Onesie", "Papillon Basic Snap Closure Bodysuit for Kids - White, 9 Months", "Pending"));
//            cart.add(new Piece("Printed Bodysuit", "https://m.media-amazon.com/images/I/41O0ELB5PLL._AC_.jpg", "150.0 L.E", "Full Body Suit", "Papillon Basic Snap Closure Bodysuit for Kids - White, 9 Months", "Pending"));
//


    }




}
