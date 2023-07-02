package com.example.ecbabywear.UI;

import static com.example.ecbabywear.ApplicationClass.cart;
import static com.example.ecbabywear.ApplicationClass.firebaseAuth;
import static com.example.ecbabywear.ApplicationClass.navigateToActivity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecbabywear.Model.CartItem;
import com.example.ecbabywear.Model.Order;
import com.example.ecbabywear.Repositories.OrderRepository;
import com.example.ecbabywear.UI.HomePage.HomePage;
import com.example.ecbabywear.Utilis.CartAdapter;
import com.example.ecbabywear.Utilis.OnDataChangedListener;
import com.example.ecbabywear.databinding.ActivityCartBinding;
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

public class Cart extends AppCompatActivity implements OnDataChangedListener {
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
        activityCartBinding.toolbar.textView11.setText("My Cart");

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
        cart.clear();
        navigateToActivity(Cart.this, HomePage.class);

    }
    private void showOrderInfo(){
        activityCartBinding.itemsTotalPrice.setText(TotalPriceBeforeTaxes.toString() + " L.E");
        activityCartBinding.TotalPrice.setText(Total.toString() + " L.E");
        activityCartBinding.Tax.setText(Tax.toString() + " L.E");

    }

    private double calculateTotalPrice(){
        double price = 0.0;
        price = calculateTotalPriceBeforeTax() + calculateTaxes() + DeliveryServices;
        return price;
    }

    private double calculateTaxes(){
        Tax = 0.0;
        Tax = Double.valueOf(df.format(0.14 * TotalPriceBeforeTaxes));
        return Tax;
    }

    private double calculateTotalPriceBeforeTax(){
        TotalPriceBeforeTaxes  = 0.0;
        for (CartItem c:cart) {
            ItemPrice = Double.parseDouble(c.getPrice()) * c.getItemQuantity();
            TotalPriceBeforeTaxes += ItemPrice;
        }
        return TotalPriceBeforeTaxes;
    }

    private void initializeCartRecyclerView() {
        CartAdapter adapter = new CartAdapter(getApplicationContext(), cart);
        adapter.setOnDataChangeListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        activityCartBinding.cartRecview.setAdapter(adapter);
        activityCartBinding.cartRecview.setLayoutManager(linearLayoutManager);
        initializeItemHelper(adapter);
    }

    private void initializeItemHelper(CartAdapter adapter) {

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP |ItemTouchHelper.DOWN) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                cart.remove(position);
                Total = calculateTotalPrice();
                showOrderInfo();
                adapter.notifyItemRemoved(position);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(activityCartBinding.cartRecview);

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

    @Override
    public void onDataChanged() {
        Total = calculateTotalPrice();
        showOrderInfo();
    }
}