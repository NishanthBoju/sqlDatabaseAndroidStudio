package com.example.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "inventory";
    public static final String DB_VERSION = "1";
    public static final String TABLE_NAME = "product";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "BRAND";
    public static final String COL_4 = "COST";
    public static final String COL_5 = "QTY";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, Integer.parseInt(DB_VERSION));
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String productlist = "create table product(id integer primary key autoincrement," +
                "name varchar,brand varchar,cost number,qty number);";
        db.execSQL(productlist);
    }

    public boolean additems(String name, String brand, String cost, String qty) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("brand", brand);
        contentValues.put("cost", cost);
        contentValues.put("qty", qty);
        db.insert("product", null, contentValues);
        db.close();
        return true;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String productList = "drop table if exists product";
        db.execSQL(productList);
        onCreate(db);
    }


    public boolean updateProduct(String id, String name, String brand, String cost, String qty) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, brand);
        contentValues.put(COL_4, cost);
        contentValues.put(COL_5, qty);
        db.update(TABLE_NAME, contentValues, "ID=?", new String[]{id});
        db.close();
        return true;
    }

    public Integer deleteProduct(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME,"ID=?", new String[]{id});

    }

    public Cursor viewAllproduct(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }



}