package com.example.ecbabywear.UI;

import static com.example.ecbabywear.ApplicationClass.cart;
import static com.example.ecbabywear.ApplicationClass.restartActivity;

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
import android.widget.Toast;

import com.example.ecbabywear.ApplicationClass;
import com.example.ecbabywear.CartAdapter;
import com.example.ecbabywear.Model.CartItem;
import com.example.ecbabywear.Model.Order;
import com.example.ecbabywear.R;
import com.example.ecbabywear.UI.HomePage.CategoriesAdapter;
import com.example.ecbabywear.UI.HomePage.HomePage;
import com.example.ecbabywear.databinding.ActivityCartBinding;
import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;

public class Cart extends AppCompatActivity {
    ActivityCartBinding activityCartBinding;
    Double ItemPrice  , TotalPriceBeforeTaxes = 0.0, Taxes , DeliveryServices = 20.0, Total = 0.0;
    DecimalFormat df = new DecimalFormat("0.00");

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCartBinding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(activityCartBinding.getRoot());

        RecyclerView.Adapter adapter = new CartAdapter(getApplicationContext(), cart, 1);

        activityCartBinding.cartRecview.setAdapter(adapter);
        activityCartBinding.cartRecview.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

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
            Order order = new Order("ord1", cart, java.time.LocalDate.now(), Total.toString());
            Toast.makeText(getApplicationContext(), "Checkout Done Sucessfully at "+ order.getOrderDate()  , Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), HomePage.class));
            cart.clear();
        });


        activityCartBinding.btnClear.setOnClickListener(view ->
                Snackbar.make(findViewById(R.id.activity_cart), "The Cart Items Will be Deleted", Snackbar.LENGTH_LONG)
                .setAction("Clear", view1 -> {
                    cart.clear();
                    restartActivity(Cart.this);
                }).setBackgroundTint(getColor(R.color.pink_300)).setTextColor(Color.WHITE).setActionTextColor(Color.WHITE).show());
    }

}