package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitDbHelper;

import static com.example.android.habittracker.data.HabitContract.HabitEntry.COLUMN_CATEGORY_NAME;
import static com.example.android.habittracker.data.HabitContract.HabitEntry.COLUMN_HABIT_NAME;
import static com.example.android.habittracker.data.HabitContract.HabitEntry.COLUMN_REPETITION_NAME;
import static com.example.android.habittracker.data.HabitContract.HabitEntry.COLUMN_STARTDATE_NAME;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new HabitDbHelper(this);

        // write habit entry
        writeEntry();

        // read habit entry
        Cursor cursor = readEndtry();

        TextView displayView = (TextView) findViewById(R.id.TestOutput);

        try {

            displayView.setText("Number of rows in habits database table: " + cursor.getCount() + " habits.\n\n");

            displayView.append(HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_HABIT_NAME + " - " +
                    HabitEntry.COLUMN_CATEGORY_NAME + " - " +
                    HabitEntry.COLUMN_REPETITION_NAME + " - " +
                    HabitEntry.COLUMN_STARTDATE_NAME + "\n"
            );

            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int categoryColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_CATEGORY_NAME);
            int repetitionColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_REPETITION_NAME);
            int startdateColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_STARTDATE_NAME);

            while (cursor.moveToNext()){
                int currentId = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentCategory = cursor.getString(categoryColumnIndex);
                String currentRepetition = cursor.getString(repetitionColumnIndex);
                String currentStartdate = cursor.getString(startdateColumnIndex);

                displayView.append("\n" + currentId + " - " +
                        currentName + " - " +
                        currentCategory + " - " +
                        currentRepetition + " - " +
                        currentStartdate
                );
            }

        } finally {
            cursor.close();
        }
    }

    public void writeEntry(){

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_HABIT_NAME, "Go for a run");
        values.put(COLUMN_CATEGORY_NAME, "Sport");
        values.put(COLUMN_REPETITION_NAME, 2);
        values.put(COLUMN_STARTDATE_NAME, "2017-07-17 18:30:00");

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);

    }

    public Cursor readEndtry(){

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                COLUMN_HABIT_NAME,
                COLUMN_CATEGORY_NAME,
                COLUMN_REPETITION_NAME,
                COLUMN_STARTDATE_NAME,
        };
        String selection = null;
        String[] selectionArgs = null;

        Cursor cursor = db.query(HabitEntry.TABLE_NAME, projection, selection, selectionArgs,
                null, null, null);

        return cursor;

    }


}
