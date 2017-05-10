package com.example.vibs.habitstracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.vibs.habitstracker.data.HabitsDbHelper;
import com.example.vibs.habitstracker.data.HabitContract.HabitEntry;
import java.util.Date;


public class HabitsActivity extends AppCompatActivity {

    /**
     * Database helper that will provide us access to the database
     */
    private HabitsDbHelper mDbHelper;

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

        insertDailyHabits();
        displayDatabaseInfo();
    }

    // Temporary helper method to display information in the onscreen TextView about the state of
    // the dailyHabits table
    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Perform this raw SQL query "SELECT * FROM dailyHabits"
        // to get a Cursor that contains all rows from the dailyHabits table.
        Cursor cursor = db.rawQuery("SELECT * FROM " + HabitEntry.TABLE_NAME, null);

        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // dailyHabits table in the database).
            TextView displayView = (TextView) findViewById(R.id.text_view_habits);
            displayView.setText("Number of records in dailyHabits DB table is: " + cursor.getCount());
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
    /**
     * Helper method to insert hardcoded habit data into the database. For debugging purposes only.
     */
    private void insertDailyHabits() {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and Toto's pet attributes are the values.
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_DATE, "01/01/2017");
        values.put(HabitEntry.COLUMN_HABIT_EXERCISE, HabitEntry.EXERCISE_YES);
        values.put(HabitEntry.COLUMN_HABIT_BREAKFAST, HabitEntry.BREAKFAST_YES);
        values.put(HabitEntry.COLUMN_HABIT_SMOKING, HabitEntry.SMOKING_NO);
        values.put(HabitEntry.COLUMN_NAME_SLEEPHOURS, 7);

        // Insert a new row for Toto in the database, returning the ID of that new row.
        // The first argument for db.insert() is the pets table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for Toto.
        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
    }
}