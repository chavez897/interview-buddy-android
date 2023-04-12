package com.example.interviewbuddy.badges;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interviewbuddy.R;
import com.example.interviewbuddy.models.CategoryModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class BadgesActivity extends AppCompatActivity {

    RecyclerView rv;
    String user;

    FirebaseFirestore db;

    BadgeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badges);
        rv = findViewById(R.id.badges);
        user = FirebaseAuth.getInstance().getCurrentUser().getUid();
        adapter = new BadgeAdapter(getApplicationContext());
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv.setAdapter(adapter);
        db = FirebaseFirestore.getInstance();
        getData();
    }

    public void getData() {
        db.collection("badges")
                .whereEqualTo("user_id", user)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<String> badges = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                badges.add((String) document.getData().get("language"));
                            }
                            System.out.println(badges.size());
                            adapter.setBadges(badges);
                        } else {
                            System.out.println("Error");
                        }
                    }
                });

    }
}

