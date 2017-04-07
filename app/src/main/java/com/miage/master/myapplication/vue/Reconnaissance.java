package com.miage.master.myapplication.vue;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.miage.master.myapplication.R;
import com.miage.master.myapplication.model.ZoneDeDessin;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class Reconnaissance extends Activity {

    private RelativeLayout rl;
    private ZoneDeDessin dessin;
    private Button push;
    private ImageView imageView;
    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_reconnaissance);

        push = (Button) findViewById(R.id.button);
        addZoneDeDessin();
        imageView = (ImageView) findViewById(R.id.imageView);

        push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View zoneDeDessin = findViewById(R.id.zoneDeDessin);
                bmp = Capture(zoneDeDessin);
                imageView.setImageBitmap(bmp);
                creerImage(bmp);
            }
        });
    }

    //Methode permettant de mettre l'image dans un objet de type Bitmap
    public Bitmap Capture(View v) {
        View rootview = v.getRootView();
        rootview.setDrawingCacheEnabled(true);
        Bitmap bitmap1 = Bitmap.createBitmap(rootview.getDrawingCache(),0,0,getWindowManager().getDefaultDisplay().getWidth(),getWindowManager().getDefaultDisplay().getHeight()/2);
        rootview.setDrawingCacheEnabled(false);
        return bitmap1;
    }

    //Methode permettant de retransmettre l'image sur l'imageView
    public void creerImage(Bitmap bmp) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 40, bytes);
        File file = new File(Environment.getExternalStorageDirectory() +
                "/capturedscreenandroid.png");
        try {
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(bytes.toByteArray());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Cette méthode permet de creer la Zone de dessin et de l'inclure dans la Vue.
    private void addZoneDeDessin() {
        rl = (RelativeLayout) findViewById(R.id.relativeL);

        dessin = new ZoneDeDessin(this);
        dessin.setBackgroundColor(Color.WHITE);
        dessin.setLayoutParams(new RelativeLayout.LayoutParams(getWindowManager().getDefaultDisplay().getWidth(), getWindowManager().getDefaultDisplay().getHeight()/2));
        dessin.setId(R.id.zoneDeDessin);

        rl.addView(dessin);
    }
}
