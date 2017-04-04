package com.miage.master.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class Grille extends Activity {

    List<EditText> textList = new ArrayList<EditText>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grille);
        for(int i=0;i<81;i++){
            textList.add((EditText)findViewById((R.id.Case1_1)+i));
            textList.get(i).setText(toString().valueOf(i));

        }
    }

    public void EditContenu(View v){
        int t = v.getId();
        EditText test= (EditText) findViewById(t);
        //test.setText("GO");


    }

}