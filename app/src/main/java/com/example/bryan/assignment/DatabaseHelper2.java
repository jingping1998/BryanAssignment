package com.example.bryan.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper2 extends SQLiteOpenHelper{

    private static final String TableName = "Personal_Details4";
    private static final String COL1 = "ID";
    private static final String COL2 = "Date";
    private static final String COL3 = "Cal";

    public DatabaseHelper2(Context context){
        super(context, TableName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TableName + "(" + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL2 + " TEXT," + COL3 + " TEXT" + ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }

    public void addData(String Date, String Cal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, Date);
        contentValues.put(COL3, Cal);

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
