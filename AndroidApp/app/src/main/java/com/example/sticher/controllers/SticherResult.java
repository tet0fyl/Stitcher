package com.example.sticher.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.sticher.R;

import java.util.ArrayList;

public class SticherResult extends AppCompatActivity {
    byte[] img1, img2;
    Bitmap bmpResult;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticher_result);
        Intent intent = getIntent();
        img1 = intent.getByteArrayExtra("img1");
        img2 = intent.getByteArrayExtra("img2");
        image = (ImageView) findViewById(R.id.imageView);
        image.post(new Runnable() {
            @Override
            public void run() {
                //image.setImageBitmap(Bitmap.createScaledBitmap(bmp, image.getWidth(), image.getHeight(), false));

            }
        });


    }

}