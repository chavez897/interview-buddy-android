package com.example.interviewbuddy.login;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.interviewbuddy.R;

public class LoginHolder extends RecyclerView.ViewHolder {
    public TextView emailTextView;
    public TextView passwordTextView;

    public LoginHolder(View itemView) {
        super(itemView);

        emailTextView = itemView.findViewById(R.id.email);
        passwordTextView = itemView.findViewById(R.id.password);
    }
}
