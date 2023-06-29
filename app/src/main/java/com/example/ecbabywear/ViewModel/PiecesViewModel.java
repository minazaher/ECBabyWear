package com.example.ecbabywear.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ecbabywear.Piece;
import com.example.ecbabywear.Repositories.PiecesRepository;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class PiecesViewModel extends ViewModel {
    public MutableLiveData<List<Piece>> pieceListMutableLiveData;
    DatabaseReference databaseReference;
    public static List<Piece> pieceList = new ArrayList<>();
    PiecesRepository piecesRepository;

    public PiecesViewModel() {
        piecesRepository = new PiecesRepository();
        pieceListMutableLiveData = new MutableLiveData<>();
//      pieceListMutableLiveData = piecesRepository.getPieceMutableLiveData();
        System.out.println(pieceListMutableLiveData);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Pieces");
        init();
    }

    public MutableLiveData<List<Piece>> getLivePiecesData() {
        return pieceListMutableLiveData;
    }

    public void init(){
        pieceList = getLivePiecesData().getValue();
    }

}

