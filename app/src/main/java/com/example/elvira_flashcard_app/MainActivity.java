package com.example.elvira_flashcard_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView questionTextView = findViewById(R.id.question);
        TextView answerTextView = findViewById(R.id.answer);

        questionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionTextView.setVisibility(View.INVISIBLE);
                answerTextView.setVisibility(View.VISIBLE);
            }
        });

        answerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionTextView.setVisibility(View.VISIBLE);
                answerTextView.setVisibility(View.INVISIBLE);
            }
        });

    }
}