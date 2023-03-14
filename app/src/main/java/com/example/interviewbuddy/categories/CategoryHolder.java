package com.example.interviewbuddy.categories;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interviewbuddy.R;

public class CategoryHolder extends RecyclerView.ViewHolder {

    ConstraintLayout layout;
    TextView name;
    TextView level;

    public CategoryHolder(@NonNull View itemView) {
        super(itemView);
        layout = itemView.findViewById(R.id.categoryCard);
        name = itemView.findViewById(R.id.categoryName);
        level = itemView.findViewById(R.id.categoryLevel);
    }
}
