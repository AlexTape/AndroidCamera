package de.alextape.androidcamera;

import android.os.Bundle;
import android.util.Log;

import de.alextape.androidcamera.camera.HardwareCamera;

/**
 * This is a demo how to implement the camera easily.
 */
public class MainActivity extends HardwareCamera {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The activity is being created.
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
        Log.d(TAG, "onDestroy");
    }

    /**
     * This method is used to gather options passed by a user interactions.
     * e.g. Focus mode, Flash mode..
     */
    @Override
    public void onCameraInitialized() {
        Log.d(TAG, "onReleaseCamera");

        Integer mode = null;
        String result = null;
//
//        // Return a list of Available Flash modes
//        String[] flashOptions = getFlashOptions();
//        // pass index of option to setFlashMode(int)
//        result = setFlashMode(1);
//        if (result == null) {
//            Log.d(TAG, "Success");
//        } else {
//            Log.d(TAG, "Fail: " + result);
//        }
//
        // Returns a list of available Focus modes
        String[] focusOptions = getFlashOptions();
        // pass index of option to setFocusMode(int)
        result = setFocusMode(6);
        if (result == null) {
            Log.d(TAG, "Success");
        } else {
            Log.d(TAG, "Fail: " + result);
        }

    }

}
