package com.example.sticher.controllers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sticher.R;
import com.example.sticher.models.*;

import java.util.ArrayList;


public class CameraActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final String TAG = "CAMERA_ACTI";
    private int numberofImage = 2;
    private ArrayList<byte[]> arrayOfImage = new ArrayList<byte[]>();
    private Camera camera;
    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            arrayOfImage.add(data);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        camera = getCameraInstance();
        CameraPreview cameraPreview = new CameraPreview(this, camera);
        final FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        Button btnCapture = (Button) findViewById(R.id.capture);
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.takePicture(null,null, mPicture);

            }
        });
        preview.addView(cameraPreview);
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

