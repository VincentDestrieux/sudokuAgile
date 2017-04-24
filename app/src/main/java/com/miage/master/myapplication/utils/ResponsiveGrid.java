package com.miage.master.myapplication.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.miage.master.myapplication.vue.Jeu;

/**
 * Created by Vincent Destrieux on 13/04/2017.
 * Classe utilisé dans la redéfinition de la classe GridView dans CustomGridView
 */
public class ResponsiveGrid extends BaseAdapter {

    private Context context;

    /**
     *
     * @param context
     */
    public ResponsiveGrid(Context context) {
        this.context = context;
    }

    /**
     *
     * @return
     */
    @Override
    public int getCount() {
        //Il y a 81 cases dans la grille de sudoku
        return 81;
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
        return 0;
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
        return Jeu.getJeu().getGrid().getItem(cursor);
    }
}
