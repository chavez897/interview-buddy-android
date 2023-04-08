package com.example.interviewbuddy.badges;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interviewbuddy.R;

public class BadgeHolder extends RecyclerView.ViewHolder {
    TextView name;
    ImageView img;

    public BadgeHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        img = itemView.findViewById(R.id.imgBadge);
    }
}
