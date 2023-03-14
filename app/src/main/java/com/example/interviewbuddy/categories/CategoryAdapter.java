package com.example.interviewbuddy.categories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interviewbuddy.R;
import com.example.interviewbuddy.models.CategoryModel;


import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {

    Context context;
    List<CategoryModel> categories;

    CategoryListener listener;

    public CategoryAdapter(Context context, CategoryListener listener) {
        this.context = context;
        this.categories = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryHolder(LayoutInflater.from(context).inflate(R.layout.category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        holder.name.setText(categories.get(position).getName());
        holder.level.setText(categories.get(position).getLevel());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(categories.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setLanguages(List<CategoryModel> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }
}
