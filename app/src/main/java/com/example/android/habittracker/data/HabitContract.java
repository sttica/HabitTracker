package com.example.android.habittracker.data;

import android.provider.BaseColumns;

public final class HabitContract {

    private HabitContract() {}

    public static final class HabitEntry implements BaseColumns {

        public static final String TABLE_NAME = "habits";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT_NAME = "name";
        public static final String COLUMN_CATEGORY_NAME = "category";
        public static final String COLUMN_REPETITION_NAME = "repetition";
        public static final String COLUMN_STARTDATE_NAME = "date";
    }

}
