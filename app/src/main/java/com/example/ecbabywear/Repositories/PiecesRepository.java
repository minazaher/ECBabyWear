package com.example.ecbabywear.Repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.ecbabywear.Piece;
import com.example.ecbabywear.Utilis.PiecesCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
public class PiecesRepository {
    MutableLiveData<List<Piece>> pieceListMutableLiveData;
    DatabaseReference databaseReference;

    public PiecesRepository() {
        this.pieceListMutableLiveData = new MutableLiveData<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Pieces");
    }

    public List<Piece> getPieceMutableLiveData(PiecesCallback listener) {
        List<Piece> pieceList = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Piece piece = ds.getValue(Piece.class);
                    pieceList.add(piece);
                }
                listener.onPiecesRetrievedListener(pieceList);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("System Problem");
            }
        });
        return pieceList;
    }

}
