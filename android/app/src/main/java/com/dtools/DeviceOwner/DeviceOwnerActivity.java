package com.dtools.DeviceOwner;

import android.app.admin.*;
import android.content.*;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.util.*;
import com.dtools.R;


public class DeviceOwnerActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank_activity);
        if (savedInstanceState == null) {
            DevicePolicyManager manager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
            if (manager.isDeviceOwnerApp(getApplicationContext().getPackageName())) {
                // This app is set up as the device owner. Show the main features.
                Log.d(TAG, "The app is the device owner.");
            } else {
                // This app is not set up as the device owner. Show instructions.
                Log.d(TAG, "The app is not the device owner.");
            }
        }
    }

}