package de.alextape.androidcamera.camera;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

/**
 * Created by thinker on 01.07.15.
 */
public class CameraApplication extends Application {

    private static final String TAG = CameraApplication.class.getSimpleName() + "ARGH";

    @Override
    public void onCreate() {
        super.onCreate();

        // promote initial orientation immediately
        CameraActivity.Orientation initialOrientation = null;
        final Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        switch (display.getRotation()) {
            case Surface.ROTATION_0:
                initialOrientation = CameraActivity.Orientation.PORTRAIT;
                break;
            case Surface.ROTATION_90:
                initialOrientation = CameraActivity.Orientation.LANDSCAPE;
                break;
            case Surface.ROTATION_180:
                initialOrientation = CameraActivity.Orientation.REVERSE_PORTRAIT;
                break;
            case Surface.ROTATION_270:
                initialOrientation = CameraActivity.Orientation.REVERSE_LANDSCAPE;
                break;
        }
        Log.d(TAG, "Initial orientation: " + initialOrientation);
        CameraController.setInitialOrientation(initialOrientation);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        throw new OutOfMemoryError("Low Memory");
    }
}
