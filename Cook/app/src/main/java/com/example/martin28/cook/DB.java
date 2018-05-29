package com.example.martin28.cook;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Martin28 on 9.5.2016 г..
 */
//klas, koito se griji za vzimaneto na vruzkata s BD
public class DB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "my_database.db";
    private static final int DATABASE_VERSION = 1;
    protected SQLiteDatabase db;
    public static final String TABLE_NAME = "recepti";
    public static final String ID = "_id";
    public static final String NAME = "name";               //Iime na receptata
    public static final String TIME = "time";               //vreme za prigotvqne
    public static final String BR = "br";                   //broi porcii
    public static final String PRODUCTS = "products";       //produkti za qstieto
    public static final String METHOD = "method";           //nachin na prigotvqne
    public static final String FAVOURITES = "favourites";   //lubimi qstiq
    public static final String MINE = "mine";               //moi qstiq
    public static final String IMAGE = "image";             //izobrajenie na qstieto
    public static final String BYTE_IMAGE = "byteImage";    //izobrajenie polucheno ot kamerata

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME + " VARCHAR(255), "
            + TIME + " VARCHAR(225), "
            + BR + " UNSIGNED TINYINT, "
            + PRODUCTS + " TEXT, "
            + METHOD + " MEDIUMTEXT, "
            + FAVOURITES + " VARCHAR(225), "
            + MINE + " VARCHAR(225), "
            + IMAGE + " INTEGER, "
            + BYTE_IMAGE + " BLOB);";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXIST" + TABLE_NAME;

    public DB (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        open();
    }

    //suzdavame baza danni i vkarvame purvonachalni danni
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        ContentValues cv = new ContentValues();
        cv.put("name", "Смути от банан и ананас");
        cv.put("time", "10 мин.");
        cv.put("br", 2);
        cv.put("products", "1 банан, 1 портокал, 1/4 ананас, 1/2 ч.л. канела");
        cv.put("method", "1. Изцедете портокала. След това почистете ананаса и нарежете 1/4 от него на едри парчета." +
                "2. Сложете портокаловия сок, парчетата ананас и обеления банан в шейкър и разбийте до получаване на гладка смес." +
                "Може да използвате блендер или кухненски робот за приготвянето на смути. Изсипете в чаши и преди сервиране поръсвате с канела.");
        cv.put("favourites", "N");
        cv.put("mine", "N");
        cv.put("image", R.drawable.smuti);
        db.insert(DB.TABLE_NAME,null,cv);

        cv.put("name", "Плодова салата");
        cv.put("time", "15 мин.");
        cv.put("br", 4);
        cv.put("products", "1 банан, 1/4 ананас, 1/2 манго, 2 киви, 1 портокал, 1 с.л. мед, 2 с.л. ром, 5 орех");
        cv.put("method", "1. Обелете всички плодове, освен портокала и ги нарежете на еднакви кубчета." +
                         "2. Изцедете сока на портокала, добавете меда и рома и разбърквайте докато се разтопи меда напълно." +
                         "3. Сложете нарязаните плодове в купички или чаши и ги полейте с получения сос. Преди сервиране ги поръсете със смелени орехи.");
        cv.put("favourites", "N");
        cv.put("mine", "N");
        cv.put("image", R.drawable.plodova_salata);
        db.insert(DB.TABLE_NAME,null,cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("LOG_TAG", "Upgrading DB from version " + oldVersion + " to version " + newVersion);

        //iztrivame predxodnata tablica pri obnovqvavaneto
        db.execSQL(SQL_DELETE_ENTRIES);

        //suzdavame nov ekzemplqr ot tablicata
        onCreate(db);
    }

    public void open() throws SQLException {
        db = getWritableDatabase();
    }

    public void close() {
        db.close();
    }
}