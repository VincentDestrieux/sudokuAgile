package com.miage.master.myapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.miage.master.myapplication.R;

/**
 * Created by Vincent  Destrieux on 13/04/2017.
 */
public class ResponsiveButton extends BaseAdapter {

    private Context context;

    public ResponsiveButton(Context context) {
        this.context = context;
    }

    /**
     *
     * @return
     */
    //Nombre de bouton (1,2,3,4,5,6,7,8,9,remise a zero)
    @Override
    public int getCount() {
        return 10;
    }

    /**
     *
     * @param cursor
     * @return
     */
    @Override
    public Object getItem(int cursor) {
        return null;
    }

    /**
     *
     * @param cursor
     * @return
     */
    @Override
    public long getItemId(int cursor) {
        return cursor;
    }


    /**
     *
     * @param cursor
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int cursor, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
            view = layoutInflater.inflate(R.layout.activity_buttongrille, viewGroup, false);

            LabelButton labelButton = (LabelButton) view;
            labelButton.setTextSize(20);
            labelButton.setId(cursor);

            if (cursor != 9) {
                labelButton.setText(String.valueOf(cursor + 1));
                labelButton.setLabel(cursor + 1);
            } else {
                labelButton.setText("Vider");
                labelButton.setLabel(0);
            }
            return labelButton;
        }

        return view;
    }
}
