package com.example.ecbabywear.UI.HomePage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecbabywear.ApplicationClass;
import com.example.ecbabywear.Model.Piece;
import com.example.ecbabywear.R;
import com.example.ecbabywear.UI.ItemDetails;

import java.util.ArrayList;
import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.viewholder> {
    private Context context;
    private List<Piece> pieceArrayList;
    public StoreAdapter(Context context, List<Piece> pieceArrayList) {
        this.context = context;
        this.pieceArrayList = pieceArrayList;
    }

    public StoreAdapter(Context context) {
        this.context = context;
    }

    public List<Piece> getPieceArrayList() {
        return pieceArrayList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_item, parent, false);
        return new StoreAdapter.viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.tv_Name.setText(pieceArrayList.get(position).getName());
        holder.tv_ShortDescription.setText(pieceArrayList.get(position).getShortDescription());
        holder.tv_Price.setText(pieceArrayList.get(position).getPrice());
        Glide.with(context).asBitmap().load(pieceArrayList.get(position).getURL()).into(holder.img);
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ItemDetails.class);
            ApplicationClass.currentPiece = pieceArrayList.get(holder.getAdapterPosition());
            holder.img.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return pieceArrayList.size();
    }


    public void updatePiecesList(final List<Piece> userArrayList) {
        this.pieceArrayList.clear();
        this.pieceArrayList = userArrayList;
        notifyDataSetChanged();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView tv_Name, tv_ShortDescription, tv_Price;
        LinearLayout cart;

        public viewholder(@NonNull View itemView) {
            super(itemView);
                img = itemView.findViewById(R.id.store_img);
                tv_Name = itemView.findViewById(R.id.tv_store_itemName);
                tv_Price = itemView.findViewById(R.id.tv_store_itemPrice);
                tv_ShortDescription = itemView.findViewById(R.id.tv_store_itemDes);
        }
    }
}
