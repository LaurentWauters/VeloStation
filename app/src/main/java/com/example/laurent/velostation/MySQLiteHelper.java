package com.example.laurent.velostation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "velo.db";
    private static final String TABLE_CONTACTS = "velostations";
    private static final int DATABASE_VERSION = 1;

    public MySQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Android expects _id to be the primary key
        String CREATE_VELOS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "(_id INTEGER PRIMARY KEY, naam TEXT, lat TEXT, lon TEXT)";
        db.execSQL(CREATE_VELOS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    public void addVelo(String naam, String lat, String lon) {
        SQLiteDatabase db = this.getWritableDatabase();
        //db.delete(TABLE_CONTACTS, null, null);

        ContentValues values = new ContentValues();
        values.put("naam", naam);
        values.put("lat", lat);
        values.put("lon", lon);

        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    public String getStation(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        /**String tblName 		The table name to compile the query against.
         String[] columnNames 	A list of which table columns to return. Passing "null" will return all columns.
         String whereClause 		Where-clause, i.e. filter for the selection of data, null will select all data.
         String[] selectionArgs 	You may include ?s in the "whereClause"". These placeholders will get replaced by the values from the selectionArgs array.
         String[] groupBy 		A filter declaring how to group rows, null will cause the rows to not be grouped.
         String[] having 			Filter for the groups, null means no filter.
         String[] orderBy 		Table columns which will be used to order the data, null means no ordering.**/

        Cursor cursor = db.query(TABLE_CONTACTS,
                new String[] { "_id", "naam", "lat", "lon"},
                "_id=?",
                new String[] { String.valueOf(id) },
                null, null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

        return cursor.getString(1) + " " + cursor.getString(2);
    }
}