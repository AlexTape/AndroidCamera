package de.alextape.androidcamera;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import de.alextape.androidcamera.camera.CameraActivity;
import de.alextape.androidcamera.camera.CameraFeatures;
import de.alextape.androidcamera.camera.CameraOrientationActivity;

/**
 * Created by thinker on 30.06.15.
 */
public class MainActivity extends CameraOrientationActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private CameraFeatures cameraFeatures;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The activity is being created.
        //Log.d(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
        //Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
        //Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
        //Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
        //Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
        //Log.d(TAG, "onDestroy");
    }


}
