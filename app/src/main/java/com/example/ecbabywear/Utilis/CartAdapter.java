package com.example.ecbabywear.Utilis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecbabywear.Model.CartItem;
import com.example.ecbabywear.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.mViewHolder> {
    private Context context;
    private ArrayList<CartItem> CartPieces;
    private int contextBit;

    private OnDataChangedListener onDataChangeListener;

    public void setOnDataChangeListener(OnDataChangedListener listener) {
        this.onDataChangeListener = listener;
    }

    public CartAdapter(Context context, ArrayList<CartItem> cartPieces) {
        this.context = context;
        CartPieces = cartPieces;
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new mViewHolder(inflate);
    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {
        double price = Double.parseDouble(CartPieces.get(position).getPrice()) * CartPieces.get(position).getItemQuantity();
        holder.tv_Name.setText(CartPieces.get(position).getName().toString());
        holder.tv_ShortDescription.setText(CartPieces.get(position).getShortDescription().toString());
        holder.tv_cart_itemPrice.setText(CartPieces.get(position).getItemPrice().toString());
        holder.counter.setText(String.valueOf(CartPieces.get(position).getItemQuantity()));
        Glide.with(context).asBitmap().load(CartPieces.get(position).getURL()).into(holder.img);

        holder.add.setOnClickListener(view -> {
            CartPieces.get(position).increaseItemQuantity();
            holder.counter.setText(String.valueOf(CartPieces.get(position).getItemQuantity()));
            holder.tv_cart_itemPrice.setText(CartPieces.get(position).getItemPrice().toString());
            this.onDataChangeListener.onDataChanged();

        });

        holder.remove.setOnClickListener(view -> {
            CartPieces.get(position).decreaseItemQuantity();
            holder.counter.setText(String.valueOf(CartPieces.get(position).getItemQuantity()));
            holder.tv_cart_itemPrice.setText(CartPieces.get(position).getItemPrice().toString());
            this.onDataChangeListener.onDataChanged();

        });


    }

    @Override
    public int getItemCount() {
        return CartPieces.size();
    }

    public class mViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView tv_Name, tv_Stat, tv_ShortDescription, tv_item_quantity, tv_cart_itemPrice, counter;
        LinearLayout cart;
        AppCompatButton add, remove;
        @SuppressLint("ResourceAsColor")
        public mViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_cart);
            tv_Name = itemView.findViewById(R.id.tv_cart_itemName);
            tv_cart_itemPrice = itemView.findViewById(R.id.tv_cart_itemPrice);
            tv_ShortDescription = itemView.findViewById(R.id.tv_cart_itemDes);
            cart = itemView.findViewById(R.id.cart_layout);
            add = itemView.findViewById(R.id.btn_plus_cart);
            remove = itemView.findViewById(R.id.btn_minus_cart);
            counter = itemView.findViewById(R.id.cart_item_quantity);


        }
    }
}
