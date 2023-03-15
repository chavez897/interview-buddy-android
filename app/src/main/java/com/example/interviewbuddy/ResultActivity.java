package com.example.interviewbuddy;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    String language;
    String level;
    String categorie;
    Integer correct;
    Integer incorrect;
    String [] topics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        language = getIntent().getStringExtra("language");
        level = getIntent().getStringExtra("level");
        categorie = getIntent().getStringExtra("categorie");
        correct = getIntent().getIntExtra("correct" , 0);
        incorrect = getIntent().getIntExtra("incorrect", 0);
        topics = getIntent().getStringExtra("topics").split(",");
        System.out.println(topics.length);
        System.out.println(incorrect);
        System.out.println(correct);
        System.out.println(language);
        System.out.println(level);
        System.out.println(categorie);

    }
}
