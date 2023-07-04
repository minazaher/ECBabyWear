package com.example.ecbabywear.Utilis;

import static com.example.ecbabywear.ApplicationClass.cart;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecbabywear.Model.Order;
import com.example.ecbabywear.R;
import com.example.ecbabywear.UI.Cart;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.mViewHolder> {
    ArrayList<Order> orders = new ArrayList<>();
    Context context;


    public OrdersAdapter(ArrayList<Order> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_item, parent, false);
        return new mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {
        holder.Name.setText(orders.get(position).getItems().get(0).getName());
        holder.Date.setText(orders.get(position).getOrderDate().toString());
        holder.Price.setText("$" +orders.get(position).getTotalPrice());
        if (orders.get(position).getItems().size() == 1) {
            holder.orderCount.setText(orders.get(position).getItems().size() + " item");
        } else {
            holder.orderCount.setText(orders.get(position).getItems().size() + " items");
        }
        Glide.with(context).asBitmap().load(orders.get(position).getItems().get(0).getURL()).into(holder.Image);
        holder.Reorder.setOnClickListener(view -> {
            Intent intent = new Intent(context, Cart.class);
            cart.clear();
            cart.addAll(orders.get(position).getItems());
            holder.Name.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class mViewHolder extends RecyclerView.ViewHolder{
        TextView Name, Date,Price, orderCount;
        ImageView Image;
        AppCompatButton Reorder;
        public mViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.tv_brand);
            Date = itemView.findViewById(R.id.tv_orderDate);
            Price = itemView.findViewById(R.id.tv_orderPrice);
            Reorder = itemView.findViewById(R.id.btn_reorder);
            Image = itemView.findViewById(R.id.img_order);
            orderCount = itemView.findViewById(R.id.tv_orderCount);
        }
    }
}
