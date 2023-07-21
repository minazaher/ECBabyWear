package com.example.ecbabywear.UI.OrderHistory;

import static com.example.ecbabywear.ApplicationClass.firebaseAuth;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecbabywear.Model.Order;
import com.example.ecbabywear.OrdersViewModel;
import com.example.ecbabywear.R;
import com.example.ecbabywear.Repositories.PiecesRepository;
import com.example.ecbabywear.UI.CategoryDetails.CategoryFragment;
import com.example.ecbabywear.Utilis.OrdersAdapter;
import com.example.ecbabywear.Utilis.StoreAdapter;
import com.example.ecbabywear.Utilis.WishlistAdapter;
import com.example.ecbabywear.databinding.FragmentCategoryBinding;
import com.example.ecbabywear.databinding.FragmentOrderHistory2Binding;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderHistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderHistoryFragment extends Fragment {

    private static final String TABNAME = "Tab";
    OrdersViewModel ordersViewModel;
    FragmentOrderHistory2Binding fragment;
    String tabName;
    OrdersAdapter ordersAdapter;
    RecyclerView ordersRecyclerView;


    public OrderHistoryFragment() {
    }

    public static OrderHistoryFragment newInstance(String tabName) {
        OrderHistoryFragment fragment = new OrderHistoryFragment();
        Bundle args = new Bundle();
        args.putString(TABNAME, tabName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ordersViewModel = new ViewModelProvider(this).get(OrdersViewModel.class);
        if (getArguments() != null) {
            tabName = getArguments().getString(TABNAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment = FragmentOrderHistory2Binding.inflate(getLayoutInflater(), container, false);
        getOrders(tabName);
        return fragment.getRoot();
    }

    private void getOrders(String Status){
        System.out.println(Status + " the order status is" );
        ordersViewModel.getOrdersByStatus(firebaseAuth.getCurrentUser().getUid(), Status)
                .observe(this.getViewLifecycleOwner(), this::initializeOrdersRecyclerView);
    }

    private void initializeOrdersRecyclerView(List<Order> orders) {
        ordersRecyclerView = fragment.ordersRecylerview;
        System.out.println("The Cancelled orders are: "  + orders.toString());
        ordersRecyclerView.setNestedScrollingEnabled(false);
        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false));
        ordersAdapter = new OrdersAdapter((ArrayList<Order>) orders, getContext());
        ordersRecyclerView.setAdapter(ordersAdapter);
    }
}