package de.alextape.androidcamera.camera.interfaces;

import android.hardware.Camera;
import android.view.SurfaceHolder;

/**
 * Created by thinker on 01.07.15.
 */
public interface CameraCallbackInterface {

    public void onPreviewFrame(byte[] data, Camera camera);

    public void surfaceCreated(SurfaceHolder holder);

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height);

    public void surfaceDestroyed(SurfaceHolder holder);

}
