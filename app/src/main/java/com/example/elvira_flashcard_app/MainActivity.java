package com.example.elvira_flashcard_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView questionTextView;
    TextView answerTextView;
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int cardptr = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//lab 1
        questionTextView = findViewById(R.id.question);
        answerTextView = findViewById(R.id.answer);

        flashcardDatabase = new FlashcardDatabase(this);
        allFlashcards = flashcardDatabase.getAllCards();
        cardptr =0;
        if (allFlashcards != null && allFlashcards.size() > 0){

            ((TextView) findViewById(R.id.question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.answer)).setText(allFlashcards.get(0).getAnswer());
        }

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
// lab 2
        ImageView addQuestion = findViewById(R.id.addQuestionM);

        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                cardptr++;
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }

        });

//lab 3

        ImageView next = findViewById(R.id.next_button);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allFlashcards.size() == 0){
                    return;
                }
                else{
                    cardptr++;
                    if (cardptr >= allFlashcards.size()){
                        cardptr = 0;
                    }
                        questionTextView.setVisibility(View.VISIBLE);
                        answerTextView.setVisibility(View.INVISIBLE);
                        allFlashcards = flashcardDatabase.getAllCards();
                        Flashcard card = allFlashcards.get(cardptr);
                        questionTextView.setText(card.getQuestion());
                        answerTextView.setText(card.getAnswer());

                }
            }
        });

        ImageView last = findViewById(R.id.last_button);

        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allFlashcards.size() == 0){
                    return;
                }
                    cardptr --;
                    if (cardptr < 0) {
                        cardptr = 0;
                    }
                        questionTextView.setVisibility(View.VISIBLE);
                        answerTextView.setVisibility(View.INVISIBLE);
                        allFlashcards = flashcardDatabase.getAllCards();
                        Flashcard card = allFlashcards.get(cardptr);
                        questionTextView.setText(card.getQuestion());
                        answerTextView.setText(card.getAnswer());

            }
        });
        ImageView delete = findViewById(R.id.delete_button);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((allFlashcards.size()-2) < 0 ){
                    Intent intent = new Intent(MainActivity.this, DeleteActivity.class);
                    MainActivity.this.startActivityForResult(intent, 100);
                }
                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.question)).getText().toString());
                allFlashcards = flashcardDatabase.getAllCards();
                cardptr--;
                questionTextView.setText(allFlashcards.get(cardptr).getQuestion());
                answerTextView.setText(allFlashcards.get(cardptr).getAnswer());

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            if (cardptr >= 0){
            String question = data.getExtras().getString("question");
            String answer = data.getExtras().getString("answer");
            questionTextView.setText(question);
            answerTextView.setText(answer);
            Flashcard newcard = new Flashcard(question, answer);
            flashcardDatabase.insertCard(newcard);
            cardptr++;
            allFlashcards = flashcardDatabase.getAllCards();
            }
            else{
                String question = data.getExtras().getString("question");
                String answer = data.getExtras().getString("answer");
                questionTextView.setText(question);
                answerTextView.setText(answer);
            }
        }
    }

}
