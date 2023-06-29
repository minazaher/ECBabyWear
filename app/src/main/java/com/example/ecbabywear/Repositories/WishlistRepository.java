package com.example.ecbabywear.Repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ecbabywear.Piece;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WishlistRepository {

    private FirebaseFirestore db;
    private String userId;

    public WishlistRepository(String userId) {
        this.db = FirebaseFirestore.getInstance();
        this.userId = userId;
    }


    public LiveData<List<Piece>> getWishlist() {
        MutableLiveData<List<Piece>> wishlistData = new MutableLiveData<>();
        DocumentReference docRef = db.collection("Users").document(userId);
        docRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                List<Map<String, Object>> pieces = (List<Map<String, Object>>) documentSnapshot.get("Wishlist");
                List<Piece> pieceList = mapToPiece(pieces);
                wishlistData.setValue(pieceList);
            }
        });
        return wishlistData;
    }

    private List<Piece> mapToPiece(List<Map<String, Object>> pieces) {
        List<Piece> pieceList = new ArrayList<>();
        for (Map<String, Object> piece : pieces) {
            String name = (String) piece.get("name");
            String shortDesc = (String) piece.get("shortDescription");
            String longDesc = (String) piece.get("longDescription");
            String price = (String) piece.get("price");
            String img = (String) piece.get("url");
            Piece p = new Piece(name, img, price, shortDesc, longDesc);
            pieceList.add(p);
        }
        return pieceList;
    }

}
