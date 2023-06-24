package com.example.ecbabywear.UI.OrderHistory;


import static com.example.ecbabywear.ApplicationClass.firebaseAuth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecbabywear.ApplicationClass;
import com.example.ecbabywear.Model.CartItem;
import com.example.ecbabywear.Model.Order;
import com.example.ecbabywear.Model.User;
import com.example.ecbabywear.R;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderHistoryFragment extends Fragment {
    private OrderRepository orderRepository;
    private ArrayList<Order> confirmedOrders;

    public OrderHistoryFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderRepository = new OrderRepository();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_history, container, false);


        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        orderRepository.getConfirmedOrdersByUser(firebaseUser, orders -> {
            RecyclerView ordersRecyclerView = (RecyclerView) view.findViewById(R.id.orders_recylerview);
            confirmedOrders = (ArrayList<Order>) orders;
            OrdersAdapter adapter = new OrdersAdapter(confirmedOrders, this.getContext());
            ordersRecyclerView.setAdapter(adapter);
            ordersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        });

        return view;
    }
}
