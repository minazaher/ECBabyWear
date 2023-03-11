package com.example.ecbabywear;

import static com.example.ecbabywear.ApplicationClass.cart;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecbabywear.Model.CartItem;
import com.example.ecbabywear.Model.Order;
import com.example.ecbabywear.Model.Piece;
import com.example.ecbabywear.UI.Cart;

import java.util.ArrayList;
import java.util.Date;

public class OrderHistoryFragment extends Fragment {

    View v;
    private ArrayList<Order> orders;
    private ArrayList<CartItem> cartItems;


    public OrderHistoryFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orders = new ArrayList<>();
        cartItems = new ArrayList<>();

        Piece piece1 = new Piece("Girl Winter Dress", "https://m.media-amazon.com/images/I/41P59oy-MjL._AC_.jpg", "150.0", "Full Body Suit", "Papillon Basic Snap Closure Bodysuit for Kids - White, 9 Months", "Pending")         ;

        cartItems.add(new CartItem(piece1, 5));
        cartItems.add(new CartItem(piece1, 5));
        cartItems.add(new CartItem(piece1, 5));
        cartItems.add(new CartItem(piece1, 5));

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_order_history, container, false);
        RecyclerView ordersRecyclerView = (RecyclerView) v.findViewById(R.id.orders_recylerview);
//        orders.add(new Order("12",  cartItems, new Date(), "158 L.E"));
//        orders.add(new Order("12",  cartItems, new Date(), "158 L.E"));
//        orders.add(new Order("12",  cartItems, new Date(), "158 L.E"));
//        orders.add(new Order("12",  cartItems, new Date(), "158 L.E"));
        OrdersAdapter ordersAdapter = new OrdersAdapter(Cart.getAllOrders() , getContext());
        ordersRecyclerView.setAdapter(ordersAdapter);
        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        return v;
    }
}