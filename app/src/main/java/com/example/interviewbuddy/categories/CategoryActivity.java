package com.example.interviewbuddy.categories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.interviewbuddy.R;
import com.example.interviewbuddy.languages.LangaugeListener;
import com.example.interviewbuddy.languages.LanguageAdapter;
import com.example.interviewbuddy.models.CategoryModel;
import com.example.interviewbuddy.models.LanguageModel;
import com.example.interviewbuddy.quiz.QuizActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    FirebaseFirestore db;
    CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        String language = getIntent().getStringExtra("language");
        db = FirebaseFirestore.getInstance();
        RecyclerView rv = findViewById(R.id.categoryRV);
        adapter = new CategoryAdapter(getApplicationContext(), new CategoryListener() {
            @Override
            public void onClick(CategoryModel category) {
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                intent.putExtra("language", language);
                intent.putExtra("level", category.getLevel());
                intent.putExtra("categorie", category.getName());
                startActivity(intent);

            }
        });
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv.setAdapter(adapter);
        db.collection("categories")
                .whereEqualTo("language", language)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<CategoryModel> cat = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                cat.add(new CategoryModel(document.getId(), document.getData()));
                            }
                            adapter.setLanguages(cat);
                        } else {
                            System.out.println("Error");
                        }
                    }
                });
    }
}