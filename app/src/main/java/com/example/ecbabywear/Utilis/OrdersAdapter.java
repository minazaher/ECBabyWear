package com.example.ecbabywear.Utilis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecbabywear.Model.Order;
import com.example.ecbabywear.R;

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
        Glide.with(context).asBitmap().load(orders.get(position).getItems().get(0).getURL()).into(holder.Image);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class mViewHolder extends RecyclerView.ViewHolder{
        TextView Name, Date,Price;
        ImageView Image;
        public mViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.tv_brand);
            Date = itemView.findViewById(R.id.tv_orderDate);
            Price = itemView.findViewById(R.id.tv_orderPrice);
            Image = itemView.findViewById(R.id.img_order);
        }
    }
}
