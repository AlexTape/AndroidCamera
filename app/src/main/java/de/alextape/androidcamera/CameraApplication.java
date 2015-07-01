package de.alextape.androidcamera;

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
        AbstractCameraActivity.Orientation initialOrientation = null;
        final Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        switch (display.getRotation()) {
            case Surface.ROTATION_0:
                initialOrientation = AbstractCameraActivity.Orientation.PORTRAIT;
                break;
            case Surface.ROTATION_90:
                initialOrientation = AbstractCameraActivity.Orientation.LANDSCAPE;
                break;
            case Surface.ROTATION_180:
                initialOrientation = AbstractCameraActivity.Orientation.REVERSE_PORTRAIT;
                break;
            case Surface.ROTATION_270:
                initialOrientation = AbstractCameraActivity.Orientation.REVERSE_LANDSCAPE;
                break;
        }
        Log.d(TAG, "Initial orientation: " + initialOrientation);
        CameraController.setInitialOrientation(initialOrientation);


    }


}