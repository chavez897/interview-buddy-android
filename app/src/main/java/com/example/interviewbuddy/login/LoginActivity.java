package com.example.interviewbuddy.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interviewbuddy.R;

import java.util.List;

public class LoginActivity extends RecyclerView.Adapter<LoginHolder> {

    private List<Login> loginList;

    @NonNull
    @Override
    public LoginHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.login, parent, false);
        return new LoginHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoginHolder holder, int position) {
        Login login = loginList.get(position);
        holder.emailTextView.setText(login.getEmail());
        holder.passwordTextView.setText(login.getPassword());
    }

    @Override
    public int getItemCount() {
        return loginList.size();
    }
}
