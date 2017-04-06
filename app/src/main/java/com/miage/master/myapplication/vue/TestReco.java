package com.miage.master.myapplication.vue;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.miage.master.myapplication.R;
import com.miage.master.myapplication.model.ZoneDeDessin;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class TestReco extends Activity {
    private static final int ECRAN = 900;
    private static final String nameFile = "screenSudoku";

    private RelativeLayout rl;
    private ZoneDeDessin z;
    private Button b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ancienne version mets en plein écran
        //setContentView(new ZoneDeDessin(this));
        setContentView(R.layout.activity_testreco);
        b = (Button) findViewById(R.id.button);
        addZoneDeDessin();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //View rootView = getWindow().getDecorView().findViewById(R.id.zoneDeDessin);
                //Bitmap dessin = z.makeScreenShot(rootView);
                // z.saveImage(rootView);
                // File f = z.storeInSDCard(dessin, nameFile);
                //shareImage(f);
            }
        });
    }

    private void shareImage(File file) {
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");

        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        try {
            startActivity(Intent.createChooser(intent, "Share Screenshot"));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No App Available", Toast.LENGTH_SHORT).show();
        }
    }

    //Cette méthode permet de creer la Zone de dessin et de l'inclure dans la Vue.
    private void addZoneDeDessin() {
        rl = (RelativeLayout) findViewById(R.id.relativeL);

        z = new ZoneDeDessin(this);
        z.setBackgroundColor(Color.WHITE);
        z.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ECRAN));
        z.setId(R.id.zoneDeDessin);

        rl.addView(z);
    }

    public void saveImage(View view) {

        // Bitmap finalBitmap = makeScreenShot(view);
        // File directory = new File(Environment.getExternalStorageDirectory()+File.separator+"images");
        //directory.mkdirs();
        View v1 = view.getRootView();
        v1.setDrawingCacheEnabled(true);
        Bitmap bm = Bitmap.createBitmap(v1.getDrawingCache());
        v1.setDrawingCacheEnabled(false);

        File myDir=new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/sdcard/Download/myFile");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Images"+ n +".jpg";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
