package de.alextape.androidcamera.camera;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import de.alextape.androidcamera.R;

/**
 * Created by thinker on 30.06.15.
 */
public abstract class CameraActivity extends CameraRootActivity {

    private static final String TAG = CameraActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The activity is being created.
        Log.d(TAG, "onCreate");

        // init camera
        View layoutView = this.findViewById(R.id.layoutContainer);

        // init CameraController

        if (CameraConfig.ASYNC_CAMERA) {
            CameraController.create(this, layoutView, new AsyncCameraCallback(), CameraController.CameraType.BACK_CAMERA);
        } else {
            CameraController.create(this, layoutView, new CameraCallback(), CameraController.CameraType.BACK_CAMERA);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
        Log.d(TAG, "onStart");
        CameraController.getInstance().startCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
        Log.d(TAG, "onResume");
        //cameraController.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
        Log.d(TAG, "onPause");
        //cameraController.stopCamera();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
        Log.d(TAG, "onStop");
        //cameraController.stopCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
        Log.d(TAG, "onDestroy");
        CameraController cameraController = CameraController.getInstance();
        cameraController.stopAndReleaseCamera();
        cameraController.releaseView();
    }


}
