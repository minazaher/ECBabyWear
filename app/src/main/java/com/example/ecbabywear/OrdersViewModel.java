package com.example.ecbabywear;

import static com.example.ecbabywear.ApplicationClass.firebaseAuth;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ecbabywear.Model.Order;
import com.example.ecbabywear.Repositories.OrderRepository;
import com.example.ecbabywear.Repositories.WishlistRepository;

import java.util.List;

public class OrdersViewModel extends ViewModel {

    private OrderRepository orderRepository;
    private LiveData<List<Order>> orders;
    private String userId;

    public OrdersViewModel() {
    }


    public LiveData<List<Order>> getOrdersByStatus(String userId,String Status) {
        orderRepository = new OrderRepository(userId);
        return orderRepository.getUserOrdersByStatus(Status);
    }

}
