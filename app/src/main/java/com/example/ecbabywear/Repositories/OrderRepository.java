package com.example.ecbabywear.Repositories;

import static com.example.ecbabywear.ApplicationClass.databaseReference;
import static com.example.ecbabywear.ApplicationClass.firebaseFirestore;

import androidx.lifecycle.MutableLiveData;

import com.example.ecbabywear.Model.Order;
import com.example.ecbabywear.Piece;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class OrderRepository {
    MutableLiveData<List<Piece>> ordersListMutableLiveData;
    MutableLiveData<Piece> orderMutableLiveData;

    public OrderRepository() {
        this.orderMutableLiveData = new MutableLiveData<>();
        this.ordersListMutableLiveData = new MutableLiveData<>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Orders");
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        firebaseFirestore.setFirestoreSettings(settings);
    }

    public void addOrderToDatabase(Order order){
        Map<String, Object> myOrder = new HashMap<>();
        DocumentReference documentReference = firebaseFirestore.collection("Orders").document();
        myOrder.put("orderID", order.getOrderID());
        myOrder.put("items", order.getItems());
        myOrder.put("orderDate", order.getOrderDate());
        myOrder.put("totalPrice", order.getTotalPrice());
        myOrder.put("Status", order.getStatus());

        documentReference.set(myOrder);


    }

    public List<Order> getConfirmedOrdersByUser(FirebaseUser user, OnOrdersRetrievedListener listener){
        List<Order> confirmedOrders = new ArrayList<>();
        firebaseFirestore.collection("Orders")
                .whereEqualTo("orderID", user.getUid())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            if (Objects.equals(document.get("Status"), "Completed"))
                                confirmedOrders.add(document.toObject(Order.class));
                        }
                        listener.onOrdersRetrieved(confirmedOrders);
                        System.out.println("Confirmed : "+ confirmedOrders);
                    } else {
                        System.out.println("Error getting documents: " + task.getException());
                    }
                });
        return confirmedOrders;
    }


    public void getCancelledOrdersByUser(FirebaseUser user, OnOrdersRetrievedListener listener) {
        List<Order> cancelledOrders = new ArrayList<>();

        firebaseFirestore.collection("Orders")
                .whereEqualTo("orderID", user.getUid())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            if (Objects.equals(document.get("Status"), "Cancelled"))
                                cancelledOrders.add(document.toObject(Order.class));
                            System.out.println("Canclled: " + cancelledOrders);
                        }
                        listener.onOrdersRetrieved(cancelledOrders);
                    } else {
                        System.out.println("Error getting documents: " + task.getException());
                    }
                });
    }

    public interface OnOrdersRetrievedListener {
        void onOrdersRetrieved(List<Order> orders);
    }

}
