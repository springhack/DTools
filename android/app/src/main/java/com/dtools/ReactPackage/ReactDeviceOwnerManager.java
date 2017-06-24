package com.dtools.ReactPackage;

import com.facebook.react.bridge.*;
import java.util.*;

public class ReactDeviceOwnerManager extends ReactContextBaseJavaModule {

    public ReactDeviceOwnerManager(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "DeviceOwnerManager";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("HIDE", true);
        constants.put("UNHIDE", false);
        return constants;
    }

    @ReactMethod
    public void getPackageList(Promise promise) {
        // getReactApplicationContext()
        try {
            WritableArray array = Arguments.createArray();
            array.pushString("con.springhack.dtools.debug");
            promise.resolve(array);
            // TODO
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void setPackageHideState(Promise promise) {
        try {
            promise.resolve(true);
            // TODO
        } catch (Exception e) {
            promise.reject(e);
        }
    }

}
