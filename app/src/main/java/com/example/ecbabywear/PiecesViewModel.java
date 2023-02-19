//package com.example.ecbabywear;
//
//import androidx.lifecycle.MutableLiveData;
//import androidx.lifecycle.ViewModel;
//
//import com.example.ecbabywear.Model.Piece;
//import com.google.firebase.firestore.FirebaseFirestore;
//
//import java.util.List;
//
//public class PiecesViewModel extends ViewModel {
//    MutableLiveData<List<Piece>> pieceListMutableLiveData;
//    FirebaseFirestore mFirestore;
//    PiecesRepository piecesRepository;
//
//    public PiecesViewModel() {
//        piecesRepository = new PiecesRepository();
//        pieceListMutableLiveData = piecesRepository.getPieceListMutableLiveData();
//        mFirestore = FirebaseFirestore.getInstance();
//
//
//    }
//
//    public MutableLiveData<List<Piece>> getLivePiecesData() {
//        return pieceListMutableLiveData;
//    }
//}
//
