package com.miage.master.myapplication.vue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.RenderScript;
import android.view.View;

import com.miage.master.myapplication.R;

import network.CNNdroid;

public class MainActivity extends Activity {

    //private RenderScript myRenderScript;
    //private CNNdroid myConv = null;
    private boolean condition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //myRenderScript = RenderScript.create(this);
        //new prepareModel().execute(myRenderScript);
        setContentView(R.layout.activity_main);
    }

    public void difficulty(View v) {
        Intent intent = new Intent(MainActivity.this, Mode.class);
        startActivity(intent);
    }

    public void exitApp(View v) {
        finish();
    }

    //Async task
    /*
    private class prepareModel extends AsyncTask<RenderScript, Void, CNNdroid> {

        ProgressDialog progDailog;

        protected void onPreExecute() {
            progDailog = new ProgressDialog(MainActivity.this);
            progDailog.setMessage("Starting...");
            progDailog.setIndeterminate(false);
            progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDailog.setCancelable(true);
            progDailog.show();
        }

        @Override
        protected CNNdroid doInBackground(RenderScript... params) {
            try {
                myConv = new CNNdroid(myRenderScript, "/sdcard/cnndroid/LeNet_def.txt");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return myConv;
        }

        protected void onPostExecute(CNNdroid result) {
            condition = true;
            progDailog.dismiss();
        }
    }*/
}
