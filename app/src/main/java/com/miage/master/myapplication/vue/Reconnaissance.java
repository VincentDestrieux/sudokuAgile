package com.miage.master.myapplication.vue;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.renderscript.RenderScript;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.miage.master.myapplication.R;
import com.miage.master.myapplication.model.ZoneDeDessin;
import com.miage.master.myapplication.model.bmpCoord;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import network.CNNdroid;

import static android.graphics.Bitmap.createScaledBitmap;
import static android.graphics.Color.red;

public class Reconnaissance extends Activity {

    private RelativeLayout rl;
    private ZoneDeDessin dessin;
    private Button push;
    private ImageView imageView;
    private Bitmap bmp;

    //CNNdroid part
    private RenderScript myRenderScript;
    private CNNdroid myCnn = null;
    private boolean condition;

    //permission
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;

    //Async tast to lauch CNNdroid
    private class prepareModel extends AsyncTask<RenderScript, Void, CNNdroid> {

        ProgressDialog progDailog;

        protected void onPreExecute() {
            progDailog = new ProgressDialog(Reconnaissance.this);
            progDailog.setMessage("Starting CNNdroid RenderScript Module - Sudoku - RD");
            progDailog.setIndeterminate(false);
            progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDailog.setCancelable(true);
            progDailog.show();
        }

        @Override
        protected CNNdroid doInBackground(RenderScript... params) {
            try {
                myCnn = new CNNdroid(myRenderScript, "/sdcard/cnndroid/LeNet_def.txt");
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("Fail setup-ask remi",e.getMessage());
            }
            return myCnn;
        }

        protected void onPostExecute(CNNdroid result) {
            Log.d("End post","CNNdroid setup");
            condition = true;
            progDailog.dismiss();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_reconnaissance);

        push = (Button) findViewById(R.id.button);
        addZoneDeDessin();
        imageView = (ImageView) findViewById(R.id.imageView);

        //Permissions ?
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Log.d("NO","We need this permission dude");
            }
            else
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            }
        }

        myRenderScript = RenderScript.create(this);
        new prepareModel().execute(myRenderScript);

        push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View zoneDeDessin = findViewById(R.id.zoneDeDessin);
                bmp = Capture(zoneDeDessin);
                //bmp = cropNumber(bmp);
                bmp = createScaledBitmap(bmp,28,28,true);
                imageView.setImageBitmap(bmp);
                //creerImage(bmp);
                try {
                    analysis(bmp);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    //Result of permissionRequest from onCreate method
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // La permission est garantie
                } else {
                    // La permission est refusée
                }
                return;
            }
        }
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
                "/image.png");
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

    //the bmp given is an 28*28 bitmap who's containing the draw number
    private int analysis(Bitmap drawNumber) throws Exception
    {
        //Bitmap resizedBitmap = createScaledBitmap(drawNumber,28,28,false);
        float[][][]  inputSingle = new float[3][28][28];
        //System.out.println("Height : " + drawNumber.getHeight());
        //System.out.println("Width : " + drawNumber.getWidth());
        //*******************************************************
        //load imput from a bitmap file to float array for CNNDroid
        for (int i = 0; i < 28; i++)
        {
            for (int j = 0; j < 28; j++)
            {
                int color = drawNumber.getPixel(i, j);
                //System.out.println(red(color));
                if (((red(color)) == 0))
                {
                    inputSingle[0][j][i] = 1;
                    inputSingle[1][j][i] = 1;
                    inputSingle[2][j][i] = 1;
                }
                else
                {
                    inputSingle[0][j][i] = 0;
                    inputSingle[1][j][i] = 0;
                    inputSingle[2][j][i] = 0;
                }
            }
        }

        //Affichage de la matrice 28*28
        /*for (int i = 0; i < 28; i++)
        {
            for (int j = 0; j < 28; j++) {
                System.out.println(inputSingle[0][i][j]+"|");
            }
            System.out.println("|\n");
        }*/

        float[][] result = (float[][]) myCnn.compute(inputSingle);

        //Log.d("mat", Arrays.toString(inputSingle[0]));

        float answer=-1;
        int answer_val = -1;
        for (int i = 0; i < result[0].length; i++) {
            System.out.println(i+" - " + result[0][i] + "\n");
            if(result[0][i]>answer)
            {
                answer_val = i;
                answer = result[0][i];
            }
        }
        System.out.println("CLASS : " + answer_val);
        //Log.d("MYCLASS", "analysis: get class " + result.getClass());
        return answer_val;
    }

    //return crooped bitmp whose containing the number written
    //Care about nothing in the draw area
    private Bitmap cropNumber(Bitmap bmp)
    {
        bmpCoord topLeft = getTopLeftCoord(bmp);
        bmpCoord bottomRight = getBottomRightCoord(bmp);
        Rect rect = new Rect(topLeft.getX(),topLeft.getY(),bottomRight.getX(),bottomRight.getY());
        System.out.println(rect.toString());
        assert(rect.left < rect.right && rect.top < rect.bottom);
        //Bitmap resultBmp = Bitmap.createBitmap(rect.right-rect.left, rect.bottom-rect.top, Bitmap.Config.ARGB_8888);
        return Bitmap.createBitmap(bmp,topLeft.getX(),topLeft.getY(),bottomRight.getX()-topLeft.getX(),bottomRight.getY()-topLeft.getY());
    }

    private bmpCoord getTopLeftCoord(Bitmap bmp) throws IllegalArgumentException
    {
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        //left -> leftest pos - top -> highest pos
        int left,top;
        left=top=-1;
        //Optimization for break outerloop
        outerloop1:
        //Step 1 -> Looking top 2 bottom & left to right to find the "leftest" point
        for(int i =0;i<width;i++)
        {
            for (int j = 0; j < height; j++)
            {
                if ((red(bmp.getPixel(i, j))) < 128)
                {
                    System.out.println(i +" - "+j +"-> " + red(bmp.getPixel(i, j)));
                    left = i;
                    top = j;
                    break outerloop1;
                }
            }
        }
        if(left==-1)
            throw new IllegalArgumentException("Empty entry");
        //step 2 - do the same for the "highest" point
        outerloop2:
        for(int i =0;i<height;i++)
        {
            for (int j = 0; j < width; j++)
            {
                if ((red(bmp.getPixel(j, i))) < 128)
                {
                    top=i;
                    break outerloop2;
                }
            }
        }
        return new bmpCoord(left,top);
    }

    //see getTopLeftCoord for more information -> same here with bottom right point
    private bmpCoord getBottomRightCoord(Bitmap bmp)
    {
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        int bottom,right;
        bottom=right=-1;
        outerloop1:
        for(int i= width-1;i>0;i--)
        {
            for(int j = height-1;j>0;j--)
            {
                if ((red(bmp.getPixel(i, j))) < 128)
                {
                    right=i;
                    bottom=j;
                    break outerloop1;
                }
            }
        }
        if(right==-1)
            throw new IllegalArgumentException("Empty entry");
        outerloop2:
        for(int i= height-1;i>0;i--)
        {
            for (int j = width - 1; j > 0; j--)
            {
                if ((red(bmp.getPixel(j, i))) < 128)
                {
                    bottom = i;
                    break outerloop2;
                }
            }
        }
        return new bmpCoord(right,bottom);
    }
}
