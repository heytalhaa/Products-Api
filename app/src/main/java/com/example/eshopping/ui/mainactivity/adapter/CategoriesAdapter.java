package com.example.eshopping.ui.mainactivity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eshopping.R;
import com.example.eshopping.data.model.Category;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {
    private Context context;
    private List<Category> categoryList;
    private OnCategorySelect listener;

    public CategoriesAdapter (Context context, List<Category> categoryList){
        this.categoryList = categoryList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.categories_items, parent, false);
        return (new MyViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category data = categoryList.get(position);
        holder.categoryTV.setText(data.getName());

        holder.categoryCV.setOnClickListener(view -> {
            listener.onSelectedCategory(categoryList.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public void onCategorySelect(OnCategorySelect lister){
        this.listener = lister;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        MaterialCardView categoryCV;
        TextView categoryTV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTV = itemView.findViewById(R.id.categoryItem);
            categoryCV = itemView.findViewById(R.id.categoryCV);
        }
    }

    public interface OnCategorySelect{
        void onSelectedCategory(Category category);
    }
}
