package com.example.elvira_flashcard_app;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView questionTextView;
    TextView answerTextView;
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int cardptr = -1;
    CountDownTimer timer;
    ImageView timer_button;
    TextView countdown;
    int hit = 0;
    private void startTimer(){
        timer.cancel();
        timer.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//lab 1
        questionTextView = findViewById(R.id.question);
        answerTextView = findViewById(R.id.answer);

        flashcardDatabase = new FlashcardDatabase(this);
        allFlashcards = flashcardDatabase.getAllCards();
        cardptr = 0;
        if (allFlashcards != null && allFlashcards.size() > 0){

            ((TextView) findViewById(R.id.question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.answer)).setText(allFlashcards.get(0).getAnswer());
        }

        timer_button = findViewById(R.id.timer);
        countdown = findViewById(R.id.countdown);
        timer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hit++;
                timer_button.setVisibility(View.INVISIBLE);
                countdown.setVisibility(View.VISIBLE);
                startTimer();
            }
        });

        countdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer_button.setVisibility(View.VISIBLE);
                countdown.setVisibility(View.INVISIBLE);
                hit--;
            }
        });

        timer = new CountDownTimer(16000, 1000) {
            public void onTick(long millisUntilFinished) {
                countdown.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
            }
        };

        questionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cx = answerTextView.getWidth();
                int cy = answerTextView.getHeight();

                float finalRadius = (float) Math.hypot(cx, cy);
                Animator anim = ViewAnimationUtils.createCircularReveal(answerTextView, cx, cy, 0f, finalRadius);

                questionTextView.setVisibility(View.INVISIBLE);
                answerTextView.setVisibility(View.VISIBLE);
                anim.setDuration(3000);
                anim.start();

            }
        });

        answerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cx = questionTextView.getWidth()/2;
                int cy = questionTextView.getHeight()/2;

                float finalRadius = (float) Math.hypot(cx, cy);
                Animator anim = ViewAnimationUtils.createCircularReveal(questionTextView, cx, cy, 0f, finalRadius);

                questionTextView.setVisibility(View.VISIBLE);
                answerTextView.setVisibility(View.INVISIBLE);

                anim.setDuration(3000);
                anim.start();

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
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }

        });

//lab 3

        ImageView next = findViewById(R.id.next_button);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation leftOutAnim = AnimationUtils.loadAnimation(view.getContext(), R.anim.left_out);


                leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation anim) {
                        // this method is called when the animation first starts
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        questionTextView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // we don't need to worry about this method
                    }

                });
                questionTextView.startAnimation(leftOutAnim);
                if (hit > 0){startTimer();}
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

                final Animation rightInAnim = AnimationUtils.loadAnimation(view.getContext(), R.anim.right_in);
                rightInAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation anim) {
                        // this method is called when the animation first starts
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        questionTextView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // we don't need to worry about this method
                    }

                });
                questionTextView.startAnimation(rightInAnim);
            }
        });

        ImageView last = findViewById(R.id.last_button);

        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation leftOutAnim = AnimationUtils.loadAnimation(view.getContext(), R.anim.left_out);

                leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation anim) {
                        // this method is called when the animation first starts
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        questionTextView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // we don't need to worry about this method
                    }

                });
                questionTextView.startAnimation(leftOutAnim);

                if (hit > 0){startTimer();}
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

                final Animation rightInAnim = AnimationUtils.loadAnimation(view.getContext(), R.anim.right_in);
                rightInAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation anim) {
                        // this method is called when the animation first starts
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        questionTextView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // we don't need to worry about this method
                    }

                });
                questionTextView.startAnimation(rightInAnim);

            }

        });
        ImageView delete = findViewById(R.id.delete_button);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((allFlashcards.size()-2) < 0 ){
                    Intent intent = new Intent(MainActivity.this, DeleteActivity.class);
                    MainActivity.this.startActivityForResult(intent, 200);
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
                String question = data.getExtras().getString("question");
                String answer = data.getExtras().getString("answer");
                questionTextView.setText(question);
                answerTextView.setText(answer);
                Flashcard newcard = new Flashcard(question, answer);
                flashcardDatabase.insertCard(newcard);
                cardptr++;
                allFlashcards = flashcardDatabase.getAllCards();
            }
        if (requestCode == 200 && resultCode == RESULT_OK) {
            String question = data.getExtras().getString("question");
            String answer = data.getExtras().getString("answer");
            questionTextView.setText(question);
            answerTextView.setText(answer);
        }
    }

}
