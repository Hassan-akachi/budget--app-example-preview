package com.example.android.planit.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PlanitDbHelper extends SQLiteOpenHelper {
    /** Name of the database file */

    public final static String DATABASE_NAME ="planit.db";
    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    public final static int DATABASE_VERSION =1;

    public PlanitDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_PLANIT_TABLE =  "CREATE TABLE " + planitContract.planitEntry.TABLE_NAME + " ("
                + planitContract.planitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +planitContract.planitEntry.COLUMN_BUDGET_NAME + " INTEGER NOT NULL, "
                + planitContract.planitEntry.COLUMN_BUDGET_AMOUNT + " INTEGER NOT NULL, "
                + planitContract.planitEntry.COLUMN_TOTAL_AMOUNT + " INTEGER , "
                + planitContract.planitEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + planitContract.planitEntry.COLUMN_QUANTITY + " INTEGER , "
                + planitContract.planitEntry.COLUMN_AMOUNT + " INTEGER , "
                + planitContract.planitEntry.COLUMN_TOTAL + " INTEGER DEFAULT 0); ";
        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PLANIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
