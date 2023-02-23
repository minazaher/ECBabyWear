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
        pieceList.add(new Piece("Girl Winter Suit", "https://m.media-amazon.com/images/I/51dzdIMH-kL._AC_SX679_.jpg", "350.0 L.E", "Zipper Plush Onesie", "Papillon Basic Snap Closure Bodysuit for Kids - White, 9 Months", "Pending"));
        pieceList.add(new Piece("Long Sleeve Suit", "https://m.media-amazon.com/images/I/410YJpaLQWS._AC_SX679_.jpg", "50.0 L.E", "Body Suit", "Elsayaad Cotton Long Sleeves Bodysuit for Kids - White, 6-9 Months","Approved"));
        pieceList.add(new Piece("Printed Bodysuit", "https://m.media-amazon.com/images/I/41O0ELB5PLL._AC_.jpg", "150.0 L.E", "Full Body Suit", "Papillon Basic Snap Closure Bodysuit for Kids - White, 9 Months", "Pending"));
        pieceList.add(new Piece("Printed Bodysuit", "https://m.media-amazon.com/images/I/41O0ELB5PLL._AC_.jpg", "150.0 L.E", "Full Body Suit", "Papillon Basic Snap Closure Bodysuit for Kids - White, 9 Months", "Pending"));
        pieceList.add(new Piece("Girl Winter Suit", "https://m.media-amazon.com/images/I/51dzdIMH-kL._AC_SX679_.jpg", "350.0 L.E", "Zipper Plush Onesie", "Papillon Basic Snap Closure Bodysuit for Kids - White, 9 Months", "Pending"));


        pieceListMutableLiveData.setValue(pieceList);
    }

}

