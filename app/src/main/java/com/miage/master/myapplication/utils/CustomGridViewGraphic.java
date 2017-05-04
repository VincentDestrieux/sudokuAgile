package com.miage.master.myapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;

import com.miage.master.myapplication.vue.DrawNumber;
import com.miage.master.myapplication.vue.Jeu;

/**
 * Created by azown on 04/05/2017.
 */

public class CustomGridViewGraphic extends CustomGridView
{
    //Internal class for calling StartActivityForResult
    public class Utils extends Activity{
        int value;

        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
        }

        public void getDrawValue()
        {
            Intent i = new Intent(this, DrawNumber.class);
            startActivityForResult(i, 1);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            // Check which request we're responding to
            if (requestCode == 1) {
                // Make sure the request was successful
                if (resultCode == RESULT_OK) {
                    value = data.getIntExtra("result_int",0);
                    if(value>0)
                        Jeu.getJeu().setChiffre(value);
                }
            }
        }
    }

    public CustomGridViewGraphic(final Context context, final AttributeSet attrs)
    {
        super(context, attrs);
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int cursor, long key) {
                openDialog(view);
                int x = cursor % 9;
                int y = cursor / 9;
                Jeu.getJeu().setPositionSelection(x, y);
            }

            public void openDialog(View view) {
                Intent open = new Intent(view.getContext(), DrawSwap.class);
                //Intent open = new Intent(context, DrawNumber.class);
                //view.getContext().startActivity(open);
                context.startActivity(open);
                //myActivity.startTest();
                //myActivity.getDrawValue();
            }
        });
    }
}
