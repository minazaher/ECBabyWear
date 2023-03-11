package com.example.ecbabywear.UI;

import static com.example.ecbabywear.ApplicationClass.cart;
import static com.example.ecbabywear.ApplicationClass.restartActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ecbabywear.ApplicationClass;
import com.example.ecbabywear.CartAdapter;
import com.example.ecbabywear.Model.CartItem;
import com.example.ecbabywear.Model.Order;
import com.example.ecbabywear.R;
import com.example.ecbabywear.UI.HomePage.CategoriesAdapter;
import com.example.ecbabywear.UI.HomePage.HomePage;
import com.example.ecbabywear.databinding.ActivityCartBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Cart extends AppCompatActivity {
    ActivityCartBinding activityCartBinding;
    Double ItemPrice  , TotalPriceBeforeTaxes = 0.0, Taxes , DeliveryServices = 20.0, Total = 0.0;
    DecimalFormat df = new DecimalFormat("0.00");
    static FirebaseFirestore firebaseFirestore;
    static FirebaseAuth firebaseAuth;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityCartBinding = ActivityCartBinding.inflate(getLayoutInflater());
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();


        setContentView(activityCartBinding.getRoot());
        if (cart.size() == 0) {
            activityCartBinding.emptyLayout.setVisibility(View.VISIBLE);
            activityCartBinding.cartLayout.setVisibility(View.GONE);
        }
        else{
            activityCartBinding.emptyLayout.setVisibility(View.GONE);
            activityCartBinding.cartLayout.setVisibility(View.VISIBLE);
        }

        RecyclerView.Adapter adapter = new CartAdapter(getApplicationContext(), cart, 1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        activityCartBinding.cartRecview.setAdapter(adapter);
        activityCartBinding.cartRecview.setLayoutManager(linearLayoutManager);

        for (CartItem c:cart) {
            ItemPrice = Double.parseDouble(c.getPrice()) * c.getItemQuantity();
            TotalPriceBeforeTaxes += ItemPrice;
        }

        Taxes = Double.valueOf(df.format(0.14 * TotalPriceBeforeTaxes));
        activityCartBinding.itemsTotalPrice.setText(TotalPriceBeforeTaxes.toString() + " L.E");
        Total = TotalPriceBeforeTaxes + Taxes + DeliveryServices;
        activityCartBinding.TotalPrice.setText(Total.toString() + " L.E");
        activityCartBinding.Tax.setText(Taxes.toString() + " L.E");


        activityCartBinding.btnCheckout.setOnClickListener(view -> {
            Order order = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                order = new Order("ord1", cart, new Date() , Total.toString());
            }
            addOrderToDatabase(order);
            startActivity(new Intent(getApplicationContext(), HomePage.class));
            cart.clear();
        });
        getAllOrders();
        activityCartBinding.btnClear.setOnClickListener(view ->
                Snackbar.make(findViewById(R.id.activity_cart), "The Cart Items Will be Deleted", Snackbar.LENGTH_LONG)
                .setAction("Clear", view1 -> {
                    cart.clear();
                    restartActivity(Cart.this);
                }).setBackgroundTint(getColor(R.color.pink_300)).setTextColor(Color.WHITE).setActionTextColor(Color.WHITE).show());
    }


    public void addOrderToDatabase(Order order){
        Map<String, Object> myOrder = new HashMap<>();
        DocumentReference documentReference = firebaseFirestore.collection("Orders").document();
        myOrder.put("orderID", firebaseAuth.getCurrentUser().getUid());
        myOrder.put("items", order.getItems());
        myOrder.put("orderDate",  FieldValue.serverTimestamp());
        myOrder.put("totalPrice", order.getTotalPrice());

        documentReference.set(myOrder).
                addOnSuccessListener(unused ->
                Toast.makeText(Cart.this, "Order Done!", Toast.LENGTH_SHORT).show()).
                addOnFailureListener(e ->
                Toast.makeText(Cart.this, "Fuck OFF!", Toast.LENGTH_SHORT).show());
    }

    public static ArrayList<Order> getAllOrders(){
        ArrayList<Order> orders = new ArrayList<>();
        firebaseFirestore.collection("Orders")
                .whereEqualTo("orderID",firebaseAuth.getCurrentUser().getUid() )
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            System.out.println("Order : ****** " +  document.get("items"));
                            orders.add(document.toObject(Order.class));
                        }
                    } else {
                        System.out.println("Error getting documents: " + task.getException());
                    }
                });
        return orders;
    }

}