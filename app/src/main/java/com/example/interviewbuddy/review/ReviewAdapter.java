package com.example.interviewbuddy.review;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interviewbuddy.R;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewHolder> {

        Context context;
        boolean[] questions;

        public ReviewAdapter(Context context) {
                this.context = context;
        }

        @NonNull
        @Override
        public ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new ReviewHolder(LayoutInflater.from(context).inflate(R.layout.review_item, parent, false));
                }

        @Override
        public void onBindViewHolder(@NonNull ReviewHolder holder, int position) {
                holder.name.setText("Question " + (position + 1));
                holder.name.setTextColor(questions[position] ? Color.parseColor("#31FE40") : Color.parseColor("#FB0000"));
                holder.img.setImageResource(questions[position] ? R.drawable.correct: R.drawable.incorrect);
        }

        @Override
        public int getItemCount() {
                return questions.length;
        }

        public void setQuestions(boolean[] questions) {
                this.questions = questions;
                notifyDataSetChanged();
        }
}
