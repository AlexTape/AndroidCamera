package de.alextape.androidcamera;

import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;

import java.io.IOException;

/**
 * Created by thinker on 30.06.15.
 */
public class CameraCallback implements SurfaceHolder.Callback,
        Camera.PreviewCallback {

    private static final String TAG = CameraCallback.class.getSimpleName() + "ARGH";

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "surfaceCreated");
        try {
            CameraController.getInstance().getCamera().setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d(TAG,"surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG,"surfaceDestroyed");
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {


    }
}
