package de.alextape.androidcamera;

import android.content.Context;
import android.hardware.SensorManager;
import android.view.OrientationEventListener;

/**
 * Created by thinker on 01.07.15.
 */
public class OrientationHelper extends OrientationEventListener {

    public OrientationHelper(Context context) {
        super(context, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onOrientationChanged(int degree) {
        // Fix undetected REVERSE_LANDSCAPE orientation when switching from LANDSCAPE to REVERSE_LANDSCAPE.
        AbstractCameraActivity.Orientation orientation = CameraController.getInstance().getOrientation();
        if (orientation == AbstractCameraActivity.Orientation.LANDSCAPE) {
            if (degree < 180) {
                orientation = AbstractCameraActivity.Orientation.REVERSE_LANDSCAPE;
                CameraController.getInstance().rotateOrientation(orientation);
            }
        }
        if (orientation == AbstractCameraActivity.Orientation.REVERSE_LANDSCAPE) {
            if (degree > 180) {
                orientation = AbstractCameraActivity.Orientation.LANDSCAPE;
                CameraController.getInstance().rotateOrientation(orientation);
            }
        }
    }

}
