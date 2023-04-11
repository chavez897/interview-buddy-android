package com.example.interviewbuddy.register;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interviewbuddy.R;

import java.util.List;

public class RegisterAdapter<RegisterActivity> extends RecyclerView.Adapter<RegisterHolder> {

    private List<RegisterActivity> registrationList;

    public RegisterAdapter(List<RegisterActivity> registrationList) {
        this.registrationList = registrationList;
    }

    @NonNull
    @Override
    public RegisterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.register, parent, false);
        return new RegisterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegisterHolder holder, int position) {
        RegisterActivity registration = registrationList.get(position);
        holder.nameTextView.setText(registration.getName());
        holder.emailTextView.setText(registration.getEmail());
        holder.passwordTextView.setText(registration.getPassword());
    }

    @Override
    public int getItemCount() {
        return registrationList.size();
    }
}

