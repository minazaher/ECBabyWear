package com.example.ecbabywear;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ecbabywear.Model.Piece;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class PiecesViewModel extends ViewModel {
    MutableLiveData<List<Piece>> pieceListMutableLiveData;
    DatabaseReference databaseReference;
    public static List<Piece> pieceList = new ArrayList<>();
    PiecesRepository piecesRepository;

    public PiecesViewModel() {
        piecesRepository = new PiecesRepository();
        pieceListMutableLiveData = new MutableLiveData<>();
        pieceListMutableLiveData = piecesRepository.getPieceMutableLiveData();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Pieces");

        init();
    }

    public MutableLiveData<List<Piece>> getLivePiecesData() {
        return pieceListMutableLiveData;
    }

    public void init(){
        pieceListMutableLiveData.setValue(pieceList);
    }

}

