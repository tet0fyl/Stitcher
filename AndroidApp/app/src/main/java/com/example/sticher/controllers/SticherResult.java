package com.example.sticher.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.sticher.R;
import com.example.sticher.globals.Globals;
import com.example.sticher.models.Stitching;


public class SticherResult extends AppCompatActivity {
    byte[] result;
    Bitmap bmpResult;
    ImageView imageView;
    Stitching stitching;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticher_result);
        Intent intent = getIntent();
        imageView = findViewById(R.id.imageView);
        stitching = new Stitching(Globals.arrayOfDataImage.get(0),Globals.arrayOfDataImage.get(1));
        imageView.post(new Runnable() {
            @Override
            public void run() {
                bmpResult = stitching.process();
                //result= Globals.arrayOfDataImage.get(1);
                //bmpResult = BitmapFactory.decodeByteArray(result, 0, result.length);
                if(bmpResult != null){
                    imageView.setImageBitmap(Bitmap.createScaledBitmap(bmpResult, bmpResult.getWidth(), bmpResult.getHeight(), false));
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