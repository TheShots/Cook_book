package com.example.martin28.cook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by Martin28 on 10.5.2016 г..
 */
public class DBPref extends DB {
    public DBPref (Context context) {
        super(context);
    }


    public void addRecord(String name, String time, String br, String products, String method, byte[] byteImage){
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("time", time);
        cv.put("br", br);
        cv.put("products", products);
        cv.put("method", method);
        cv.put("favourites", "N");
        cv.put("mine", "Y");
        cv.put("byteImage", byteImage);
        this.db.insert("recepti", null, cv);
    }

    /*
    public Cursor getVals2(String imeNaReceptata){
        return this.db.query("recepti", new String[]{"_id", "name", "time", "br", "products", "method", "favourites"}, DB.NAME + " = " + imeNaReceptata, null, null, null, null);
    }
    */

    //vzimame stoinostite ot bazata danni - select zaqvka
    public Cursor getVals(){
        return this.db.query("recepti", new String[]{"_id", "name", "time", "br", "products", "method", "favourites", "image"}, DB.MINE + "=?", new String[]{"N"}, null, null, null);
    }

    //vzimame produkti
    public Cursor getProducts(String recipeName){
        return this.db.query("recepti", new String[]{"products"}, DB.NAME + "=?", new String[]{recipeName}, null, null, null,null);
    }

    //vzimame vremeto
    public Cursor getTime(String recipeName){
        return this.db.query("recepti", new String[]{"time"}, DB.NAME + "=?", new String[]{recipeName}, null, null, null,null);
    }

    //vzimame broq na porciite
    public Cursor getNumberOfPortions(String recipeName){
        return this.db.query("recepti", new String[]{"br"}, DB.NAME + "=?", new String[]{recipeName}, null, null, null,null);
    }

    //vzimame metoda na prigotvqne
    public Cursor getMethod(String recipeName){
        return this.db.query("recepti", new String[]{"method"}, DB.NAME + "=?", new String[]{recipeName}, null, null, null,null);
    }

    //vzimame dali e lubima receptata
    public Cursor getIsFavorite(String recipeName){
        return this.db.query("recepti", new String[]{"favourites"}, DB.NAME + "=?", new String[]{recipeName}, null, null, null,null);
    }

    //vzimame kartinka
    public Cursor getImage(String recipeName){
        return this.db.query("recepti", new String[]{"image"}, DB.NAME + "=?", new String[]{recipeName}, null, null, null,null);
    }

    //vzimane na byte kartinka
    public Cursor getByteImage(String recipeName){
        return this.db.query("recepti", new String[]{"byteImage"}, DB.NAME + "=?", new String[]{recipeName}, null, null, null,null);
    }

    //vzimame stoinostite ot bazata danni - lubimi recepti
    public Cursor getFavourites(){
        return this.db.query("recepti", new String[]{"_id", "name", "time", "br", "products", "method", "favourites", "image"}, DB.FAVOURITES + "=?", new String[]{"Y"}, null, null, null);
    }

    //vzimame stoinostite ot bazata danni - moi recepti
    public Cursor getMine(){
        return this.db.query("recepti", new String[]{"_id", "name", "time", "br", "products", "method", "favourites", "byteImage"}, DB.MINE + "=?", new String[]{"Y"}, null, null, null);
    }

    //obnovqvame poleto za lubimi s Yes
    public void updateFavouriteYES(String recipeName){
        String updateQuery2 = "UPDATE " + TABLE_NAME + " SET " + FAVOURITES + " = 'Y' WHERE " + NAME + " = '" + recipeName + "' ;";
        this.db.execSQL(updateQuery2);
    }

    //obnovqvame poleto za lubimi s No
    public void updateFavouriteNO(String recipeName){
        String updateQuery2 = "UPDATE " + TABLE_NAME + " SET " + FAVOURITES + " = 'N' WHERE " + NAME + " = '" + recipeName + "' ;";
        this.db.execSQL(updateQuery2);
    }


}