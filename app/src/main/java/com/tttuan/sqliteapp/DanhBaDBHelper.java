package com.tttuan.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;

public class DanhBaDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "DanhBaDB.db";

    public DanhBaDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DanhBaEntry.TAO_BANG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DanhBaEntry.XOA_BANG);

        onCreate(sqLiteDatabase);
    }
//    public long themDanhBa(DanhBaEntry danhBaEntry){
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues values =  new ContentValues();
//        values.put(DanhBaEntry.COL_TEN, danhBaEntry.getTen());
//        values.put(DanhBaEntry.COL_SDT, danhBaEntry.getSdt());
//
//        long insertedID = sqLiteDatabase.insert(DanhBaEntry.TABLE_NAME, null,values);
//        return insertedID;
//    }
//    public DanhBaEntry timTheoSDT(String sdt){
//        SQLiteDatabase db = this.getReadableDatabase();
//        String[] projection = {
//                DanhBaEntry.COL_ID,
//                DanhBaEntry.COL_TEN,
//                DanhBaEntry.COL_SDT
//        };
//        String selection = DanhBaEntry.COL_SDT + " =?";
//        String[] selectionArgs = {sdt};
//
//        String sortOrder = DanhBaEntry.COL_TEN + " ASC";
//
//        Cursor cursor = db.query(
//                DanhBaEntry.TABLE_NAME,
//                projection,
//                selection,
//                selectionArgs,
//                null,
//                null,
//                sortOrder
//        );
//        DanhBaEntry dbObj = null;
//        while (cursor.moveToNext()){
//            int index = cursor.getColumnIndexOrThrow(DanhBaEntry.COL_ID);
//            long id = cursor.getLong(index);
//            String ten = cursor.getString(cursor.getColumnIndexOrThrow(DanhBaEntry.COL_TEN));
//            String SDT = cursor.getString(cursor.getColumnIndexOrThrow(DanhBaEntry.COL_SDT));
//            dbObj = new DanhBaEntry(id,ten,SDT);
//        }
//        cursor.close();
//        return dbObj;
//    }
//    public int xoaDanhBa(DanhBaEntry dbObj){
//        String selection = DanhBaEntry.COL_SDT + " =?";
//        String[] selectionArgs = {dbObj.getSdt()};
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        int deleteRows = db.delete(
//                DanhBaEntry.TABLE_NAME,
//                selection,
//                selectionArgs
//        );
//        return deleteRows;
//    }
//    public int capNhatDanhBa(DanhBaEntry dbObj){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(DanhBaEntry.COL_TEN,dbObj.getTen());
//        values.put(DanhBaEntry.COL_SDT,dbObj.getSdt());
//
//        String selection = DanhBaEntry.COL_ID + " =?";
//        String[] selectionArgs = {Long.toString(dbObj.getId())};
//
//        int updatedRows = db.update(
//                DanhBaEntry.TABLE_NAME,
//                values,
//                selection,
//                selectionArgs
//        );
//        return updatedRows;
//    }
}
