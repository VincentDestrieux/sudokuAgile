package com.miage.master.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

//Test Branch
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void difficulty(View v){
        Intent intent = new Intent(MainActivity.this, Niveau.class);
        startActivity(intent);
    }

    public void exitApp(View v){
        finish();
    }
}
