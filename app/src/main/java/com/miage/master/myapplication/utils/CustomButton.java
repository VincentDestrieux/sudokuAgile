package com.miage.master.myapplication.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;

import com.miage.master.myapplication.R;

/**
 * Created by Vincent  Destrieux on 03/04/2017.
 * Classe surchargée pour ajouter l'option de modifier la typographie d'un bouton.
 */
public class CustomButton extends android.support.v7.widget.AppCompatButton {

    /**
     *
     * @param context
     * @param attrs
     */
    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        //Typeface.createFromAsset ne marche pas dans l'éditeur de layout.
        if (isInEditMode()) {
            return;
        }

        //accède aux attributs ajoutés à cette CustomTextView
        TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);

        //récupère l'attribut "font"
        String fontName = styledAttrs.getString(R.styleable.CustomTextView_font);

        //permet au garbage collector de récupérer l'espace utilisé par ce TypedArray
        styledAttrs.recycle();

        //puis modifie la font de cet élément
        setTypeFace(fontName);
    }

    /**
     *
     * @param fontName
     */
    public void setTypeFace(String fontName) {
        if (fontName != null) {
            try {
                Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + fontName);
                setTypeface(typeface);
            } catch (Exception e) {
                Log.e("FONT", fontName + " not found", e);
            }
        }
    }

}