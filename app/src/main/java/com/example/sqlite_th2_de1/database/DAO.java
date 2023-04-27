package com.example.sqlite_th2_de1.database;

import static com.example.sqlite_th2_de1.database.MyDatabaseHelper.DATABASE_NAME;
import static com.example.sqlite_th2_de1.database.MyDatabaseHelper.TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Pair;

import com.example.sqlite_th2_de1.model.Tour;
import com.example.sqlite_th2_de1.model.TourAndID;

import java.util.ArrayList;
import java.util.List;

public class DAO {

    private SQLiteDatabase database;
    private MyDatabaseHelper dbHelper;
    private String[] allColumns = {
            MyDatabaseHelper.COLUMN_0,
            MyDatabaseHelper.COLUMN_1,
            MyDatabaseHelper.COLUMN_2,
            MyDatabaseHelper.COLUMN_3,
            MyDatabaseHelper.COLUMN_4,
            MyDatabaseHelper.COLUMN_5
    };

    public DAO(Context context) {
        dbHelper = new MyDatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void add(Tour tour) {
        open();
        ContentValues values = new ContentValues();
        values.put(MyDatabaseHelper.COLUMN_1, tour.getField1());
        values.put(MyDatabaseHelper.COLUMN_2, tour.getField2());
        values.put(MyDatabaseHelper.COLUMN_3, tour.getField3());
        values.put(MyDatabaseHelper.COLUMN_4, tour.getField4());
        values.put(MyDatabaseHelper.COLUMN_5, tour.getField5());
        database.insert(TABLE_NAME, null, values);
        close();
    }

    public void update(Tour tour, int id) {
        open();
        ContentValues values = new ContentValues();
        values.put(MyDatabaseHelper.COLUMN_1, tour.getField1());
        values.put(MyDatabaseHelper.COLUMN_2, tour.getField2());
        values.put(MyDatabaseHelper.COLUMN_3, tour.getField3());
        values.put(MyDatabaseHelper.COLUMN_4, tour.getField4());
        values.put(MyDatabaseHelper.COLUMN_5, tour.getField5());

        String whereClause = MyDatabaseHelper.COLUMN_0 + " = ?";
        String[] whereArgs = { String.valueOf(id) };
        database.update(TABLE_NAME, values, whereClause, whereArgs);
        close();
    }

    public void delete(int id) {
        open();
        String whereClause = MyDatabaseHelper.COLUMN_0 + " = ?";
        String[] whereArgs = { String.valueOf(id) };
        database.delete(TABLE_NAME, whereClause, whereArgs);
        close();
    }

    public List<TourAndID> getAll() {
        open();
        List<TourAndID> tours = new ArrayList<>();
        Cursor cursor = database.query(TABLE_NAME, allColumns,
                null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Tour tour = new Tour();
            tour.setField1(cursor.getString(1));
            tour.setField2(cursor.getString(2));
            tour.setField3(cursor.getString(3));
            tour.setField4(cursor.getString(4));
            tour.setField5(cursor.getLong(5));
            int id = cursor.getInt(0);
            tours.add(new TourAndID(id, tour));
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return tours;
    }

    public List<TourAndID> getAll(String searchString) {
        open();
        List<TourAndID> tours = new ArrayList<>();
        Cursor cursor = database.query(TABLE_NAME, allColumns, "field3 LIKE ?",
                new String[]{"%" + searchString + "%"}, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Tour tour = new Tour();
            tour.setField1(cursor.getString(1));
            tour.setField2(cursor.getString(2));
            tour.setField3(cursor.getString(3));
            tour.setField4(cursor.getString(4));
            tour.setField5(cursor.getLong(5));
            int id = cursor.getInt(0);
            tours.add(new TourAndID(id, tour));
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return tours;
    }

    public List<Pair<String, Long>> getYearlyTotal() {
        List<Pair<String, Long>> result = new ArrayList<>();

        open();

        String query = "SELECT strftime('%Y', date(field4)) AS year, SUM(field5) AS total "
                + "FROM " + TABLE_NAME + " "
                + "GROUP BY year "
                + "ORDER BY total DESC";
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String year = cursor.getString(0);
                Long total = cursor.getLong(1);

                result.add(new Pair<>(year, total));
            } while (cursor.moveToNext());
        }

        cursor.close();
        close();

        return result;
    }


}

