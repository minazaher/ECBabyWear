package com.example.ecbabywear.UI;

import static com.example.ecbabywear.ApplicationClass.cart;
import static com.example.ecbabywear.ApplicationClass.firebaseAuth;
import static com.example.ecbabywear.ApplicationClass.navigateToActivity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ecbabywear.Model.CartItem;
import com.example.ecbabywear.Model.Order;
import com.example.ecbabywear.UI.HomePage.HomePage;
import com.example.ecbabywear.Repositories.OrderRepository;
import com.example.ecbabywear.Utilis.CartAdapter;
import com.example.ecbabywear.databinding.ActivityCartBinding;
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

public class Cart extends AppCompatActivity {
    ActivityCartBinding activityCartBinding;
    Double ItemPrice  , TotalPriceBeforeTaxes = 0.0, Tax , DeliveryServices = 20.0, Total = 0.0;
    DecimalFormat df = new DecimalFormat("0.00");
    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
    FirebaseUser user;
    OrderRepository orderRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCartBinding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(activityCartBinding.getRoot());
        orderRepository = new OrderRepository();
        user = firebaseAuth.getCurrentUser();

        showCart();
        initializeCartRecyclerView();
        Total = calculateTotalPrice();
        showOrderInfo();

        activityCartBinding.btnCheckout
                .setOnClickListener(view -> {
                    checkout();
                });

    }
    private void checkout(){
        String date = dateFormat.format(new Date());
        Order order = new Order(user.getUid(), cart,date, Total.toString(), "Completed");
        orderRepository.addOrderToDatabase(order);
        navigateToActivity(Cart.this, HomePage.class);
    }
    private void showOrderInfo(){
        activityCartBinding.itemsTotalPrice.setText(TotalPriceBeforeTaxes.toString() + " L.E");
        activityCartBinding.TotalPrice.setText(Total.toString() + " L.E");
        activityCartBinding.Tax.setText(Tax.toString() + " L.E");

    }

    private double calculateTotalPrice(){
        double price;
        price = calculateTotalPriceBeforeTax() + calculateTaxes() + DeliveryServices;
        return price;
    }

    private double calculateTaxes(){
        Tax = Double.valueOf(df.format(0.14 * TotalPriceBeforeTaxes));
        return Tax;
    }

    private double calculateTotalPriceBeforeTax(){
        for (CartItem c:cart) {
            ItemPrice = Double.parseDouble(c.getPrice()) * c.getItemQuantity();
            TotalPriceBeforeTaxes += ItemPrice;
        }
        return TotalPriceBeforeTaxes;
    }


    private void initializeCartRecyclerView(){
        CartAdapter adapter = new CartAdapter(getApplicationContext(), cart, 1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        activityCartBinding.cartRecview.setAdapter(adapter);
        activityCartBinding.cartRecview.setLayoutManager(linearLayoutManager);
    }

    private void showCartIsEmptyMessage(){
        activityCartBinding.emptyLayout.setVisibility(View.VISIBLE);
        activityCartBinding.cartLayout.setVisibility(View.GONE);
    }

    private void showCartItems(){
        activityCartBinding.emptyLayout.setVisibility(View.GONE);
        activityCartBinding.cartLayout.setVisibility(View.VISIBLE);
    }


    private void showCart(){
        if (cart.isEmpty()) {
            showCartIsEmptyMessage();
        } else {
            showCartItems();
        }
    }

}