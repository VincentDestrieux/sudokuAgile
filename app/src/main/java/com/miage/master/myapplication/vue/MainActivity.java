package com.miage.master.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.miage.master.myapplication.R;

public class MainActivity extends Activity {

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
