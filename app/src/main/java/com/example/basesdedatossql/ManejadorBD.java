package com.example.basesdedatossql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ManejadorBD extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "moviles.db";

    private static final String COL_ID = "ID";
    private static final String COL_MODELO = "MODELO";
    private static final String COL_MARCA = "MARCA";
    private static final String COL_PRECIO = "PRECIO";

    private static final String TABLE_NAME = "MOVILES";

    public ManejadorBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, 1);
    }

    public ManejadorBD(MainActivity mainActivity) {
        super(mainActivity, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_MODELO + " TEXT, " + COL_MARCA + " TEXT, " + COL_PRECIO + " TEXT " + ")");


    }

    public boolean insertar(String modelo, String marca, String precio) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_MARCA, marca);
        contentValues.put(COL_MODELO, modelo);
        contentValues.put(COL_PRECIO, precio);

        long resultado = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return (resultado != -1);

    }

    boolean borrar(String id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        int borradas = sqLiteDatabase.delete(TABLE_NAME, COL_ID + "=?", new String[]{id});

        sqLiteDatabase.close();

        return (borradas > 0);
    }

    Cursor listar(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return cursor;

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean actualizar(String id, String modelo, String marca, String precio) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_MARCA, marca);
        contentValues.put(COL_MODELO, modelo);
        contentValues.put(COL_PRECIO, precio);

        long resultado = db.update(TABLE_NAME, contentValues, COL_ID+"=?", new String[]{id});

        db.close();
        return (resultado>0);

    }

}
