package com.example.android.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.habittracker.data.HabitContract.HabitEntry;

public class HabitDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "habits.db";
    public static final String SQL_DELETE_ENTRIES = "";

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                        + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
                        + HabitEntry.COLUMN_CATEGORY_NAME + " TEXT, "
                        + HabitEntry.COLUMN_REPETITION_NAME + " INTEGER NOT NULL DEFAULT 0, "
                        + HabitEntry.COLUMN_STARTDATE_NAME + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

}