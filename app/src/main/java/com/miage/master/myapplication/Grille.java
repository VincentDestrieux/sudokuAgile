package com.miage.master.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Grille extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grille);
        int[][] Sudoku = new int[9][9];
        //EditText text = findViewById(R.id)

    }

    public void EditContenu(View v){
      //  String x =v.getTag().toString();
        int t = v.getId();
        EditText test= (EditText) findViewById(t);
        test.setText("coucou");

}
}
