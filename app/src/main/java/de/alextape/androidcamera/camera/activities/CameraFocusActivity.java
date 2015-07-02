package de.alextape.androidcamera.camera.activities;

import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import de.alextape.androidcamera.R;
import de.alextape.androidcamera.camera.CameraController;
import de.alextape.androidcamera.camera.callbacks.AutoFocusCallback;

/**
 * Created by thinker on 02.07.15.
 */
public class CameraFocusActivity extends CameraOrientationActivity implements View.OnTouchListener {

    private static final String TAG = CameraFocusActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The activity is being created.
        Log.d(TAG, "onCreate");

        // get view for onTouch()
        View layoutView = this.findViewById(R.id.layoutContainer);
        layoutView.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d(TAG, "MainActivity onTouch");
        //cameraFeatures.focusOnTouch(event);
        return true;
    }

    public String[] getFocusOptions() {
        String[] options = new String[7];
        options[0] = "Auto";
        options[1] = "Continuous Video";
        options[2] = "EDOF";
        options[3] = "Fixed";
        options[4] = "Infinity";
        options[5] = "Makro";
        options[6] = "Continuous Picture";
        return options;
    }

    public String[] getFlashOptions() {

        String[] options = new String[5];
        options[0] = "Auto";
        options[0] = "Off";
        options[2] = "On";
        options[3] = "Red-Eye";
        options[4] = "Torch";
        return options;
    }

    public void focusOnTouch(MotionEvent event) {
        Rect focusRect = calculateTapArea(event.getRawX(), event.getRawY(), 1f);
        Rect meteringRect = calculateTapArea(event.getRawX(), event.getRawY(), 1.5f);

        Log.d(TAG, focusRect.toString());

        Camera.Parameters parameters = CameraController.getInstance().getCamera().getParameters();
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);

        if (parameters.getMaxNumFocusAreas() > 0) {
            List<Camera.Area> focusAreas = new ArrayList<Camera.Area>();
            focusAreas.add(new Camera.Area(focusRect, 1000));

            parameters.setFocusAreas(focusAreas);
        }

        if (parameters.getMaxNumMeteringAreas() > 0) {
            List<Camera.Area> meteringAreas = new ArrayList<Camera.Area>();
            meteringAreas.add(new Camera.Area(meteringRect, 1000));

            parameters.setMeteringAreas(meteringAreas);
        }

        CameraController.getInstance().getCamera().setParameters(parameters);
        CameraController.getInstance().getCamera().autoFocus(new AutoFocusCallback());
    }

    public Camera.Size getResolution() {
        Camera.Parameters params = CameraController.getInstance().getCamera().getParameters();
        Camera.Size s = params.getPreviewSize();
        return s;
    }

    private Rect calculateTapArea(float x, float y, float coefficient) {
        float focusAreaSize = 300;
        int areaSize = Float.valueOf(focusAreaSize * coefficient).intValue();

        int centerX = (int) (x / getResolution().width - 1000);
        int centerY = (int) (y / getResolution().height - 1000);

        int left = clamp(centerX - areaSize / 2, -1000, 1000);
        int top = clamp(centerY - areaSize / 2, -1000, 1000);

        RectF rectF = new RectF(left, top, left + areaSize, top + areaSize);

        return new Rect(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
    }

    private int clamp(int x, int min, int max) {
        if (x > max) {
            return max;
        }
        if (x < min) {
            return min;
        }
        return x;
    }

    private String setFocusMode(int type) {

        String returnThis = null;
        Camera.Parameters params = CameraController.getInstance().getCamera().getParameters();
        List<String> FocusModes = params.getSupportedFocusModes();

        switch (type) {
            case 0:
                if (FocusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
                    params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                } else
                    returnThis = "Auto Mode not supported";
                break;
            case 1:
                if (FocusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO)) {
                    params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
                } else
                    returnThis = "Continuous Video Mode not supported";
                break;
            case 2:
                if (FocusModes.contains(Camera.Parameters.FOCUS_MODE_EDOF)) {
                    params.setFocusMode(Camera.Parameters.FOCUS_MODE_EDOF);

                } else
                    returnThis = "EDOF Mode not supported";
                break;
            case 3:
                if (FocusModes.contains(Camera.Parameters.FOCUS_MODE_FIXED)) {
                    params.setFocusMode(Camera.Parameters.FOCUS_MODE_FIXED);

                } else
                    returnThis = "Fixed Mode not supported";
                break;
            case 4:
                if (FocusModes.contains(Camera.Parameters.FOCUS_MODE_INFINITY)) {
                    params.setFocusMode(Camera.Parameters.FOCUS_MODE_INFINITY);

                } else
                    returnThis = "Infinity Mode not supported";
                break;
            case 5:
                if (FocusModes.contains(Camera.Parameters.FOCUS_MODE_MACRO)) {
                    params.setFocusMode(Camera.Parameters.FOCUS_MODE_MACRO);

                } else
                    returnThis = "Macro Mode not supported";
                break;
            case 6:
                if (FocusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
                    params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);

                } else
                    returnThis = "Continuous Picture Mode not supported";
                break;
        }

        CameraController.getInstance().getCamera().setParameters(params);
        return returnThis;
    }

    private String setFlashMode(int type) {

        String returnThis = null;
        Camera.Parameters params = CameraController.getInstance().getCamera().getParameters();
        List<String> FlashModes = params.getSupportedFlashModes();

        switch (type) {
            case 0:
                if (FlashModes.contains(Camera.Parameters.FLASH_MODE_AUTO)) {
                    params.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);

                } else
                    returnThis = "Auto Mode not supported";
                break;
            case 1:
                if (FlashModes.contains(Camera.Parameters.FLASH_MODE_OFF)) {
                    params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);

                } else
                    returnThis = "Off Mode not supported";
                break;
            case 2:
                if (FlashModes.contains(Camera.Parameters.FLASH_MODE_ON)) {
                    params.setFlashMode(Camera.Parameters.FLASH_MODE_ON);

                } else
                    returnThis = "On Mode not supported";
                break;
            case 3:
                if (FlashModes.contains(Camera.Parameters.FLASH_MODE_RED_EYE)) {
                    params.setFlashMode(Camera.Parameters.FLASH_MODE_RED_EYE);
                } else
                    returnThis = "Red Eye Mode not supported";
                break;
            case 4:
                if (FlashModes.contains(Camera.Parameters.FLASH_MODE_TORCH)) {
                    params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                } else {
                    returnThis = "Torch Mode not supported";
                }
                break;
        }

        CameraController.getInstance().getCamera().setParameters(params);
        return returnThis;
    }

}
