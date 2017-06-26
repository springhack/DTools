package com.dtools.ReactPackage;

import android.app.admin.*;
import android.content.*;
import android.content.pm.*;
import com.facebook.react.bridge.*;
import java.util.*;
import com.dtools.Utils.*;
import com.dtools.DeviceOwner.*;



// dpm set-device-owner com.springhack.dtools/com.dtools.DeviceOwner.DeviceOwnerReceiver
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
        constants.put("UNHIDE", true);
        constants.put("HIDE", false);
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
        try {
            List<ApplicationInfo> apps = getReactApplicationContext().getPackageManager().getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
            DevicePolicyManager manager = (DevicePolicyManager) getReactApplicationContext().getSystemService(Context.DEVICE_POLICY_SERVICE);
            ComponentName componentName = DeviceOwnerReceiver.getComponentName(getReactApplicationContext());
            WritableArray array = Arguments.createArray();
            for (ApplicationInfo app:apps) {
                WritableMap map = Arguments.createMap();
                map.putString("packageName", app.packageName);
                map.putString("appName", app.loadLabel(getReactApplicationContext().getPackageManager()).toString());
                map.putBoolean("systemApp", 0 < (app.flags & ApplicationInfo.FLAG_SYSTEM));
                map.putBoolean("enabled", !manager.isApplicationHidden(componentName, app.packageName));
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
            ApplicationInfo info = getReactApplicationContext().getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
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
            boolean result = manager.setApplicationHidden(componentName, packageName, !hidden);
            promise.resolve(result);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void getPackageHideState(String packageName, Promise promise) {
        try {
            DevicePolicyManager manager = (DevicePolicyManager) getReactApplicationContext().getSystemService(Context.DEVICE_POLICY_SERVICE);
            ComponentName componentName = DeviceOwnerReceiver.getComponentName(getReactApplicationContext());
            boolean result = manager.isApplicationHidden(componentName, packageName);
            promise.resolve(result);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

}
