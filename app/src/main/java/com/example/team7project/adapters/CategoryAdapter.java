package com.example.team7project.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.team7project.Browse;
import com.example.team7project.BrowseList;
import com.example.team7project.CreateMediaList;
import com.example.team7project.Home;
import com.example.team7project.R;
import com.example.team7project.entities.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context context;
    private List<Category> categories;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryAdapter.CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.view_media_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.bindTo(category);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Category category;
        private ImageView imageview;
        private TextView categoryTextView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            imageview = itemView.findViewById(R.id.categoryImage);
            categoryTextView = itemView.findViewById(R.id.categoryName);
            itemView.setOnClickListener(this);
        }

        public void bindTo(Category category) {
            this.category = category;
            categoryTextView.setText(category.getName());
            int catImgId = context.getResources().getIdentifier(category.getImg(), "drawable", context.getPackageName());
            Glide.with(context).load(catImgId).into(imageview);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, BrowseList.class);
            intent.putExtra("categoryId", category.getId());
            intent.putExtra("categoryName", category.getName());
            context.startActivity(intent);
        }
    }
}
