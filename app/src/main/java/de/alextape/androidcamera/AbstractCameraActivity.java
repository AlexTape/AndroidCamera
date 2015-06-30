package de.alextape.androidcamera;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

/**
 * Created by thinker on 30.06.15.
 */
public abstract class AbstractCameraActivity extends Activity {

    private static final String TAG = AbstractCameraActivity.class.getSimpleName() + "ARGH";

    private CameraController cameraController;

    public enum Orientation {
        PORTRAIT, LANDSCAPE, REVERSE_PORTRAIT, REVERSE_LANDSCAPE
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The activity is being created.
        Log.d(TAG, "onCreate");

        // TODO remove this later
        setContentView(R.layout.camera_layout);

        // set default/initial orientation
        //this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // init camera
        View layoutView = this.findViewById(R.id.layoutContainer);
        cameraController = CameraController.create(this, layoutView, new CameraCallback(), CameraController.CameraType.BACK_CAMERA);
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
        cameraController.stopAndReleaseCamera();
        cameraController.releaseView();
    }

    public void onConfigurationChanged(Configuration newConfiguration) {

        super.onConfigurationChanged(newConfiguration);
        final View view = findViewById(R.id.layoutContainer);

        ViewTreeObserver observer = view.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {

                Orientation orientation = getScreenOrientation();
                Log.d(TAG,
                        String.format("new width=%d; new height=%d; new orientation=" + orientation, view.getWidth(),
                                view.getHeight()));

                //cameraController.configureCamera(orientation, view.getWidth(), view.getHeight());

                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private Orientation getScreenOrientation() {
        Orientation returnThis = null;
        final Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        switch (display.getRotation()) {
            case Surface.ROTATION_0:
                returnThis = Orientation.PORTRAIT;
                break;

            case Surface.ROTATION_90:
                returnThis = Orientation.LANDSCAPE;
                break;

            case Surface.ROTATION_180:
                returnThis = Orientation.REVERSE_PORTRAIT;
                break;

            case Surface.ROTATION_270:
                returnThis = Orientation.REVERSE_LANDSCAPE;
                break;
        }
        return returnThis;
    }


}
