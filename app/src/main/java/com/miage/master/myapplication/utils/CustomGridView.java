package com.miage.master.myapplication.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.miage.master.myapplication.vue.Jeu;

/**
 * Created by Vincent  Destrieux on 13/04/2017.
 * Inspiré du code de  marcellelek :
 * https://github.com/marcellelek/Sudoku/blob/master/src/com/marcellelek/sudoku/view/sudokugrid/SudokuGridView.java
 * Classe surchargée pour afficher la grille.
 */

public class CustomGridView extends GridView {

    private Context context;

    /**
     *
     * @param context
     * @param attrs
     */
    public CustomGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        ResponsiveGrid responsiveGrid = new ResponsiveGrid(context);
        setAdapter(responsiveGrid);
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int cursor, long key) {
                int x = cursor % 9;
                int y = cursor / 9;
                Jeu.getJeu().setSelectedPos(x, y);
            }
        });
    }

    /**
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

}
