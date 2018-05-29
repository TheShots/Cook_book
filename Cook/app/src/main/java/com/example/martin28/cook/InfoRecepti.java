package com.example.martin28.cook;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Martin28 on 21.5.2016 г..
 */
public class InfoRecepti extends Activity implements View.OnClickListener{

    TextView imence;
    TextView products;
    TextView time;
    TextView br;
    TextView method;
    ImageView image;

    CheckBox checkBoxFav;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_recepti);

        checkBoxFav = (CheckBox) findViewById(R.id.checkBoxFav);
        checkBoxFav.setOnClickListener(this);

        /*
        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("STRING_I_NEED");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("STRING_I_NEED");
        }
        */

        //vzimame imeto nd izbranata ot nas recepta ot activity Recepti i go izvejdame v tova activity
        imence = (TextView) findViewById(R.id.textView7);
        Bundle extras2 = getIntent().getExtras();
        String newString2 = extras2.getString("STRING_I_NEED");
        imence.setText(newString2);

        //izvejdame produktite za suotvetnata recepta
        DBPref pref = new DBPref(getApplicationContext());
        Cursor c = pref.getProducts(newString2);
        while (c.moveToNext())
        {
            String productsString = c.getString(c.getColumnIndex(DB.PRODUCTS));
            products = (TextView) findViewById(R.id.textView13);
            products.setText(productsString);
        }

        //izvejdame vremeto za suotvetnata recepta
        c = pref.getTime(newString2);
        while (c.moveToNext())
        {
            String timeString = c.getString(c.getColumnIndex(DB.TIME));
            time = (TextView) findViewById(R.id.textView14);
            time.setText(timeString);
        }

        //izvejdame broq porcii za suotvetnata recepta
        c = pref.getNumberOfPortions(newString2);
        while (c.moveToNext())
        {
            String brString = c.getString(c.getColumnIndex(DB.BR));
            br = (TextView) findViewById(R.id.textView15);
            br.setText(brString);
        }

        //izvejdame metoda za prigotvqne na suotvetnata recepta
        c = pref.getMethod(newString2);
        while (c.moveToNext())
        {
            String methodString = c.getString(c.getColumnIndex(DB.METHOD));
            method = (TextView) findViewById(R.id.textView16);
            method.setText(methodString);
        }

        //izvejdame dali e lubima suotvetnata recepta
        c = pref.getIsFavorite(newString2);
        while(c.moveToNext())
        {
            String isFavoriteString = c.getString(c.getColumnIndex(DB.FAVOURITES));
            if (isFavoriteString.equals("Y"))
            {
                checkBoxFav.setChecked(true);
            }
            else
            {
                checkBoxFav.setChecked(false);
            }
        }

        //izvejdame kartinka
        c = pref.getImage(newString2);
        while (c.moveToNext())
        {
            int imageInt = c.getInt(c.getColumnIndex(DB.IMAGE));
            image = (ImageView) findViewById(R.id.imageView);
            image.setImageResource(imageInt);
        }

        //izvejdame byte kartinka
        c = pref.getByteImage(newString2);
        while (c.moveToNext())
        {
            byte[] blob = c.getBlob(c.getColumnIndex(DB.BYTE_IMAGE));
            Bitmap bitmapImage = null;
            if(blob != null){
                bitmapImage = BitmapFactory.decodeByteArray(blob, 0, blob.length);
                image = (ImageView) findViewById(R.id.imageView);
                image.setImageBitmap(bitmapImage);
            }
        }

        //StringBuilder b = new StringBuilder();
        //b.append(c.getString(c.getColumnIndex("name")));

        c.close();
        pref.close();
    }

    //za check box - lubimi
    @Override
    public void onClick(View v) {

        Bundle extras2 = getIntent().getExtras();
        String newString2 = extras2.getString("STRING_I_NEED");
        DBPref pref = new DBPref(getApplicationContext());
        CheckBox t = (CheckBox) v;
        if(t.isChecked()){
            pref.updateFavouriteYES(newString2);
        }
        else{
            pref.updateFavouriteNO(newString2);
        }
    }
}