package de.alextape.androidcamera.camera;

import android.app.Activity;
import android.os.Bundle;

import de.alextape.androidcamera.R;

/**
 * Created by thinker on 02.07.15.
 */
public class CameraRootActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO remove this later
        setContentView(R.layout.camera_layout);
    }
    
}
