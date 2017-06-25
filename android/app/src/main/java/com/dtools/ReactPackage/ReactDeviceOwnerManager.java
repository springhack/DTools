package com.dtools.ReactPackage;

import android.content.pm.*;
import com.facebook.react.bridge.*;
import java.util.*;
import com.dtools.Utils.*;

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
            List<PackageInfo> packs = getReactApplicationContext().getPackageManager().getInstalledPackages(0);
            WritableArray array = Arguments.createArray();
            for (PackageInfo p:packs) {
                WritableMap map = Arguments.createMap();
                map.putString("packageName", p.packageName);
                map.putString("appName", p.applicationInfo.loadLabel(getReactApplicationContext().getPackageManager()).toString());
                map.putString("appIcon", ReactDrawable.drawableToBase64(p.applicationInfo.loadIcon(getReactApplicationContext().getPackageManager())));
                map.putBoolean("systemApp", 0 < (p.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM));
                array.pushMap(map);
            }
            promise.resolve(array);
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

    @ReactMethod
    public void getPackageHideState(Promise promise) {
        try {
            promise.resolve(true);
            // TODO
        } catch (Exception e) {
            promise.reject(e);
        }
    }

}
