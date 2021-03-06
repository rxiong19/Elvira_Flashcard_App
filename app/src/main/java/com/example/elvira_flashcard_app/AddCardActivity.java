package com.example.elvira_flashcard_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.EditText;


public class AddCardActivity extends AppCompatActivity {
    EditText Question;
    EditText Answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        ImageView cancelButton = findViewById(R.id.cancelQuestion);
        ImageView saveButton = findViewById(R.id.saveQuestion);
        Question = findViewById(R.id.QuestionLine);
        Answer = findViewById(R.id.AnswerLine);



        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddCardActivity.this, MainActivity.class);
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                newQuestion();


            }
        });

    }

    public void newQuestion(){
        Intent intent = new Intent(AddCardActivity.this, MainActivity.class);
        intent.putExtra("question", ((EditText)Question).getText().toString());
        intent.putExtra("answer", ((EditText)Answer).getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }



}