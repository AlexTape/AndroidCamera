package de.alextape.androidcamera.camera.callbacks;

import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.util.Log;

import de.alextape.androidcamera.camera.CameraController;
import de.alextape.androidcamera.camera.tasks.AsyncCameraTask;

/**
 * Created by thinker on 01.07.15.
 */
public class AsyncCameraCallback extends CameraCallback implements Camera.PreviewCallback {

    private static final String TAG = AsyncCameraCallback.class.getSimpleName();

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {

        //Log.d(TAG, "onPreviewFrame");
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
