package de.alextape.androidcamera.camera.callbacks;

import android.hardware.Camera;
import android.util.Log;

/**
 * Created by thinker on 02.07.15.
 */
public class AutoFocusCallback implements Camera.AutoFocusCallback {

    private static final String TAG = AutoFocusCallback.class.getSimpleName();

    @Override
    public void onAutoFocus(boolean success, Camera camera) {
        Log.d(TAG, "onAutoFocus state:" + success);
    }

}
