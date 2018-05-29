package com.example.martin28.cook;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;

/**
 * Created by Martin28 on 11.5.2016 г..
 */
public class MoiReceptiDobavi extends FragmentActivity implements View.OnClickListener {

    public static final int REQUEST_CAPTURE = 1;
    ImageView resultPhoto;

    Button btnZapishi;
    Button btnNapraviSnimka;

    EditText ime;
    EditText vreme;
    EditText broi;
    TextView produkti;
    EditText nachin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moi_recepti_dobavi);

        ime = (EditText) findViewById(R.id.editTextIme);
        vreme = (EditText) findViewById(R.id.editTextVreme);
        broi = (EditText) findViewById(R.id.editTextBroi);
        produkti = (TextView) findViewById(R.id.editTextProdukti);
        nachin = (EditText) findViewById(R.id.editTextNachin);

        btnZapishi = (Button) findViewById(R.id.zapishiBtn);
        btnZapishi.setOnClickListener(this);

        btnNapraviSnimka = (Button) findViewById(R.id.napraviSnimkaBtn);
        resultPhoto = (ImageView) findViewById(R.id.imageViewPhoto);
        resultPhoto.setImageResource(R.drawable.blank);
        if(!hasCamera()){
            btnNapraviSnimka.setEnabled(true);
        }
    }

    public boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    public void takePhoto(View v){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (requestCode == REQUEST_CAPTURE || requestCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap photo = (Bitmap) extras.get("data");
                resultPhoto.setImageBitmap(photo);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public void selectProducts(View v)
    {
        MultiSelection multi = new MultiSelection();
        multi.show(getFragmentManager(), "multi");
    }

    @Override
    public void onClick(View v) {

        //zapisvame podadenite stoinosti v poletata v bazata danni
        if(v.getId() == R.id.zapishiBtn) {

            try {
                Bitmap bitmap = ((BitmapDrawable) resultPhoto.getDrawable()).getBitmap();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                byte[] buffer = outputStream.toByteArray();

                DBPref pref = new DBPref(getApplicationContext());
                pref.addRecord(ime.getText().toString(),
                               vreme.getText().toString(),
                               broi.getText().toString(),
                               produkti.getText().toString(),
                               nachin.getText().toString(),
                               buffer);

                Intent i = new Intent(MoiReceptiDobavi.this, MoiRecepti.class);
                startActivity(i);
                Toast.makeText(getBaseContext(), "Беше добавена нова рецепта: " + ime.getText().toString(), Toast.LENGTH_SHORT).show();

            }catch (Exception e){
                Toast.makeText(getBaseContext(), "Опитайте отново!", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
        //vzimame suotvetnite rezultati ot bazata danni
        else {
            DBPref pref = new DBPref(getApplicationContext());
            Cursor c = pref.getVals();
            StringBuilder b = new StringBuilder();
            if (c.moveToFirst()){
                do {
                    b.append(c.getString(c.getColumnIndex("name")));
                    b.append(" , ");
                    b.append(c.getString(c.getColumnIndex("time")));
                    b.append(" , ");
                    b.append(c.getString(c.getColumnIndex("br")));
                    b.append(" , ");
                    b.append(c.getString(c.getColumnIndex("products")));
                    b.append(" , ");
                    b.append(c.getString(c.getColumnIndex("method")));
                    b.append(" , ");
                    b.append(c.getString(c.getColumnIndex("image")));
                    b.append(" , ");

                    /*
                    b.append(c.getString(c.getColumnIndex("favourites")));
                    b.append(" , ");

                    b.append(c.getString(c.getColumnIndex("mine")));
                    b.append(" , ");
                    */

                }while (c.moveToNext());
            }c.close();
            pref.close();
        }
    }
}