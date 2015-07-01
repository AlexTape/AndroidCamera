package de.alextape.androidcamera;

import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.ImageView;

/**
 * Created by thinker on 30.06.15.
 */
public class CameraCallback implements SurfaceHolder.Callback,
        Camera.PreviewCallback {

    private static final String TAG = CameraCallback.class.getSimpleName() + "ARGH";
    private Bitmap mBitmap;

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "surfaceCreated");
//        try {
//            CameraController.getInstance().getCamera().setPreviewDisplay(holder);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d(TAG, "surfaceChanged");
        Log.d(TAG, String.format("Format=%d; width=%d; height=%d", format, width, height));
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "surfaceDestroyed");
        CameraController.getInstance().stopAndReleaseCamera();
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
//        Log.d(TAG, "onPreviewFrame");
        if (CameraController.getInstance().getPreviewFormat() != ImageFormat.NV21) {
            Log.d(TAG, "wrong format");
        } else {
            if (data != null) {
                camera.addCallbackBuffer(data);
                new AsyncCameraTask().execute(data);
            }
        }
    }
}
