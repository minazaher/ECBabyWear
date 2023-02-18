package com.example.ecbabywear;

import static com.google.android.material.color.MaterialColors.getColor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.mViewHolder> {
    private Context context;
    private ArrayList<Piece> pieceArrayList;
    private int contextBit;

    public StoreAdapter(Context context, ArrayList<Piece> pieceArrayList, int contextBit) {
        this.context = context;
        this.pieceArrayList = pieceArrayList;
        this.contextBit = contextBit;
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new mViewHolder(inflate);    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {

        holder.tv_Name.setText(pieceArrayList.get(position).getName().toString());
        holder.tv_ShortDescription.setText(pieceArrayList.get(position).getShortDescription().toString());
        holder.tv_Stat.setText(pieceArrayList.get(position).getStatus().toString());
        Glide.with(context).asBitmap().load(pieceArrayList.get(position).getURL()).into(holder.img);

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

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ItemDetails.class);
            ApplicationClass.currentPiece = pieceArrayList.get(holder.getAdapterPosition());
            holder.img.getContext().startActivity(intent);
        });
        if (holder.getAdapterPosition() % 2 != 0)
            holder.tv_Stat.setTextColor(Color.parseColor("#00FFFF"));
        else
            holder.tv_Stat.setTextColor(Color.RED);
    }

    @Override
    public int getItemCount() {
        return pieceArrayList.size();
    }

    public class mViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView tv_Name, tv_Stat, tv_ShortDescription;
        LinearLayout cart;
        @SuppressLint("ResourceAsColor")
        public mViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_cart);
            tv_Name = itemView.findViewById(R.id.tv_cart_itemName);
            tv_Stat = itemView.findViewById(R.id.tv_request_itemStat);
            tv_ShortDescription = itemView.findViewById(R.id.tv_cart_itemDes);
            cart = itemView.findViewById(R.id.cart_linearlayout);
        }
    }
}
