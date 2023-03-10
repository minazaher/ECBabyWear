package com.example.ecbabywear.UI.HomePage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecbabywear.R;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.viewholder> {
    Context context;

    public CategoriesAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public CategoriesAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new CategoriesAdapter.viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.viewholder holder, int position) {
        holder.Name.setText("Suit");
        holder.Image.setImageResource(R.drawable.category);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView Name;
        ImageView Image;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.cat_name);
            Image = itemView.findViewById(R.id.cat_img);

        }
    }
}
