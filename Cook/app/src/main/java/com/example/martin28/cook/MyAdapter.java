package com.example.martin28.cook;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Martin28 on 29.5.2017 г..
 */
public class MyAdapter extends ArrayAdapter {
    private HashMap<String,Bitmap> recepti;
    private ArrayList<String> arrNames;
    private Context context;

    public MyAdapter(Context context, int resource, HashMap<String,Bitmap> recepti,ArrayList<String> arrNames) {
        super(context, R.layout.list_row_recepti,arrNames);
        this.context=context;
        this.recepti=recepti;
        this.arrNames=arrNames;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li = LayoutInflater.from(context);
        View row=li.inflate(R.layout.list_row_recepti,parent,false);

        TextView name= (TextView) row.findViewById(R.id.tw_row);
        ImageView image= (ImageView) row.findViewById(R.id.imageView2);
        name.setText(arrNames.get(position));
        image.setImageBitmap(recepti.get(arrNames.get(position)));

        return row;
    }
}
