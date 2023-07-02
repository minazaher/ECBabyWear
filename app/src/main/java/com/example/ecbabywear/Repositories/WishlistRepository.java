package com.example.ecbabywear.Repositories;

import static com.example.ecbabywear.ApplicationClass.firebaseFirestore;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ecbabywear.Piece;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class WishlistRepository {

    private FirebaseFirestore db;
    private String userId;

    public WishlistRepository(String userId) {
        this.db = FirebaseFirestore.getInstance();
        this.userId = userId;
    }


    public void CreateWishlist(String userId){
        Map<String, Object> wishlist = new HashMap<>();
        DocumentReference documentReference = firebaseFirestore.collection("Orders").document();
        wishlist.put("wishlistId", userId);
        wishlist.put("items", new ArrayList<HashMap<String, Object>>());

        firebaseFirestore.collection("Wishlist")
                .document(userId)
                .set(wishlist)
                .addOnSuccessListener(aVoid -> {
                    System.out.println("Created Successfully!!");
                })
                .addOnFailureListener(e -> {
                    System.out.println("Creation failed!" + e.getMessage());
                });
    }

    public boolean isInWishlist(Map<String, Object> piece){
        AtomicBoolean found = new AtomicBoolean(false);
        firebaseFirestore.collection("Wishlist")
                .whereEqualTo(FieldPath.documentId(), userId)
                .whereArrayContains("items", piece)
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (task.getResult().isEmpty()) {
                            System.out.println("Didn't find it");
                        } else {
                            found.set(task.isSuccessful());
                        }
                    } else {
                        System.out.println(task.getException());
                    }
                });
        return found.get();
    }


    public void addItemToWishlist(Map<String, Object> piece) {
        firebaseFirestore.collection("Wishlist")
                .document(userId)
                .update("items", FieldValue.arrayUnion(piece))
                .addOnSuccessListener(aVoid -> {
                    System.out.println("success");
                })
                .addOnFailureListener(e -> {
                    System.out.println("failed!" + e.getMessage());
                });
    }


    public void DeleteItemFromWishlist(Map<String, Object> piece) {
        firebaseFirestore.collection("Wishlist")
                .document(userId)
                .update("items", FieldValue.arrayRemove(piece))
                .addOnSuccessListener(aVoid -> {
                    System.out.println("success");
                })
                .addOnFailureListener(e -> {
                    System.out.println("failed!" + e.getMessage());
                });
    }

    public LiveData<List<Piece>> getWishlist() {
        MutableLiveData<List<Piece>> wishlistData = new MutableLiveData<>();
        firebaseFirestore.collection("Wishlist")
                .whereEqualTo("wishlistId", userId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            List<Map<String, Object>> pieces = (List<Map<String, Object>>) document.get("items");
                            List<Piece> pieceList = mapToPiece(pieces);
                            wishlistData.setValue(pieceList);
                        }
                    } else {
                        System.out.println("Error 404 Wishlist Not found!!");
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
            String img = (String) piece.get("URL");
            Piece p = new Piece(name, img, price, shortDesc, longDesc);
            pieceList.add(p);
        }
        return pieceList;
    }

}
