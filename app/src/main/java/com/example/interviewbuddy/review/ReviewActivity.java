package com.example.interviewbuddy.review;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interviewbuddy.R;

public class ReviewActivity extends AppCompatActivity {

    Integer correct;
    Integer incorrect;
    String reviewString;
    boolean[] review;
    TextView title;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_screen);
        correct = getIntent().getIntExtra("correct" , 0);
        incorrect = getIntent().getIntExtra("incorrect", 0);
        reviewString = getIntent().getStringExtra("review");
        reviewString = reviewString.replace("[", "");
        reviewString = reviewString.replace("]", "");
        String[] parts = reviewString.split(",");
        System.out.println(reviewString);
        review = new boolean[parts.length];
        for (int i = 0; i < parts.length; i++) {
            System.out.println(parts[i]);
            review[i] = parts[i].trim().equals("true");
        }
        title = findViewById(R.id.textView5);
        title.setText("Result: " + correct + "/" + (correct + incorrect));
        rv = findViewById(R.id.rv);
        ReviewAdapter adapter = new ReviewAdapter(getApplicationContext());
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter.setQuestions(review);
        rv.setAdapter(adapter);

    }
}
