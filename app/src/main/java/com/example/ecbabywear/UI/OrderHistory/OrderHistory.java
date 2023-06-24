package com.example.ecbabywear.UI.OrderHistory;

import static com.example.ecbabywear.ApplicationClass.firebaseAuth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Parcelable;

import com.example.ecbabywear.ApplicationClass;
import com.example.ecbabywear.Model.Order;
import com.example.ecbabywear.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderHistory extends AppCompatActivity {
    private TabLayout tabLayout;
    private FragmentAdapter fragmentAdapter;
    private ViewPager viewPager;
    OrderRepository orderRepository;
    OrderHistoryFragment completedOrdersFragment;
    ArrayList<Order> confirmedOrders, cancelledOrders;
    CanceledOrderHistoryFragment canceledOrderHistoryFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        orderRepository=  new OrderRepository();

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        completedOrdersFragment = new OrderHistoryFragment();
        canceledOrderHistoryFragment = new CanceledOrderHistoryFragment();

        fragmentAdapter.addFragment(completedOrdersFragment, "Completed");
        fragmentAdapter.addFragment(canceledOrderHistoryFragment, "Cancelled");

        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}