package com.example.ecbabywear.UI.OrderHistory;

import static com.example.ecbabywear.ApplicationClass.Cancelled;
import static com.example.ecbabywear.ApplicationClass.databaseReference;
import static com.example.ecbabywear.ApplicationClass.firebaseAuth;
import static com.example.ecbabywear.ApplicationClass.firebaseFirestore;
import static com.example.ecbabywear.ApplicationClass.orders;

import androidx.lifecycle.MutableLiveData;

import com.example.ecbabywear.Model.Order;
import com.example.ecbabywear.Model.Piece;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.List;
import java.util.Objects;

public class OrderRepository {
    MutableLiveData<List<Piece>> ordersListMutableLiveData;
    MutableLiveData<Piece> orderMutableLiveData;
    private DocumentReference ordersRef;

    public OrderRepository() {
        this.orderMutableLiveData = new MutableLiveData<>();
        this.ordersListMutableLiveData = new MutableLiveData<>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Orders");
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        firebaseFirestore.setFirestoreSettings(settings);
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

}
