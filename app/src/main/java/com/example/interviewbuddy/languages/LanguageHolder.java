package com.example.interviewbuddy.languages;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interviewbuddy.R;

public class LanguageHolder extends RecyclerView.ViewHolder{
    ImageView img;
    TextView name;

    public LanguageHolder(@NonNull View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.languageLogo);
        name = itemView.findViewById(R.id.languageName);
    }
}
