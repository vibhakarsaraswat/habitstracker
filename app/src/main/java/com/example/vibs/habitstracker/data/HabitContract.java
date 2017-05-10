package com.example.vibs.habitstracker.data;

import android.provider.BaseColumns;

/**
 * Created by vibhakar.sarswat on 5/9/2017.
 */

public class HabitContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public HabitContract() {
    }

    // Inner class that defines constant values for the dailyhabits database table.
    // Each entry in the table represents User Habits for a single Day.
    public static final class HabitEntry implements BaseColumns {

        /** Name of database table for User Habits */
        public final static String TABLE_NAME = "dailyhabits";

        // Unique ID number for the pet (only for use in the database table).
        // Type: INTEGER
        public final static String _ID = BaseColumns._ID;

        // Date: date to records habits for a single day.
        // Type: INTEGER
        public final static String COLUMN_HABIT_DATE ="date";

        /**
         * Exercise: The only possible values are:
         * {@link #EXERCISE_NO}, {@link #EXERCISE_YES} or {@link #EXERCISE_UNKNOWN}
         *
         * Type: INTEGER
         */
        public final static String COLUMN_HABIT_EXERCISE ="exercise";

        /**
         * Breakfast: The only possible values are:
         * {@link #BREAKFAST_NO}, {@link #BREAKFAST_YES} or {@link #BREAKFAST_UNKNOWN}
         *
         * Type: INTEGER
         */
        public final static String COLUMN_HABIT_BREAKFAST ="breakfast";

        /**
         * Smoking: The only possible values are:
         * {@link #SMOKING_NO}, {@link #SMOKING_YES} or {@link #SMOKING_UNKNOWN}
         * Type: INTEGER
         */
        public final static String COLUMN_HABIT_SMOKING ="smoking";

        // SleepHours: No of hours user slept in a single day.
        // Type: INTEGER
        public static final String COLUMN_NAME_SLEEPHOURS = "sleepHours";


        // Possible values for the exercise habit.
        public static final int EXERCISE_NO = 0;
        public static final int EXERCISE_YES = 1;
        public static final int EXERCISE_UNKNOWN = 2;

        // Possible values for the breakfast habit.
        public static final int BREAKFAST_NO = 0;
        public static final int BREAKFAST_YES = 1;
        public static final int BREAKFAST_UNKNOWN = 2;

        // Possible values for the smoking habit.
        public static final int SMOKING_NO = 0;
        public static final int SMOKING_YES = 1;
        public static final int SMOKING_UNKNOWN = 2;
    }
}