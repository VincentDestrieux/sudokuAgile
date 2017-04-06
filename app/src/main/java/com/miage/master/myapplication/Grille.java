package com.miage.master.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.miage.master.myapplication.service.generator;

import java.util.ArrayList;
import java.util.List;

public class Grille extends AppCompatActivity {

    List<EditText> textList = new ArrayList<EditText>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grille);
        int [][] sudoku = generator.getInstance().generateGrid();

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                textList.add((EditText)findViewById((R.id.Case1_1)+x+y));
                textList.get(x+y).setText(toString().valueOf(sudoku[x][y]));

                System.out.print(sudoku[x][y] + "|");
            }
            System.out.println();
        }
        for(int i=0;i<81;i++){


        }
    }

    public void EditContenu(View v){
        int t = v.getId();
        EditText test= (EditText) findViewById(t);
        test.setText("coucou");

}
}
