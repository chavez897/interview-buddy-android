package com.example.interviewbuddy.badges;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interviewbuddy.R;
import com.example.interviewbuddy.categories.CategoryHolder;

import java.util.ArrayList;
import java.util.List;

public class BadgeAdapter extends RecyclerView.Adapter<BadgeHolder> {
    Context context;
    List<String> badges;
    public BadgeAdapter(Context context) {
        this.context = context;
        badges = new ArrayList<>();
    }
    @NonNull
    @Override
    public BadgeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BadgeHolder(LayoutInflater.from(context).inflate(R.layout.badge_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BadgeHolder holder, int position) {
        holder.name.setText(badges.get(position));
        holder.img.setImageResource(R.drawable.starbadge);
    }

    @Override
    public int getItemCount() {
        return badges.size();
    }

    public void setBadges(List<String> badges) {
           this.badges = badges;notifyDataSetChanged();
    }
}
