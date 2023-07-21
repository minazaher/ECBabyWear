package com.example.ecbabywear.Repositories;

import static com.example.ecbabywear.ApplicationClass.databaseReference;
import static com.example.ecbabywear.ApplicationClass.firebaseFirestore;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ecbabywear.Model.Order;
import com.example.ecbabywear.Piece;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class OrderRepository {

    private FirebaseFirestore db;
    private String userId;

    public OrderRepository(String userId) {
        this.userId = userId;
        this.db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
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

    public LiveData<List<Order>> getUserOrdersByStatus(String Status){
        MutableLiveData<List<Order>> orders = new MutableLiveData<>();
        firebaseFirestore.collection("Orders")
                .whereEqualTo("orderID", userId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Order> ordersList = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            if (Objects.equals(document.get("Status"), Status)){
                                ordersList.add(document.toObject(Order.class));
                            }
                        }
                        System.out.println("The third order is :" + ordersList.get(2));
                        orders.setValue(ordersList);
                    } else {
                        System.out.println("Error getting documents: " + task.getException());
                    }
                });
        return orders;
    }


}
