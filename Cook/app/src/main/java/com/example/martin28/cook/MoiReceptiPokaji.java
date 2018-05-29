package com.example.martin28.cook;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Martin28 on 19.5.2017 г..
 */
public class MoiReceptiPokaji extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moi_recepti_pokaji);

        //zadavame imenata na receptite v list view
        final ListView listView = (ListView) findViewById(R.id.listViewMoiReceptiPokaji);

        try {
            //list view
            DBPref pref = new DBPref(this);
            Cursor c = pref.getMine();
            startManagingCursor(c);
            HashMap<String, Bitmap> map = new HashMap<>();
            while (c.moveToNext()) {
                String name = c.getString(c.getColumnIndex("name"));
                byte[] arrByte = c.getBlob(c.getColumnIndex("byteImage"));
                Bitmap bitmap = BitmapFactory.decodeByteArray(arrByte, 0, arrByte.length);
                map.put(name, bitmap);
            }

            final ArrayList<String> arr = new ArrayList<>(map.keySet());
            MyAdapter myAdapter = new MyAdapter(this, R.layout.list_row_recepti, map, arr);
            listView.setAdapter(myAdapter);

            //otvarqne na novo activity sled izbor ot list view
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String asd = arr.get(position);
                    Toast.makeText(getBaseContext(), "Име: " + asd, Toast.LENGTH_SHORT).show();
                    //String SQL = "SELECT * FROM " + DB.TABLE_NAME + " WHERE " + DB.NAME + " = '" + asd + "';";
                    Intent appInfo = new Intent(MoiReceptiPokaji.this, InfoRecepti.class);
                    appInfo.putExtra("STRING_I_NEED", asd);
                    startActivity(appInfo);
                }
            });
        }catch (NullPointerException e){
            Toast.makeText(getBaseContext(), "Нещо се обърка!", Toast.LENGTH_SHORT).show();
        }
    }
}