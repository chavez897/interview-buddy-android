package com.example.interviewbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.interviewbuddy.badges.BadgesActivity;
import com.example.interviewbuddy.languages.LanguagesActivity;
import com.example.interviewbuddy.review.ReviewActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    String user;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        user = "WiyGaThUSzTXz0L0NJdA1nmmB0v1";
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
        db = FirebaseFirestore.getInstance();
        getData(score);
    }

    public void getData(int score) {
        db.collection("quizesToBadges")
                .whereEqualTo("user", user)
                .whereEqualTo("language", language)
                .whereEqualTo("level", level)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        System.out.println("test");
                        if (task.isSuccessful()) {
                            List<DocumentSnapshot> list = task.getResult().getDocuments();
                            if (list.size() > 0) {
                                long left = (long) list.get(0).getData().get("left");
                                if (score >= 50) {
                                    if (left > 1) {
                                        left--;
                                        pending.setText("Pending quizzes to badges: " + left);
                                        updateRecord(left, list.get(0).getId());
                                    } else {
                                        pending.setText("You have the badge");
                                        if (left == 1) {
                                            left--;
                                            updateRecord(left, list.get(0).getId());
                                            createBadge();
                                        }
                                    }
                                } else {
                                    pending.setText("Pending quizzes to badges: " + left);
                                }
                            } else {
                                pending.setText("Pending quizzes to badges: 10");
                                createQuizesToBadge();
                            }
                        } else {
                            System.out.println("Error");
                        }
                    }
                });

    }

    public void updateRecord(long left, String id) {
        System.out.println(id);
        db.collection("quizesToBadges").document(id).update(
                "left", left
        );
    }

    public void createBadge() {
        Map<String, Object> data = new HashMap<>();
        data.put("image", "badge2");
        data.put("language", language);
        data.put("level", level);
        data.put("user_id", user);

        db.collection("badges")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }

    public void createQuizesToBadge() {
        Map<String, Object> data = new HashMap<>();
        data.put("left", 10);
        data.put("language", language);
        data.put("level", level);
        data.put("user_id", user);

        db.collection("quizesToBadges")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }
    public void goToReview(View view) {
        Intent intent = new Intent(getApplicationContext(), ReviewActivity.class);
        intent.putExtra("correct", correct);
        intent.putExtra("incorrect", incorrect);
        intent.putExtra("review", review);
        startActivity(intent);
    }

    public void goToHome(View view) {
        Intent intent = new Intent(getApplicationContext(), LanguagesActivity.class);
        startActivity(intent);
    }

    public void goToMyBadges(View view) {
        Intent intent = new Intent(getApplicationContext(), BadgesActivity.class);
        startActivity(intent);
    }

}
