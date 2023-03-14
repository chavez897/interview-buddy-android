package com.example.interviewbuddy.languages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.interviewbuddy.R;
import com.example.interviewbuddy.models.LanguageModel;

import java.util.ArrayList;
import java.util.List;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageHolder>{

    Context context;
    List<LanguageModel> language;

    LangaugeListener listener;

    public LanguageAdapter(Context context, LangaugeListener listener) {
        this.context = context;
        this.language = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public LanguageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LanguageHolder(LayoutInflater.from(context).inflate(R.layout.language_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageHolder holder, int position) {
        Glide.with(context).load(language.get(position).getImage()).into(holder.img);
        holder.name.setText(language.get(position).getName());
        holder.img.setOnClickListener(new View.OnClickListener(
        ) {
            @Override
            public void onClick(View v) {
                listener.onClick(language.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return language.size();
    }

    public void setLanguages(List<LanguageModel> language) {
        this.language = language;
        notifyDataSetChanged();
    }
}
