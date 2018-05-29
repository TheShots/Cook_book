package com.example.martin28.cook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Martin28 on 7.5.2016 г..
 */
public class MoiRecepti extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moi_recepti);
    }

    public void openDobaviNova (View v){
        if(v.getId() == R.id.dobaviNovaBtn){
            Intent i = new Intent(MoiRecepti.this, MoiReceptiDobavi.class);
            startActivity(i);
        }
    }

    public void openPokajiMoi (View v){
        if(v.getId() == R.id.pokajiMoiBtn){
            Intent i = new Intent(MoiRecepti.this, MoiReceptiPokaji.class);
            startActivity(i);
        }
    }
}
