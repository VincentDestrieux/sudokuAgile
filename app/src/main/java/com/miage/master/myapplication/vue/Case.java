package com.miage.master.myapplication.vue;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;

import com.miage.master.myapplication.utils.CustomViewCase;

/**
 * Created by Vincent Destrieux on 13/04/2017.
 * Cette classe permet de creer les 81 cases de la grille
 */
public class Case extends CustomViewCase {

    //Chaque case est un paint
    private Paint mPaint;

    public Case(Context context) {
        super(context);
        mPaint = new Paint();
    }

    /**
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        dessineChiffre(canvas);
        dessineLignes(canvas);
    }

    /**
     *
     * @param canvas
     */
    private void dessineChiffre(Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(80);
        mPaint.setStyle(Style.FILL);

        //On dessine un rectangle qui contiendra le chiffre
        Rect bounds = new Rect();
        mPaint.getTextBounds(String.valueOf(getValeur()), 0, String.valueOf(getValeur()).length(), bounds);

        if (getValeur() != 0) {
            canvas.drawText(String.valueOf(getValeur()), (getWidth() - bounds.width()) / 2, (getHeight() + bounds.height()) / 2, mPaint);
        }
    }

    /**
     *
     * @param canvas
     */
    private void dessineLignes(Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Style.STROKE);

        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
    }

}
