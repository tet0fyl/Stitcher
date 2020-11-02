package com.example.sticher.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.sticher.R;
import com.example.sticher.models.Stitching;


public class SticherResult extends AppCompatActivity {
    byte[] img1, img2, result;
    Bitmap bmpResult;
    ImageView image;
    Stitching stitching;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticher_result);
        Intent intent = getIntent();
        img1 = intent.getByteArrayExtra("img1");
        img2 = intent.getByteArrayExtra("img2");
        image = (ImageView) findViewById(R.id.imageView);
        stitching = new Stitching(img1,img2);
        image.post(new Runnable() {
            @Override
            public void run() {
                result = stitching.process();
                //result= img2;
                if(result != null){
                    bmpResult = BitmapFactory.decodeByteArray(result, 0, result.length);
                    image.setImageBitmap(Bitmap.createScaledBitmap(bmpResult, image.getWidth(), image.getHeight(), false));
                } else {
                    TextView txtInfo = (TextView) findViewById(R.id.info);
                    txtInfo.setText("Impossible a Sticher, Veuillez recommencer");
                }

            }
        });

        Button btnRetry = (Button) findViewById(R.id.btnretry);
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent mainActivity = new Intent(SticherResult.this,MainActivity.class);
                    startActivity(mainActivity);
                }
            });
    }

}