package com.example.sticher.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sticher.R;

public class MainActivity extends AppCompatActivity {

    private boolean allowSitching = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnStartCam = (Button) findViewById(R.id.start_cam);
        allowSitching = cameraPermissionRequest();
        btnStartCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(allowSitching) {
                    Intent cameraActivity = new Intent(MainActivity.this,CameraActivity.class);
                    startActivity(cameraActivity);
                } else {
                    Toast.makeText(getApplicationContext(),"Camera Permission required\nCheck App Permission Setting", Toast.LENGTH_LONG).show();
                    allowSitching = cameraPermissionRequest();
                }
            }
        });
    }

    public boolean cameraPermissionRequest(){

        int cameraPermission = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);

        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CAMERA,
                            Manifest.permission.CAMERA},
                    100
            );
            return false;
        } else {
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 100: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    allowSitching = true;
                } else {
                    allowSitching = false;
                }
                return;
            }
        }
    }

}