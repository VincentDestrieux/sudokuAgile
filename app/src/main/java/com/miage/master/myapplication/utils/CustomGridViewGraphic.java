package com.miage.master.myapplication.utils;

import android.content.Context;
import android.content.Intent;
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
    //throwing value
    /*public class DrawReceiver extends BroadcastReceiver
    {
        public void onReceive(Context DrContext, Intent DrIntent)
        {
            int value = DrIntent.getIntExtra("result_int",0);
            if(value>0)
                Jeu.getJeu().setChiffre(value);
        }
    }*/

    /*private BroadcastReceiver receiver = new BroadcastReceiver(){

        public void onReceive(Context DrContext, Intent DrIntent)
        {
            System.out.println("received");
            int value = DrIntent.getIntExtra("result_int",0);
            if(value>0)
                Jeu.getJeu().setChiffre(value);
        }
    };*/

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
                //Intent open = new Intent(view.getContext(), DrawSwap.class);
                Intent open = new Intent(view.getContext(), DrawNumber.class);
                //view.getContext().startActivity(open);
                context.startActivity(open);
                //myActivity.startTest();
                //myActivity.getDrawValue();
            }
        });
    }
}
