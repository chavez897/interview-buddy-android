package com.example.interviewbuddy.review;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interviewbuddy.R;

public class ReviewHolder extends RecyclerView.ViewHolder {

    TextView name;
    ImageView img;

    public ReviewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        img = itemView.findViewById(R.id.img);
    }
}
