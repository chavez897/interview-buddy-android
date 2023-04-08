package com.example.interviewbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.interviewbuddy.review.ReviewActivity;

import java.util.Arrays;

public class ResultActivity extends AppCompatActivity {

    String language;
    String review;
    String level;
    String categorie;
    Integer correct;
    Integer incorrect;
    String topics;
    TextView topicsTV;
    TextView correctTV;
    TextView incorrectTV;
    TextView title;
    TextView description;
    TextView pending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        language = getIntent().getStringExtra("language");
        level = getIntent().getStringExtra("level");
        categorie = getIntent().getStringExtra("categorie");
        correct = getIntent().getIntExtra("correct" , 0);
        incorrect = getIntent().getIntExtra("incorrect", 0);
        topics = getIntent().getStringExtra("topics");
        review = getIntent().getStringExtra("review");

        System.out.println(review);
        System.out.println(review);

        topicsTV = findViewById(R.id.textView11);
        correctTV = findViewById(R.id.textView3);
        incorrectTV = findViewById(R.id.textView4);
        title = findViewById(R.id.textView);
        description = findViewById(R.id.textView2);
        pending = findViewById(R.id.textView9);

        topicsTV.setText(topics.replace(",", "\n"));
        correctTV.setText("Correct: " + correct);
        incorrectTV.setText("Incorrect: " + incorrect);

        int score = correct * 100 / (correct + incorrect);
        if (score >= 50) {
            title.setText("Congratulations!");
            description.setText("You have passed the quiz with " + score + "%");
        } else {
            title.setText("I am sorry");
            description.setText("You have failed the quiz with " + score + "%");
        }
    }

    public void goToReview(View view) {
        Intent intent = new Intent(getApplicationContext(), ReviewActivity.class);
        intent.putExtra("correct", correct);
        intent.putExtra("incorrect", incorrect);
        intent.putExtra("review", review);
        startActivity(intent);
    }
}
