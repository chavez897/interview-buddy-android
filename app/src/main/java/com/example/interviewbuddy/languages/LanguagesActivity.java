package com.example.interviewbuddy.languages;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interviewbuddy.R;
import com.example.interviewbuddy.models.LanguageModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class LanguagesActivity extends AppCompatActivity {

    FirebaseFirestore db;
    LanguageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.languages);
        db = FirebaseFirestore.getInstance();
        RecyclerView rv = findViewById(R.id.languageRV);
        adapter = new LanguageAdapter(getApplicationContext(), new LangaugeListener() {
            @Override
            public void onClick(LanguageModel language) {
                System.out.println(language.getName());
            }
        });
        rv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        rv.setAdapter(adapter);

        db.collection("languages")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<LanguageModel> languages = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                languages.add(new LanguageModel(document.getId(), document.getData()));
                            }
                            adapter.setLanguages(languages);
                        } else {
                            System.out.println("Error");
                        }
                    }
                });
    }
}
