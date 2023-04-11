package com.example.interviewbuddy.register;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.interviewbuddy.R;

public class RegisterHolder extends RecyclerView.ViewHolder {
    public TextView nameTextView;
    public TextView emailTextView;
    public TextView passwordTextView;

    public RegisterHolder(View itemView) {
        super(itemView);

        nameTextView = (TextView) itemView.findViewById(R.id.name);
        emailTextView = (TextView) itemView.findViewById(R.id.email);
        passwordTextView = (TextView) itemView.findViewById(R.id.password);
    }
}
