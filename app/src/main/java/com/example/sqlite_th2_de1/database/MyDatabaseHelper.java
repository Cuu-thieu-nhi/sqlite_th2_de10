package com.example.sqlite_th2_de1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "tour_db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "dbtable";

    public static final String COLUMN_0 = "id";
    public static final String COLUMN_1 = "field1";
    public static final String COLUMN_2 = "field2";
    public static final String COLUMN_3 = "field3";
    public static final String COLUMN_4 = "field4";
    public static final String COLUMN_5 = "field5";


    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_0 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_1 + " TEXT, " +
                    COLUMN_2 + " TEXT, " +
                    COLUMN_3 + " TEXT, " +
                    COLUMN_4 + " TEXT, " +
                    COLUMN_5 + " LONG);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
