package com.miage.master.myapplication.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Vincent  Destrieux on 13/04/2017.
 */

public class CustomButtonGridView extends GridView {

    public CustomButtonGridView(Context context, AttributeSet attrs) {
        super(context, attrs);

        ResponsiveButton responsiveButton = new ResponsiveButton(context);

        setAdapter(responsiveButton);
    }
}
