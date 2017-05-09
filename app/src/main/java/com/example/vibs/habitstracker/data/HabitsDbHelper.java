package com.example.vibs.habitstracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vibs.habitstracker.data.HabitContract.HabitEntry;

//Database helper for HabitsTracker app. Manages database creation and version management.
public class HabitsDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = HabitsDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "habits.db";

    /** Database version. If you change the database schema, you must increment the database version. */
    private static final int DATABASE_VERSION = 1;


    public HabitsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // This method is called when the DB is created for the first time.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the dailyhabits table
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_DAILYHABITS_TABLE =  "CREATE TABLE " + HabitContract.HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_HABIT_DATE + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_HABIT_EXCERCISE + " TEXT, "
                + HabitEntry.COLUMN_HABIT_BREAKFAST + " TEXT, "
                + HabitEntry.COLUMN_HABIT_SMOKING + " TEXT, "
                + HabitEntry.COLUMN_NAME_SLEEPHOURS + " INTEGER NOT NULL DEFAULT 0);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_DAILYHABITS_TABLE);
    }

    // This method called when the database needs to be upgraded.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}