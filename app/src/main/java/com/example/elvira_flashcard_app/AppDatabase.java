package com.example.elvira_flashcard_app;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {Flashcard.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FlashcardDao flashcardDao();
}
