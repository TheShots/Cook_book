package com.example.martin28.cook;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by Martin28 on 7.5.2016 г..
 */
public class Lubimi extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lubimi);

        //zadavame imenata na receptite v list view
        final ListView listView = (ListView) findViewById(R.id.listView2);

        //otvarqne na novo activity sled izbor ot list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor c = ((SimpleCursorAdapter)listView.getAdapter()).getCursor();
                c.moveToPosition(position);
                String asd = c.getString(c.getColumnIndex(DB.NAME));
                Toast.makeText(getBaseContext(), "Име: " + asd, Toast.LENGTH_SHORT).show();
                //String SQL = "SELECT * FROM " + DB.TABLE_NAME + " WHERE " + DB.NAME + " = '" + asd + "';";
                Intent appInfo = new Intent(Lubimi.this, InfoRecepti.class);

                appInfo.putExtra("STRING_I_NEED", asd);
                startActivity(appInfo);
            }
        });

        //list view
        DBPref pref = new DBPref(this);
        Cursor c = pref.getFavourites();//
        startManagingCursor(c);
        String[] from = new String[]{DB.NAME, DB.IMAGE};
        int[] to = new int[]{R.id.tw_row, R.id.imageView2};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.list_row_recepti, c, from, to);
        listView.setAdapter(adapter);
    }
}

