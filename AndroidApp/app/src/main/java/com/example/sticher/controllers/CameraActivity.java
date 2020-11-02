package com.example.sticher.controllers;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sticher.R;
import com.example.sticher.models.*;

import java.util.ArrayList;


public class CameraActivity extends AppCompatActivity {
    private int numberofImage = 2;
    private ArrayList<byte[]> arrayOfDataImage = new ArrayList<byte[]>();
    private Camera camera;
    TextView photoTake;
    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            arrayOfDataImage.add(data);
            camera.startPreview();
            numberofImage--;
            photoTake.setText("Take " + numberofImage +" more photo");
            if(numberofImage == 0) {
                Intent stitcherResultActivity = new Intent(CameraActivity.this,SticherResult.class);
                stitcherResultActivity.putExtra("img1", arrayOfDataImage.get(0));
                stitcherResultActivity.putExtra("img2", arrayOfDataImage.get(1));
                startActivity(stitcherResultActivity);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        camera = getCameraInstance();
        final CameraPreview cameraPreview = new CameraPreview(this, camera);
        final FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        photoTake = (TextView) findViewById(R.id.take);
        Button btnCapture = (Button) findViewById(R.id.capture);
        preview.addView(cameraPreview);
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.takePicture(null,null, mPicture);
            }
        });
    }

    public Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(0);
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

}

