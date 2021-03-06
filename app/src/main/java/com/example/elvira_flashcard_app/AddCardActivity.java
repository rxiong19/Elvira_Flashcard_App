package com.example.elvira_flashcard_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.EditText;


public class AddCardActivity extends AppCompatActivity {
    EditText Question = findViewById(R.id.QuestionLine);
    EditText Answer = findViewById(R.id.AnswerLine);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        ImageView cancelButton = findViewById(R.id.cancelQuestion);
        ImageView saveButton = findViewById(R.id.saveQuestion);



        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddCardActivity.this, MainActivity.class);
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                newQuestion();
                Intent intent = new Intent(AddCardActivity.this, MainActivity.class);
                intent.putExtra("New_Question", Question.getText().toString());
                intent.putExtra("New_Answer", Answer.getText().toString());
                finish();
            }
        });

    }
//    public void newQuestion(){
//        Intent intent = new Intent(AddCardActivity.this, MainActivity.class);
//        intent.putExtra("New_Question", Question.getText().toString());
//        intent.putExtra("New_Answer", Answer.getText().toString());
//        finish();
//    }



}