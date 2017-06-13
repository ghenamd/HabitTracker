package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by Ghena on 12/06/2017.
 */

public class HabitContract {

    private HabitContract() {
    }

    public static final class HabitEntry implements BaseColumns {

        public final static String _ID = BaseColumns._ID;
        public final static String TABLE_NAME = "Habit";
        public final static String COLUMN_PERSON_NAME = "Person name";
        public final static String COLUMN_WEEK_DAY = "Day of the week";
        public final static String COLUMN_MORNING_EXERCISE_MINUTES = "Morning Exercise Minutes";
        public final static String COLUMN_WORK_HOURS = "Work Hours";
        public final static String COLUMN_HOURS_SLEPT = "Hours Slept";

    }
}
