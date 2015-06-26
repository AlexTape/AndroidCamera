package de.alextape.androidcamera;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

public class CameraActivity extends Activity {

    private static final String TAG = "AndroidCamera";

    private Camera mCamera;
    private CameraPreview mPreview;
    private View mCameraView;

    public CameraActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.camera_layout);
        boolean runningCamera = safeCameraOpenInView(findViewById(R.id.camera_frame));

        if (runningCamera == false) {
            Log.d(TAG, "Failed to open cam.");
        }

    }

    private boolean safeCameraOpenInView(View view) {
        boolean isOpen = false;

        releaseCameraAndPreview();
        mCamera = getCameraInstance();
        mCameraView = view;

        if (mCamera != null) {
            isOpen = true;
        }

        if (isOpen == true) {
            mPreview = new CameraPreview(this, mCamera, view);
            FrameLayout preview = (FrameLayout) view.findViewById(R.id.camera_frame);
            preview.addView(mPreview);
            mPreview.startCameraPreview();
        }
        return isOpen;
    }

    public static Camera getCameraInstance() {
        Camera cam = null;
        try {
            cam = Camera.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cam;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseCameraAndPreview();
    }

    private void releaseCameraAndPreview() {

        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
        if (mPreview != null) {
            mPreview.destroyDrawingCache();
            mPreview.resetCamera();
        }
    }



}
