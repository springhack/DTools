package com.dtools.ReactPackage

import android.app.admin.*
import android.content.*
import android.content.pm.*
import com.facebook.react.bridge.*
import java.util.*
import com.dtools.Utils.*
import com.dtools.DeviceOwner.*


// dpm set-device-owner com.springhack.dtools/com.dtools.DeviceOwner.DeviceOwnerReceiver
class ReactDeviceOwnerManager(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    override fun getName(): String {
        return "DeviceOwnerManager";
    }

    override fun getConstants(): Map<String, Any> {
        val constants = HashMap<String, Any>()
        constants.put("UNHIDE", true)
        constants.put("HIDE", false)
        return constants
    }

    @ReactMethod
    fun isDeviceOwner(promise: Promise) {
        try {
            val manager = getReactApplicationContext().getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
            promise.resolve(manager.isDeviceOwnerApp(getReactApplicationContext().getPackageName()))
        } catch (e: Exception) {
            promise.reject(e)
        }

    }

    @ReactMethod
    fun getPackageList(promise: Promise) {
        try {
            val apps = getReactApplicationContext().getPackageManager().getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES)
            val componentName = DeviceOwnerReceiver.getComponentName(getReactApplicationContext())
            val manager = getReactApplicationContext().getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
            val array = Arguments.createArray()
            for (app in apps) {
                val map = Arguments.createMap()
                map.putString("packageName", app.packageName)
                map.putString("appName", app.loadLabel(getReactApplicationContext().getPackageManager()).toString())
                map.putBoolean("systemApp", 0 < app.flags and ApplicationInfo.FLAG_SYSTEM)
                map.putBoolean("enabled", !manager.isApplicationHidden(componentName, app.packageName))
                array.pushMap(map)
            }
            promise.resolve(array)
        } catch (e: Exception) {
            promise.reject(e)
        }

    }

    @ReactMethod
    fun getAppIcon(packageName: String, promise: Promise) {
        try {
            val info = getReactApplicationContext().getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES)
            promise.resolve(ReactDrawable.drawableToBase64(info.loadIcon(getReactApplicationContext().getPackageManager())))
        } catch (e: Exception) {
            promise.reject(e)
        }

    }

    @ReactMethod
    fun setPackageHideState(packageName: String, hidden: Boolean, promise: Promise) {
        try {
            val componentName = DeviceOwnerReceiver.getComponentName(getReactApplicationContext())
            val manager = getReactApplicationContext().getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
            val result = manager.setApplicationHidden(componentName, packageName, !hidden)
            promise.resolve(result)
        } catch (e: Exception) {
            promise.reject(e)
        }

    }

    @ReactMethod
    fun getPackageHideState(packageName: String, promise: Promise) {
        try {
            val componentName = DeviceOwnerReceiver.getComponentName(getReactApplicationContext())
            val manager = getReactApplicationContext().getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
            val result = manager.isApplicationHidden(componentName, packageName)
            promise.resolve(result)
        } catch (e: Exception) {
            promise.reject(e)
        }

    }

}
