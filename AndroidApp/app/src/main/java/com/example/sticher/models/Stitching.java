package com.example.sticher.models;

import android.util.Log;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.MatVector;
import org.bytedeco.javacpp.opencv_stitching.Stitcher;

import static org.bytedeco.javacpp.opencv_stitching.createStitcher;


public class Stitching {
    static MatVector imgs = new MatVector();
    private Mat pano;
    Stitcher stitcher;

    public Stitching(byte[] img1, byte[] img2) {
        pano = new Mat();
        imgs.push_back(new Mat(img1));
        imgs.push_back(new Mat(img2));
    }

    public byte[] process() {
        stitcher = Stitcher.create(Stitcher.PANORAMA);
        int status = stitcher.stitch(imgs, pano);
        if (status != Stitcher.OK) {
            return null;
        }
        return Mat2Bytes(pano);
    }

    public static byte[] Mat2Bytes(Mat mat){
        byte[] b = new byte[mat.channels() * mat.cols() * mat.rows()];
        mat.data().get(b);
        return b;
    }
}
