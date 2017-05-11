package com.example.vibs.habitstracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.vibs.habitstracker.data.HabitsDbHelper;
import com.example.vibs.habitstracker.data.HabitContract.HabitEntry;

public class HabitsActivity extends AppCompatActivity {

    /**
     * Database helper that will provide us access to the database
     */
    private HabitsDbHelper mDbHelper;

//---------------------------------------------------------------------------------------------------------------------------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habits);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HabitsActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new HabitsDbHelper(this);
    }
//---------------------------------------------------------------------------------------------------------------------------//

    // Overriding "onStart()" method, so that "displayDatabaseInfo()" can be called each time HabitActivity starts.
    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }
//---------------------------------------------------------------------------------------------------------------------------//

    // Temporary helper method to display information in the onscreen TextView about the state of
    // the dailyHabits table
    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_DATE,
                HabitEntry.COLUMN_HABIT_EXERCISE,
                HabitEntry.COLUMN_HABIT_BREAKFAST,
                HabitEntry.COLUMN_HABIT_SMOKING,
                HabitEntry.COLUMN_HABIT_SLEEPHOURS };

        // Perform a query on the dailyhabits table
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,      // The table to query
                projection,                 // The columns to return
                null,                       // The values for the WHERE clause
                null,                       // Don't group the rows
                null,                       // Don't filter by row groups
                null,                       // The sort order
                null,
                null);

        TextView displayView = (TextView) findViewById(R.id.text_view_habits);

        try {
            // Create a header in the Text View that looks like this:
            //
            // The dailyhabits table contains <number of days in Cursor>.
            // _id - date - exercise - breakfast - sleephours
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("There are " + cursor.getCount() + " no. of days in dailyhabits DB table.\n\n");
            displayView.append(HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_HABIT_DATE  + " - " +
                    HabitEntry.COLUMN_HABIT_EXERCISE  + " - " +
                    HabitEntry.COLUMN_HABIT_BREAKFAST  + " - " +
                    HabitEntry.COLUMN_HABIT_SLEEPHOURS + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int dateColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DATE);
            int exerciseColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_EXERCISE);
            int breakfastColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_BREAKFAST);
            int sleephrsColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_SLEEPHOURS);


            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentDate = cursor.getString(dateColumnIndex);
                String currentExercise = cursor.getString(exerciseColumnIndex);
                String currentBreakfast = cursor.getString(breakfastColumnIndex);
                int currentSleepHours = cursor.getInt(sleephrsColumnIndex);

                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentDate + " - " +
                        currentExercise + " - " +
                        currentBreakfast + " - " +
                        currentSleepHours));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
//---------------------------------------------------------------------------------------------------------------------------//
}