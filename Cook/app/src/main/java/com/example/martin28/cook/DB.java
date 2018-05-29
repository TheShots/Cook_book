package com.example.martin28.cook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Martin28 on 9.5.2016 г..
 */

public class DB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "my_database.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "recepti";
    public static final String ID = "id";
    public static final String NAME = "name";               //Iime na receptata
    public static final String TIME = "time";               //vreme za prigotvqne
    public static final String BR = "br";                   //broi porcii
    public static final String PRODUCTS = "products";       //produkti za qstieto
    public static final String METHOD = "method";           //nachin na prigotvqne
    public static final String FAVOURITES = "favourites";   //lubimi qstiq
    public static final String MINE = "mine";               //moi qstiq
    public static final String IMAGE = "image";             //izobrajenie na qstieto

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE" + TABLE_NAME + " (" + ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME + " VARCHAR(255), "
            + TIME + "VARCHAR(225), "
            + BR + "UNSIGNED TINYINT, "
            + PRODUCTS + "TEXT, "
            + METHOD + "MEDIUMTEXT, "
            + FAVOURITES + "BOOLEAN, "
            + MINE + "BOOLEAN, "
            + IMAGE + "IMAGE);";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXIST" + TABLE_NAME;

    public DB (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("LOG_TAG", "Upgrading DB from version " + oldVersion + " to version " + newVersion);

        //iztrivame predxodnata tablica pri obnovqvavaneto
        db.execSQL(SQL_DELETE_ENTRIES);

        //suzdavame nov ekzemplqr ot tablicata
        onCreate(db);
    }
}

