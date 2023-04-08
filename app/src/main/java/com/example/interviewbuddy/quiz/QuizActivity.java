package com.example.interviewbuddy.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.interviewbuddy.R;
import com.example.interviewbuddy.ResultActivity;
import com.example.interviewbuddy.models.CategoryModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    TextView header;
    TextView question;
    Button option1;
    Button option2;
    Button option3;
    Button option4;
    int questionCounter = 0;
    int correctCounter = 0;
    int incorrectCounter = 0;
    List<String> topicsToReview = new ArrayList<>();
    FirebaseFirestore db;
    List<Map<String, String>> questions;
    String language;
    String level;
    String categorie;

    boolean[] review = new boolean[5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        header = findViewById(R.id.question_heading_tv);
        question = findViewById(R.id.question_tv);
        option1 = findViewById(R.id.option1_b);
        option2 = findViewById(R.id.option2_b);
        option3 = findViewById(R.id.option3_b);
        option4 = findViewById(R.id.option4_b);
        db = FirebaseFirestore.getInstance();
        language = getIntent().getStringExtra("language");
        level = getIntent().getStringExtra("level");
        categorie = getIntent().getStringExtra("categorie");
        getQuestion(language, level, categorie);
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAnswer(option1.getText().toString());
            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAnswer(option2.getText().toString());
            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAnswer(option3.getText().toString());
            }
        });
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAnswer(option4.getText().toString());
            }
        });
    }

    private void getQuestion(String language, String level, String categorie) {
        db.collection("questions")
                .whereEqualTo("language", language)
                .whereEqualTo("level", level)
                .whereEqualTo("categorie", categorie)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Random randI = new Random();
                            Map<String, Object> data = task.getResult().getDocuments().get(randI.nextInt(task.getResult().size())).getData();
                            questions = (List) data.get("questions");
                            updateUI();
                        } else {
                            System.out.println("Error");
                        }
                    }
                });
    }

    private void updateUI(){
        header.setText("Question " + (questionCounter + 1));
        question.setText(questions.get(questionCounter).get("question"));
        option1.setText(questions.get(questionCounter).get("answer1"));
        option2.setText(questions.get(questionCounter).get("answer2"));
        option3.setText(questions.get(questionCounter).get("answer3"));
        option4.setText(questions.get(questionCounter).get("answer4"));
    };

    private void validateAnswer(String answer) {
        if (answer.equals(questions.get(questionCounter).get("correct"))) {
            correctCounter++;
            review[questionCounter] = true;
        } else {
            incorrectCounter++;
            topicsToReview.add(questions.get(questionCounter).get("topic"));
            review[questionCounter] = false;
        }
        questionCounter++;
        if (questionCounter < questions.size()) {
            updateUI();
        } else {
            finishQuiz();
        }
    }

    private void finishQuiz() {
        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
        intent.putExtra("language", language);
        intent.putExtra("level", level);
        intent.putExtra("categorie", categorie);
        intent.putExtra("correct", correctCounter);
        intent.putExtra("incorrect", incorrectCounter);
        String topics = String.join(",", topicsToReview);
        intent.putExtra("topics", topics);
        String reviewString  = Arrays.toString(review);
        intent.putExtra("review", reviewString);
        startActivity(intent);
    }
}
