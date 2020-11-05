package com.example.sticher.models;

import android.graphics.Bitmap;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.MatVector;
import org.bytedeco.javacpp.opencv_stitching.Stitcher;
import org.bytedeco.javacv.AndroidFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;

import static org.bytedeco.javacpp.opencv_imgcodecs.IMREAD_COLOR;
import static org.bytedeco.javacpp.opencv_imgcodecs.imdecode;


public class Stitching {
    AndroidFrameConverter converterToBitmap = new AndroidFrameConverter();
    OpenCVFrameConverter.ToMat converterToMat = new OpenCVFrameConverter.ToMat();
    static MatVector imgs;
    private Mat pano, img1, img2;
    Stitcher stitcher;

    public Stitching(byte[] img1, byte[] img2) {
        stitcher = Stitcher.create(Stitcher.SCANS);
        this.img1 = imdecode(new Mat(new BytePointer(img1), false),IMREAD_COLOR);
        this.img2 = imdecode(new Mat(new BytePointer(img2), false),IMREAD_COLOR);
        pano = new Mat();
        Mat[] arrayOfMatImg = {
                this.img1, this.img2
        };
        imgs = new MatVector(arrayOfMatImg);
    }

    public Bitmap process() {
        try {
            int status = 0;
            status = stitcher.stitch(imgs, pano);
            if (status != Stitcher.OK) {
                return null;
            }
            return converterToBitmap.convert(converterToMat.convert(pano));
            //return converterToBitmap.convert(converterToMat.convert(img1));
        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }
}
