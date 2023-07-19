package com.example.ecbabywear.Utilis;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecbabywear.Model.Category;
import com.example.ecbabywear.R;
import com.example.ecbabywear.UI.CategoryDetails.CategoryProducts;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.viewholder> {
    Context context;
    List<Category> categories;

    public CategoriesAdapter(List<Category> categories,Context context) {
        this.categories = categories;
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
        holder.Name.setText(categories.get(position).getName());
        Glide.with(context).asBitmap().load(categories.get(position).getURL()).into(holder.Image);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, CategoryProducts.class);
            intent.putExtra("tabIndex", position);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
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
