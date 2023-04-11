package com.example.interviewbuddy.register;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.interviewbuddy.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private EditText mEmailEditText;
    private Button mRegisterButton;
    private CheckBox mChekboxButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        mUsernameEditText = findViewById(R.id.name);
        mPasswordEditText = findViewById(R.id.password);
        mEmailEditText = findViewById(R.id.email);
        mRegisterButton = findViewById(R.id.btnSignup);
        mChekboxButton = findViewById(R.id.checkTerms);


        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mUsernameEditText.getText().toString().trim();
                String password = mPasswordEditText.getText().toString().trim();
                String email = mEmailEditText.getText().toString().trim();

                if (TextUtils.isEmpty(username)) {
                    mUsernameEditText.setError("Username is required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPasswordEditText.setError("Password is required");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    mEmailEditText.setError("Email is required");
                    return;
                }
                if (!mChekboxButton.isChecked()) {
                    Toast.makeText(RegisterActivity.this, "You must agree to the terms and conditions", Toast.LENGTH_SHORT).show();
                    return;
                }
                // TODO: Implement registration logic here
            }
        });
    }
}
