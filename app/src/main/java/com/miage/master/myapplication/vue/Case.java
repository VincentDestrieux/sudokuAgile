package com.miage.master.myapplication.vue;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;

import com.miage.master.myapplication.R;
import com.miage.master.myapplication.utils.CustomViewCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent Destrieux on 13/04/2017.
 * Cette classe définit la forme des 81 cases de la grille
 */
public class Case extends CustomViewCase {

    //Ce tableau représente les ids des cases qui doivent être grisés.
    private static final int tabID[] = {1, 2, 3, 7, 8, 9, 10, 11, 12, 16, 17, 18, 19, 20, 21, 25,
            26, 27, 31, 32, 33, 40, 41, 42, 49, 50, 51, 55, 56, 57, 61, 62, 63, 64, 65, 66, 70, 71,
            72, 73, 74, 75, 79, 80, 81};

    //Chaque case est un paint
    private Paint mPaint;

    /**
     *
     * @param context
     * @param id
     */
    public Case(Context context, int id) {
        super(context);
        this.id = id;
        mPaint = new Paint();
    }

    /**
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        dessineFonds(canvas);
        dessineLignes(canvas);
        dessineChiffre(canvas);

    }

    /**
     * Cette méthode permet de dessiner les chiffres dans les cases du sudoku,
     * elle est appelée en dernier car les chiffres sont écrit au premier plan.
     * @param canvas
     */
    private void dessineChiffre(Canvas canvas) {
        mPaint.setStyle(Style.FILL);
        if (!getModifiable()) {
            mPaint.setColor(Color.BLACK);
        } else {
            mPaint.setColor(getResources().getColor(R.color.pen));
        }

        mPaint.setTextSize(80);

        //On dessine un rectangle qui contiendra le chiffre
        Rect bounds = new Rect();

        mPaint.getTextBounds(String.valueOf(getValeur()), 0, String.valueOf(getValeur()).length(), bounds);

        if (getValeur() != 0) {
            canvas.drawText(String.valueOf(getValeur()), (getWidth() - bounds.width()) / 2, (getHeight() + bounds.height()) / 2, mPaint);
        }
    }

    /**
     * Cette méthode permet de dessiner les contours de chaque case.
     * @param canvas
     */
    private void dessineLignes(Canvas canvas) {

        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Style.STROKE);

        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
    }

    /**
     * Cette méthode permet de colorier le fonds des cases du sudoku,
     * elle est appelée en premier car le fonds est au second plan.
     * @param canvas
     */
    private void dessineFonds(Canvas canvas) {

        //On place le tableau dans une list pour la parcourir en fonction de l'id en suivant.
        List<Integer> tabId = new ArrayList<Integer>();
        for (int i = 0; i < tabID.length; i++) {
            tabId.add(tabID[i]);
        }

        int id = getId();
        if (tabId.contains(id)) {
            canvas.drawColor(getResources().getColor(R.color.fondGrille));
        }

        /*if (!getModifiable()) {
            canvas.drawColor(getResources().getColor(R.color.fondGrille));
        }*/
    }
}
