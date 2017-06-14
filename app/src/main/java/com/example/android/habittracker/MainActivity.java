package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.habittracker.data.HabitContract;
import com.example.android.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    private EditText mPersonName;
    private EditText mWeekDay;
    private EditText mMorningExercise;
    private EditText mWorkHours;
    private EditText mHoursSlept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPersonName = (EditText)findViewById(R.id.name);
        mWeekDay = (EditText)findViewById(R.id.week_day);
        mMorningExercise = (EditText)findViewById(R.id.exercise);
        mWorkHours = (EditText)findViewById(R.id.work);
        mHoursSlept = (EditText)findViewById(R.id.hours_slept);
    }

    // Method to insert a new Habit
    private void insertHabit() {
        HabitDbHelper mDbHelper = new HabitDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String personName = mPersonName.getText().toString().trim();
        String weekDay = mWeekDay.getText().toString().trim();
        int morningExercise = Integer.parseInt(mMorningExercise.getText().toString().trim());
        int workHours = Integer.parseInt(mWorkHours.getText().toString().trim());
        int hoursSlept = Integer.parseInt(mHoursSlept.getText().toString().trim());

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_PERSON_NAME, personName);
        values.put(HabitContract.HabitEntry.COLUMN_WEEK_DAY, weekDay);
        values.put(HabitContract.HabitEntry.COLUMN_MORNING_EXERCISE_MINUTES, morningExercise);
        values.put(HabitContract.HabitEntry.COLUMN_WORK_HOURS, workHours);
        values.put(HabitContract.HabitEntry.COLUMN_HOURS_SLEPT, hoursSlept);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(HabitContract.HabitEntry.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "Error with saving habit", Toast.LENGTH_SHORT);
        } else {
            Toast.makeText(this, "Habit saved with row id" + newRowId, Toast.LENGTH_SHORT);
        }
    }
    //Method that returns a cursor
    private  Cursor cursor() {

        HabitDbHelper mDbHelper = new HabitDbHelper(this);
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {
                HabitContract.HabitEntry._ID,
                HabitContract.HabitEntry.COLUMN_PERSON_NAME,
                HabitContract.HabitEntry.COLUMN_WEEK_DAY,
                HabitContract.HabitEntry.COLUMN_MORNING_EXERCISE_MINUTES,
                HabitContract.HabitEntry.COLUMN_WORK_HOURS,
                HabitContract.HabitEntry.COLUMN_HOURS_SLEPT
        };

        Cursor cursor = db.query(
                HabitContract.HabitEntry.TABLE_NAME,
                projection,
                null, null,
                null, null, null);

        return cursor;
    }
}
