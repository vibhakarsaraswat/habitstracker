package com.example.vibs.habitstracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vibs.habitstracker.data.HabitContract;
import com.example.vibs.habitstracker.data.HabitContract.HabitEntry;
import com.example.vibs.habitstracker.data.HabitsDbHelper;

public class EditorActivity extends AppCompatActivity {

    /** EditText field to enter the date for babit addition */
    private EditText mDateEditText;

//----------------------------------------------------------------------------------------------------------------------//
    /** Spinner field to enter if excercise is done or not */
    private Spinner mExerciseSpinner;

    /**
     * Excercise Habit of the User. The possible valid values are in the HabitContract.java file:
     * {@link HabitEntry#EXERCISE_NO}, {@link HabitEntry#EXERCISE_YES} or {@link HabitEntry#EXERCISE_UNKNOWN}
     */
    private int mExcerciseHabit = HabitEntry.EXERCISE_UNKNOWN;
//----------------------------------------------------------------------------------------------------------------------//
    /** Spinner field to enter if breakfast is eaten or not */
    private Spinner mBreakfastSpinner;

    /**
     * Breakfast Habit of the User. The possible valid values are in the HabitContract.java file:
     * {@link HabitEntry#BREAKFAST_NO}, {@link HabitEntry#BREAKFAST_YES} or {@link HabitEntry#BREAKFAST_UNKNOWN}
     */
    private int mBreakfastHabit = HabitEntry.BREAKFAST_UNKNOWN;
//----------------------------------------------------------------------------------------------------------------------//
    /** Spinner field to enter if there was any smoking or not  */
    private Spinner mSmokingSpinner;

    /**
     * Smoking Habit of the User. The possible valid values are in the HabitContract.java file:
     * {@link HabitEntry#SMOKING_NO}, {@link HabitEntry#SMOKING_YES} or {@link HabitEntry#SMOKING_UNKNOWN}
     */
    private int mSmokingHabit = HabitEntry.SMOKING_UNKNOWN;
//----------------------------------------------------------------------------------------------------------------------//
    /** EditText field to enter No of hours user slept */
    private EditText mSleephoursEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Find all relevant views that we will need to read user input from
        mDateEditText = (EditText) findViewById(R.id.editText_date);
        mExerciseSpinner = (Spinner) findViewById(R.id.spinner_exercise);
        mBreakfastSpinner = (Spinner) findViewById(R.id.spinner_brakfast);
        mSmokingSpinner = (Spinner) findViewById(R.id.spinner_smoking);
        mSleephoursEditText = (EditText) findViewById(R.id.editText_sleephours);

        setupSpinner();
    }

    private void setupSpinner() {
//--------------------------------------------SELECTING SPINNER VALUE FOR EXERCISE--------------------------------------------//
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter exerciseSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_exercise_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        exerciseSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mExerciseSpinner.setAdapter(exerciseSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mExerciseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               String selection = (String) parent.getItemAtPosition(position);
               if (!TextUtils.isEmpty(selection)) {
                   if (selection.equals(getString(R.string.habit_exercise_yes))) {
                       mExcerciseHabit = HabitEntry.EXERCISE_YES;
                   } else if (selection.equals(getString(R.string.habit_exercise_no))) {
                       mExcerciseHabit = HabitEntry.EXERCISE_NO;
                   } else {
                       mExcerciseHabit = HabitEntry.EXERCISE_UNKNOWN;
                   }
               }
           }

           // Because AdapterView is an abstract class, onNothingSelected must be defined
           @Override
           public void onNothingSelected(AdapterView<?> parent) {
               mExcerciseHabit = HabitEntry.EXERCISE_UNKNOWN;
           }
       });
//--------------------------------------------SELECTING SPINNER VALUE FOR BREAKFAST--------------------------------------------//
        ArrayAdapter breakfastSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_breakfast_options, android.R.layout.simple_spinner_item);

        breakfastSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mBreakfastSpinner.setAdapter(breakfastSpinnerAdapter);

        mBreakfastSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.habit_breakfast_yes))) {
                        mBreakfastHabit = HabitEntry.BREAKFAST_YES;
                    } else if (selection.equals(getString(R.string.habit_breakfast_no))) {
                        mBreakfastHabit = HabitEntry.BREAKFAST_NO;
                    } else {
                        mBreakfastHabit = HabitEntry.BREAKFAST_UNKNOWN;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mBreakfastHabit = HabitEntry.EXERCISE_UNKNOWN;
            }
        });
//--------------------------------------------SELECTING SPINNER VALUE FOR SMOKING--------------------------------------------//
        ArrayAdapter smokingSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_smoking_options, android.R.layout.simple_spinner_item);

        smokingSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mSmokingSpinner.setAdapter(smokingSpinnerAdapter);

        mSmokingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.habit_smoking_yes))) {
                        mSmokingHabit = HabitEntry.SMOKING_YES;
                    } else if (selection.equals(getString(R.string.habit_smoking_no))) {
                        mSmokingHabit = HabitEntry.SMOKING_NO;
                    } else {
                        mSmokingHabit = HabitEntry.SMOKING_UNKNOWN;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mSmokingHabit = HabitEntry.EXERCISE_UNKNOWN;
            }
        });
    }
//---------------------------------------------------------------------------------------------------------------------------//

    private void insertDailyHabits() {
        // Read from input fields
        String dateString = mDateEditText.getText().toString().trim();
        String sleepHrsString = mSleephoursEditText.getText().toString().trim();
        int sleepHrs = Integer.parseInt(sleepHrsString);

        // Create database helper
        HabitsDbHelper mDbHelper = new HabitsDbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and habit attributes are the values.
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_DATE, dateString);
        values.put(HabitEntry.COLUMN_HABIT_EXERCISE, mExcerciseHabit);
        values.put(HabitEntry.COLUMN_HABIT_BREAKFAST, mBreakfastHabit);
        values.put(HabitEntry.COLUMN_HABIT_SMOKING, mSmokingHabit);
        values.put(HabitEntry.COLUMN_NAME_SLEEPHOURS, sleepHrs);

        // Insert a new row for pet in the database, returning the ID of that new row.
        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving habit data", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Habits saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }
//---------------------------------------------------------------------------------------------------------------------------//
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }
//---------------------------------------------------------------------------------------------------------------------------//
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save habits to database
                insertDailyHabits();
                // Exit activity
                finish();
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
