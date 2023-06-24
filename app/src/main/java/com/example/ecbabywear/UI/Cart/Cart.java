package com.example.ecbabywear.UI.Cart;

import static com.example.ecbabywear.ApplicationClass.cart;
import static com.example.ecbabywear.ApplicationClass.firebaseAuth;
import static com.example.ecbabywear.ApplicationClass.firebaseFirestore;
import static com.example.ecbabywear.ApplicationClass.restartActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ecbabywear.Model.CartItem;
import com.example.ecbabywear.Model.Order;
import com.example.ecbabywear.R;
import com.example.ecbabywear.UI.HomePage.HomePage;
import com.example.ecbabywear.UI.OrderHistory.OrderRepository;
import com.example.ecbabywear.databinding.ActivityCartBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Cart extends AppCompatActivity {
    ActivityCartBinding activityCartBinding;
    Double ItemPrice  , TotalPriceBeforeTaxes = 0.0, Taxes , DeliveryServices = 20.0, Total = 0.0;
    DecimalFormat df = new DecimalFormat("0.00");
    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
    FirebaseUser user;
    OrderRepository orderRepository;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCartBinding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(activityCartBinding.getRoot());
        orderRepository = new OrderRepository();
        user = firebaseAuth.getCurrentUser();


        if (cart.size() == 0) {
            activityCartBinding.emptyLayout.setVisibility(View.VISIBLE);
            activityCartBinding.cartLayout.setVisibility(View.GONE);
        }
        else{
            activityCartBinding.emptyLayout.setVisibility(View.GONE);
            activityCartBinding.cartLayout.setVisibility(View.VISIBLE);
        }

        CartAdapter adapter = new CartAdapter(getApplicationContext(), cart, 1);
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
                String date = dateFormat.format(new Date());
                order = new Order("ord1", cart, date , Total.toString(), "Completed");
            }
            assert order != null;
            orderRepository.addOrderToDatabase(order,user);
            startActivity(new Intent(getApplicationContext(), HomePage.class));
        });

        activityCartBinding.btnClear.setOnClickListener(view ->
                Snackbar.make(findViewById(R.id.activity_cart), "The Cart Items Will be Deleted", Snackbar.LENGTH_LONG)
                        .setAction("Clear", view1 -> {
                            cart.clear();
                            restartActivity(Cart.this);
                        }).setBackgroundTint(getColor(R.color.pink_300)).setTextColor(Color.WHITE).setActionTextColor(Color.WHITE).show());
    }




}