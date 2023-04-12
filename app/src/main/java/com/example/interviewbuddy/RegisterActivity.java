package com.example.interviewbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.interviewbuddy.languages.LanguagesActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText name,email,pwd;
    Button btnregister;
    TextView gologin;
    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        name= findViewById(R.id.name);
        email=findViewById(R.id.email);
        pwd=findViewById(R.id.password);
        btnregister=findViewById(R.id.btnSignup);
        gologin=findViewById(R.id.goLogin);

        fauth=FirebaseAuth.getInstance();

        gologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        });
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=name.getText().toString();
                String useremail=email.getText().toString();
                String userpwd=pwd.getText().toString();

                if(username.isEmpty()){
                    name.setError("Username Required");
                    return;
                }
                if(useremail.isEmpty()){
                    email.setError("Email Required");
                    return;
                }
                if(userpwd.isEmpty()){
                    pwd.setError("Password Required");
                    return;
                }

                //Toast.makeText(RegisterActivity.this,"Data Valid",Toast.LENGTH_SHORT).show();
                btnregister.setText("Loading");
                btnregister.setClickable(false);
                fauth.createUserWithEmailAndPassword(useremail,userpwd).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Intent intent =new Intent(getApplicationContext(), LanguagesActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        btnregister.setText("Register");
                        btnregister.setClickable(true);
                    }
                });
            }
        });
    }
}

