package com.miage.master.myapplication.vue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.miage.master.myapplication.R;
import com.miage.master.myapplication.algo.BackTrackingAlgorithm;
import com.miage.master.myapplication.model.Grid;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void difficulty(View v) {
        Intent intent = new Intent(MainActivity.this, Niveau.class);
        startActivity(intent);
    }

    public void testAlgo(View v) {
        //Permet de tester l'algo BackTracking
        System.out.println("------START-------");
        int[][] easy = {{0, 0, 9, 7, 0, 6, 0, 3, 1},
                {0, 0, 3, 4, 0, 9, 6, 0, 2},
                {0, 0, 0, 0, 0, 0, 0, 0, 9},
                {1, 6, 0, 0, 0, 4, 0, 0, 7},
                {0, 9, 5, 1, 0, 8, 2, 6, 0},
                {3, 0, 0, 9, 0, 0, 0, 4, 5},
                {5, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 0, 6, 2, 0, 7, 5, 0, 0},
                {9, 4, 0, 6, 0, 5, 7, 0, 0}};
        Grid easyGrid = new Grid(easy);
        System.out.println("------INITIATE-------");
        System.out.println(easyGrid.toString());
        System.out.println("------LOAD-------");
        BackTrackingAlgorithm bta = new BackTrackingAlgorithm();
        Grid res = bta.compute(easyGrid);
        System.out.println("------FINISH-------");
        System.out.println(res.toString());
    }

    public void exitApp(View v) {
        finish();
    }
}
