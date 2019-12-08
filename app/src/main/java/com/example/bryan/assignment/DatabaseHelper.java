package com.example.bryan.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String TableName = "Personal_Details1";
    private static final String COL1 = "ID";
    private static final String COL2 = "Height";
    private static final String COL3 = "Weight";
    private static final String COL4 = "Age";
    private static final String COL5 = "Gender";
    private static final String COL6 = "Calories";

    public DatabaseHelper(Context context){
        super(context, TableName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TableName + "(" + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL2 + " TEXT," + COL3 + " TEXT," + COL4 + " TEXT," + COL5 + " TEXT," + COL6 + " TEXT" + ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }

    public void addData(String Height, String Weight, String Age, String Gender, String Calories) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, Height);
        contentValues.put(COL3, Weight);
        contentValues.put(COL4, Age);
        contentValues.put(COL5, Gender);
        contentValues.put(COL6, Calories);

        Log.d("lol",Height);

        db.insert(TableName, null, contentValues);
        db.close();
    }

    public Cursor getInfo(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TableName;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void deleteInfo(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TableName + " WHERE " + COL1 + " = '" + id + "'";
        db.execSQL(query);
    }
}
