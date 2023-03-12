package com.example.ecbabywear;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import com.example.ecbabywear.Model.CartItem;
import com.example.ecbabywear.Model.Order;
import com.example.ecbabywear.Model.Piece;
import com.example.ecbabywear.UI.HomePage.PiecesViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class ApplicationClass extends Application {

    public static Piece currentPiece;
    public static ArrayList<CartItem> cart;
    public static ArrayList<Piece> approved;
    DatabaseReference databaseReference;
    public static ArrayList<Order> orders, Cancelled;
    public static FirebaseFirestore firebaseFirestore;
    public static FirebaseAuth firebaseAuth;


    public static ArrayList<Piece> FinalPieces;
    @Override
    public void onCreate() {
            super.onCreate();

            cart = new ArrayList<>();
            approved = new ArrayList<>();
            FinalPieces = (ArrayList<Piece>) PiecesViewModel.pieceList;


        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();


        orders = new ArrayList<>();
        Cancelled = new ArrayList<>();

//        Piece piece1 = new Piece("Girl Winter Dress", "https://m.media-amazon.com/images/I/41P59oy-MjL._AC_.jpg", "150.0", "Full Body Suit", "Papillon Basic Snap Closure Bodysuit for Kids - White, 9 Months", "Pending")         ;
//        Piece piece2= new Piece("Baby Cadeau", "https://m.media-amazon.com/images/I/815l8Bpl2RL._AC_SX679_.jpg", "450.0", "Full Body Suit", "Papillon Basic Snap Closure Bodysuit for Kids - White, 9 Months", "Pending")         ;
//        Piece piece3 = new Piece("Spring Dress" , "https://m.media-amazon.com/images/I/61-BoU+DFaL._AC_SX569_.jpg", "300.0", "Full Body Suit", "Papillon Basic Snap Closure Bodysuit for Kids - White, 9 Months", "Pending")         ;
//        databaseReference = FirebaseDatabase.getInstance().getReference();
//        databaseReference.child("Pieces").child("PFour").setValue(piece1);
//        databaseReference = FirebaseDatabase.getInstance().getReference();
//        databaseReference.child("Pieces").child("PFive").setValue(piece2);
//        databaseReference = FirebaseDatabase.getInstance().getReference();
//        databaseReference.child("Pieces").child("PSix").setValue(piece3);
//
//            cart.add(new Piece(" Printed Bodysuit", "https://m.media-amazon.com/images/I/41O0ELB5PLL._AC_.jpg", "150.0 L.E", "Full Body Suit", "Papillon Basic Snap Closure Bodysuit for Kids - White, 9 Months", "Pending"));
//            cart.add(new Piece("Girl Winter Suit", "https://m.media-amazon.com/images/I/51dzdIMH-kL._AC_SX679_.jpg", "350.0 L.E", "Zipper Plush Onesie", "Papillon Basic Snap Closure Bodysuit for Kids - White, 9 Months", "Pending"));
//            cart.add(new Piece("Printed Bodysuit", "https://m.media-amazon.com/images/I/41O0ELB5PLL._AC_.jpg", "150.0 L.E", "Full Body Suit", "Papillon Basic Snap Closure Bodysuit for Kids - White, 9 Months", "Pending"));
//

        getAllOrders();



    }


    public static void getAllOrders(){
        firebaseFirestore.collection("Orders")
                .whereEqualTo("orderID",firebaseAuth.getCurrentUser().getUid() )
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            if (Objects.equals(document.get("Status"), "Completed"))
                                orders.add(document.toObject(Order.class));
                            else
                                Cancelled.add(document.toObject(Order.class));
                        }
                    } else {
                        System.out.println("Error getting documents: " + task.getException());
                    }
                });
    }


    public static void restartActivity(Activity act){
        Intent intent=new Intent();
        intent.setClass(act, act.getClass());
        act.finish();
        act.startActivity(intent);
    }





}
