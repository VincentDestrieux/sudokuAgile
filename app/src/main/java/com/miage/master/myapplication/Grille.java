package com.miage.master.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Grille extends Activity {
    EditText test;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grille);
        for(i=0;i<81;i++) {
            test = (EditText) findViewById((R.id.Case1_1)+i);
            String t = String.valueOf(i);
            test.setText(t);
        }
    }

    public void EditContenu(View v){
        //  String x =v.getTag().toString();
        int t = v.getId();
        EditText test= (EditText) findViewById(t);
        test.setText("coucou");

    }
}