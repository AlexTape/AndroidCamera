package de.alextape.androidcamera.camera.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import de.alextape.androidcamera.R;

/**
 * This class is the root activity object to define the environment of all subclasses.
 */
public class CameraLayoutActivity extends Activity {

    protected View layoutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO remove this later
        setContentView(R.layout.camera_layout);
        this.layoutView = this.findViewById(R.id.layoutContainer);
    }

}
