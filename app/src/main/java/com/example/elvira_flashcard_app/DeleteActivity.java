package com.example.elvira_flashcard_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        ImageView homeBtn = findViewById(R.id.home_button);
        TextView msg = findViewById(R.id.bottomDeckMessage);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeleteActivity.this, MainActivity.class);
                intent.putExtra("question", "Make your question now" );
                intent.putExtra("answer","Make your answer now" );
                setResult(RESULT_OK);
                finish();
            }
        });

    }
}