package com.example.martin28.cook;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicializirame spomagatelniq klas
        DB mydb = new DB(this);

        //otvarqme BD za chetene i zapis
        SQLiteDatabase sqdb = mydb.getWritableDatabase();

        //zatvarqme vruzkata s BD
        sqdb.close();
        mydb.close();
    }

    public void openRecepti(View v)
    {
        if(v.getId() == R.id.receptiBtn)
        {
            Intent i = new Intent(MainActivity.this, Recepti.class);
            startActivity(i);
        }
    }


    public void openMoiRecepti(View v)
    {
        if(v.getId() == R.id.moiBtn)
        {
            Intent i = new Intent(MainActivity.this, MoiRecepti.class);
            startActivity(i);
        }
    }


    public void openLubimi(View v)
    {
        if(v.getId() == R.id.lubimiBtn)
        {
            Intent i = new Intent(MainActivity.this, Lubimi.class);
            startActivity(i);
        }
    }


    public void openSpisuk(View v)
    {
        if(v.getId() == R.id.spisukBtn)
        {
            Intent i = new Intent(MainActivity.this, Spisuk.class);
            startActivity(i);
        }
    }


    public void openImam(View v)
    {
        if(v.getId() == R.id.imamBtn)
        {
            Intent i = new Intent(MainActivity.this, Imam.class);
            startActivity(i);
        }
    }


}
