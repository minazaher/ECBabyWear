package com.example.ecbabywear;

import static com.google.android.material.color.MaterialColors.getColor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecbabywear.Model.CartItem;
import com.example.ecbabywear.Model.Piece;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.mViewHolder> {
    private Context context;
    private ArrayList<CartItem> CartPieces;
    private int contextBit;


    public CartAdapter(Context context, ArrayList<CartItem> cartPieces, int contextBit) {
        this.context = context;
        CartPieces = cartPieces;
        this.contextBit = contextBit;
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new mViewHolder(inflate);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {
        Double price = Double.parseDouble(CartPieces.get(position).getPrice()) * CartPieces.get(position).getItemQuantity();
        holder.tv_Name.setText(CartPieces.get(position).getName().toString());
        holder.tv_ShortDescription.setText(CartPieces.get(position).getShortDescription().toString());
        holder.tv_Stat.setText(CartPieces.get(position).getStatus().toString());
        holder.tv_cart_itemPrice.setText(price.toString() + " L.E");
        holder.tv_item_quantity.setText(String.valueOf(CartPieces.get(position).getItemQuantity()));
        Glide.with(context).asBitmap().load(CartPieces.get(position).getURL()).into(holder.img);

        if ( this.contextBit == 1)
        {
            holder.tv_Stat.setVisibility(View.GONE);
            holder.cart.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.tv_Stat.setVisibility(View.VISIBLE);
            holder.cart.setVisibility(View.GONE);
        }

        if (holder.getAdapterPosition() % 2 != 0)
            holder.tv_Stat.setTextColor(Color.RED);
        else
            holder.tv_Stat.setTextColor(Color.RED);
    }

    @Override
    public int getItemCount() {
        return CartPieces.size();
    }

    public class mViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView tv_Name, tv_Stat, tv_ShortDescription, tv_item_quantity, tv_cart_itemPrice;
        LinearLayout cart;
        @SuppressLint("ResourceAsColor")
        public mViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_cart);
            tv_Name = itemView.findViewById(R.id.tv_cart_itemName);
            tv_Stat = itemView.findViewById(R.id.tv_request_itemStat);
            tv_cart_itemPrice = itemView.findViewById(R.id.tv_cart_itemPrice);
            tv_ShortDescription = itemView.findViewById(R.id.tv_cart_itemDes);
            tv_item_quantity = itemView.findViewById(R.id.cat_item_quantity);
            cart = itemView.findViewById(R.id.cart_linearlayout);
        }
    }
}
