package com.example.ecbabywear;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.ecbabywear.Model.Piece;
import com.example.ecbabywear.UI.HomePage.HomePage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PiecesRepository {
    MutableLiveData<List<Piece>> pieceListMutableLiveData;
    DatabaseReference databaseReference;
    MutableLiveData<Piece> pieceMutableLiveData;

    private DocumentReference PiecesRef;

    public PiecesRepository() {
        this.pieceListMutableLiveData = new MutableLiveData<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Pieces");
        FirebaseFirestore firebaseFirestore= FirebaseFirestore.getInstance();
        pieceMutableLiveData = new MutableLiveData<>();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        firebaseFirestore.setFirestoreSettings(settings);
    }

    public MutableLiveData<List<Piece>> getPieceMutableLiveData() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Piece> pieceList = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Piece piece = ds.getValue(Piece.class);
                    pieceList.add(piece);
                }
                pieceListMutableLiveData.postValue(pieceList);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("System Problem");
            }
        });
        return pieceListMutableLiveData;

    }

}
