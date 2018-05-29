package com.example.martin28.cook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by Martin28 on 14.5.2016 г..
 */
public class MultiSelection extends DialogFragment {

    private String selections = "";
    ArrayList<String> list = new ArrayList<String>();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final String[] items = getResources().getStringArray(R.array.my_products_selection);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Изберете продукти").setMultiChoiceItems(R.array.my_products_selection, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked)
                {
                    list.add(items[which]);
                }
                else if (list.contains(items[which]))
                {
                    list.remove(items[which]);
                }
            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                for (String ms : list)
                {
                    selections = selections + "\n" + ms;
                }
                Toast.makeText(getActivity(), "Избрани продукти: " + selections, Toast.LENGTH_SHORT).show();
                selections = selections.replace("\n",", ");
                TextView tv = (TextView) getActivity().findViewById(R.id.editTextProdukti);
                tv.setText(selections);
            }

        });

        return builder.create();
    }
}