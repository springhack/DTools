package com.dtools.ReactPackage;

import android.app.admin.*;
import android.content.*;
import android.content.pm.*;
import com.facebook.react.bridge.*;
import java.util.*;
import com.dtools.Utils.*;
import com.dtools.DeviceOwner.*;

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
    public void isDeviceOwner(Promise promise) {
        try {
            DevicePolicyManager manager = (DevicePolicyManager) getReactApplicationContext().getSystemService(Context.DEVICE_POLICY_SERVICE);
            promise.resolve(manager.isDeviceOwnerApp(getReactApplicationContext().getPackageName()));
        } catch (Exception e) {
            promise.reject(e);
        }
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
                map.putBoolean("systemApp", 0 < (p.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM));
                map.putBoolean("enables", p.applicationInfo.enabled);
                array.pushMap(map);
            }
            promise.resolve(array);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void getAppIcon(String packageName, Promise promise) {
        try {
            ApplicationInfo info = getReactApplicationContext().getPackageManager().getApplicationInfo(packageName, 0);
            promise.resolve(ReactDrawable.drawableToBase64(info.loadIcon(getReactApplicationContext().getPackageManager())));
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void setPackageHideState(String packageName, boolean hidden, Promise promise) {
        try {
            DevicePolicyManager manager = (DevicePolicyManager) getReactApplicationContext().getSystemService(Context.DEVICE_POLICY_SERVICE);
            ComponentName componentName = DeviceOwnerReceiver.getComponentName(getReactApplicationContext());
            boolean result = manager.setApplicationHidden(componentName, packageName, hidden);
            promise.resolve(result);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void getPackageHideState(String packageName, Promise promise) {
        try {
            ApplicationInfo info = getReactApplicationContext().getPackageManager().getApplicationInfo(packageName, 0);
            promise.resolve(info.enabled);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

}
