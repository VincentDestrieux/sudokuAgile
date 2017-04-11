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
        System.out.println("------START-------");
        int[][] easy = {{5, 6, 0, 8, 4, 7, 0, 0, 0},
                {3, 0, 9, 0, 0, 0, 6, 0, 0},
                {0, 0, 8, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 8, 0, 0, 4, 0},
                {7, 9, 0, 6, 0, 2, 0, 1, 8},
                {0, 5, 0, 0, 3, 0, 0, 9, 0},
                {0, 0, 0, 0, 0, 0, 2, 0, 0},
                {0, 0, 6, 0, 0, 0, 8, 0, 7},
                {0, 0, 0, 3, 1, 6, 0, 5, 9}};
        Grid easyGrid = new Grid(easy);
        System.out.println("------INITIATE-------");
        System.out.println(easyGrid.toString());

        System.out.println("------LOAD-------");
        BackTrackingAlgorithm bta = new BackTrackingAlgorithm(3);
        Grid res = bta.compute(easyGrid);
        System.out.println(easyGrid.toString());
        System.out.println("------FINISH-------");

    }

    public void exitApp(View v) {
        finish();
    }
}
