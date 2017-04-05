package com.miage.master.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class Grille extends Activity {

    private List<EditText> textList = new ArrayList<EditText>();
    private EditText textToEdit;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;
    private Button b7;
    private Button b8;
    private Button b9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grille);
    /*    for(int i=0;i<81;i++){
            textList.add((EditText)findViewById((R.id.Case1_1)+i));
            textList.get(i).setText(toString().valueOf(i));

        }*/
        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        b5 = (Button) findViewById(R.id.button5);
        b6 = (Button) findViewById(R.id.button6);
        b7 = (Button) findViewById(R.id.button7);
        b8 = (Button) findViewById(R.id.button8);
        b9 = (Button) findViewById(R.id.button9);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textToEdit != null)
                    textToEdit.setText(textToEdit.getText().insert(textToEdit.getText().length(), "1"));
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (textToEdit != null)
                    textToEdit.setText(textToEdit.getText().insert(textToEdit.getText().length(), "2"));
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (textToEdit != null)
                    textToEdit.setText(textToEdit.getText().insert(textToEdit.getText().length(), "3"));
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (textToEdit != null)
                    textToEdit.setText(textToEdit.getText().insert(textToEdit.getText().length(), "4"));
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (textToEdit != null)
                    textToEdit.setText(textToEdit.getText().insert(textToEdit.getText().length(), "5"));
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (textToEdit != null)
                    textToEdit.setText(textToEdit.getText().insert(textToEdit.getText().length(), "6"));
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (textToEdit != null)
                    textToEdit.setText(textToEdit.getText().insert(textToEdit.getText().length(), "7"));
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (textToEdit != null)
                    textToEdit.setText(textToEdit.getText().insert(textToEdit.getText().length(), "8"));
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (textToEdit != null)
                    textToEdit.setText(textToEdit.getText().insert(textToEdit.getText().length(), "9"));
            }
        });


    }

    //Fonction permettant de remplir la grille
    public void EditContenu(View v) {
        int t = v.getId();
        EditText test = (EditText) findViewById(t);
        textToEdit = test;
        //Permet de clear la zone de texte pour réécrire dedans.
        textToEdit.setText("");
    }

}

