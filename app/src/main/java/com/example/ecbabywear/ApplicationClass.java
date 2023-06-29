package com.example.ecbabywear;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import com.example.ecbabywear.Model.CartItem;
import com.example.ecbabywear.ViewModel.PiecesViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ApplicationClass extends Application {

    public static Piece currentPiece;
    public static ArrayList<CartItem> cart;
    public static ArrayList<Piece> approved;
    public static FirebaseFirestore firebaseFirestore;
    public static FirebaseAuth firebaseAuth;
    public static DatabaseReference databaseReference;

    public static List<Piece> FinalPieces;
    @Override
    public void onCreate() {
        super.onCreate();

        cart = new ArrayList<>();
        approved = new ArrayList<>();
        FinalPieces = (ArrayList<Piece>) PiecesViewModel.pieceList;
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

    }

    public static void restartActivity(Activity act){
        Intent intent=new Intent();
        intent.setClass(act, act.getClass());
        act.finish();
        act.startActivity(intent);
    }

    public static void navigateToActivity(Activity context, Class destinationActivity) {
        Intent intent = new Intent(context.getApplicationContext(), destinationActivity);
        context.startActivity(intent);
    }

}
