//package com.example.ecbabywear;
//
//import android.content.Context;
//import android.util.Log;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.lifecycle.MutableLiveData;
//
//import com.example.ecbabywear.Model.Piece;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.EventListener;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.FirebaseFirestoreException;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.firestore.QuerySnapshot;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class PiecesRepository {
//    MutableLiveData<List<Piece>> pieceListMutableLiveData;
//    FirebaseFirestore mFirestore;
//    MutableLiveData<Piece> pieceMutableLiveData;
//
//    private DocumentReference PiecesRef;
//
//    public PiecesRepository() {
//        this.pieceListMutableLiveData = new MutableLiveData<>();
//        //define firestore
//        mFirestore = FirebaseFirestore.getInstance();
//        //define bloglist
//        pieceMutableLiveData = new MutableLiveData<>();
//
//        PiecesRef = mFirestore.collection("Pieces").document("tf1aK8ibvA4OmsFM2WTN");
//    }
//    //get blog from firebase firestore
//    public MutableLiveData<List<Piece>> getPieceListMutableLiveData() {
//        Log.i("TAG", "getBlogListMutableLiveData: ");
//        mFirestore.collection("Pieces").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                List<Piece> pieceList = new ArrayList<>();
//                for (QueryDocumentSnapshot doc : value) {
//                    if (doc != null) {
//                        pieceList.add(doc.toObject(Piece.class));
//                    }
//                }
//                pieceListMutableLiveData.postValue(pieceList);
//            }
//        });
//        return pieceListMutableLiveData;
//    }
//
//    public MutableLiveData<List<Piece>> getPieceMutableLiveData(Context context) {
//            PiecesRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                @Override
//                public void onSuccess(DocumentSnapshot documentSnapshot) {
//                    List<Piece> pieceList = new ArrayList<>();
//                    pieceList.add(documentSnapshot.toObject(Piece.class));
//                    pieceListMutableLiveData.postValue(pieceList);
//                    Toast.makeText(context, pieceList.get(0).getName(), Toast.LENGTH_SHORT).show();
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(context, "No Data!", Toast.LENGTH_SHORT).show();
//                }
//            });
//            return pieceListMutableLiveData;
//    }
//}
