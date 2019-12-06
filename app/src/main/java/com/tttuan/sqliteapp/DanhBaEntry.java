package com.tttuan.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DanhBaEntry {
    public static final String TABLE_NAME = "danh_ba";
    public static final String COL_ID = "id";
    public static final String COL_TEN = "ten";
    public static final String COL_SDT = "sdt";

    public static final String TAO_BANG = "CREATE TABLE " + TABLE_NAME + " (" +COL_ID + " INTEGER PRIMARY KEY," + COL_TEN + " TEXT," + COL_SDT + " TEXT)";

    public static final String XOA_BANG = "DROP TABLE IF EXIST" + TABLE_NAME;

    private long id;
    private String ten;
    private String sdt;
    private DanhBaDBHelper dbHelper;
    private Context context;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public DanhBaEntry(Context context){
        this.id = 0;
        this.ten = "";
        this.sdt = "";
        this.context = context;
        this.dbHelper = new DanhBaDBHelper(this.context);
    }

    public DanhBaEntry(Context context,String ten, String sdt){
        this.id = 0;
        this.ten = ten;
        this.sdt = sdt;
        this.context = context;
        this.dbHelper = new DanhBaDBHelper(this.context);
    }

    public DanhBaEntry(Context context,long id, String ten, String sdt){
        this.id = id;
        this.ten = ten;
        this.sdt = sdt;
        this.context = context;
        this.dbHelper = new DanhBaDBHelper(this.context);
    }

    public long themDanhBa(){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values =  new ContentValues();
        values.put(DanhBaEntry.COL_TEN, this.ten);
        values.put(DanhBaEntry.COL_SDT, this.sdt);

        long insertedID = sqLiteDatabase.insert(DanhBaEntry.TABLE_NAME, null,values);
        return insertedID;
    }
    public DanhBaEntry timTheoSDT(String sdt){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                DanhBaEntry.COL_ID,
                DanhBaEntry.COL_TEN,
                DanhBaEntry.COL_SDT
        };
        String selection = DanhBaEntry.COL_SDT + " =?";
        String[] selectionArgs = {sdt};

        String sortOrder = DanhBaEntry.COL_TEN + " ASC";

        Cursor cursor = db.query(
                DanhBaEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
        DanhBaEntry dbObj = null;
        while (cursor.moveToNext()){
            int index = cursor.getColumnIndexOrThrow(DanhBaEntry.COL_ID);
            long id = cursor.getLong(index);
            String ten = cursor.getString(cursor.getColumnIndexOrThrow(DanhBaEntry.COL_TEN));
            dbObj = new DanhBaEntry(this.context, id, ten, sdt);
        }
        cursor.close();
        return dbObj;
    }
    public int xoaDanhBa(DanhBaEntry dbObj){
        String selection = DanhBaEntry.COL_SDT + " =?";
        String[] selectionArgs = {dbObj.getSdt()};

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deleteRows = db.delete(
                DanhBaEntry.TABLE_NAME,
                selection,
                selectionArgs
        );
        return deleteRows;
    }
    public int capNhatDanhBa(DanhBaEntry dbObj){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DanhBaEntry.COL_TEN,dbObj.getTen());
        values.put(DanhBaEntry.COL_SDT,dbObj.getSdt());

        String selection = DanhBaEntry.COL_ID + " =?";
        String[] selectionArgs = {Long.toString(dbObj.getId())};

        int updatedRows = db.update(
                DanhBaEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
        return updatedRows;
    }
}
