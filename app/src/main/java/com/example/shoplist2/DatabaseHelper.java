package com.example.shoplist2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String db_name="ShopList.db";
    public static final String table_name="Lists";
    public static final String col_0="id";
    public static final String col_1="list_no";
    public static final String col_2="item_no";
    public static final String col_3="item_name";
    public static final String col_4="price";
    public static final String col_5="quantity";

    public DatabaseHelper(@Nullable Context context) {
        super(context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + table_name + "(" + col_0 + " integer primary key autoincrement, "
                                                      + col_1 + " integer, "
                                                      + col_2 + " integer, "
                                                      + col_3 + " varchar(30), "
                                                      + col_4 + " integer, "
                                                      + col_5 + " integer );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+table_name);
        onCreate(db);
    }

    public boolean insertData(int list_no, int item_no, String name, int quantity, int price) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col_1, list_no);
        contentValues.put(col_2, item_no);
        contentValues.put(col_3, name);
        contentValues.put(col_4, quantity);
        contentValues.put(col_5, price);
        long result=db.insert(table_name, null, contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor viewData(int list_no) {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor result=db.rawQuery("select * from " + table_name + " where " + col_1 + "=" + list_no, null);
        return result;
    }

    public int getListNumber() {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor result=db.rawQuery("select max(" + col_1 + ") from " + table_name, null);
        if(result==null)
            return 1;
        result.moveToFirst();
        return result.getInt(0)+1;
    }
}
