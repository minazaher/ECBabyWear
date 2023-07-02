package com.example.ecbabywear.Model.Utilis;

import static com.example.ecbabywear.ApplicationClass.currentPiece;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecbabywear.Piece;
import com.example.ecbabywear.UI.ItemDetails;
import com.example.ecbabywear.databinding.WishlistItemBinding;

import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.viewholder> {

    List<Piece> wishlist;

    public WishlistAdapter(List<Piece> wishlist) {
        this.wishlist = wishlist;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        WishlistItemBinding itemBinding = WishlistItemBinding.inflate(layoutInflater, parent, false);
        return new viewholder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Piece piece = wishlist.get(position);
        currentPiece = wishlist.get(holder.getAdapterPosition());
        holder.itemBinding.setPiece(piece);
        holder.itemBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return wishlist.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        WishlistItemBinding itemBinding;
        public viewholder(@NonNull WishlistItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
            itemBinding.wishlistImg.setOnClickListener(view -> {
                System.out.println(currentPiece.getLongDescription());
                Intent intent = new Intent(view.getContext(), ItemDetails.class);
                view.getContext().startActivity(intent);
            });
        }


    }
}