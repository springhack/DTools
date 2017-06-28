package com.dtools.ReactPackage

import android.view.View
import com.facebook.react.*
import com.facebook.react.bridge.*
import com.facebook.react.uimanager.*
import java.util.*

class ReactDeviceOwnerPackage : ReactPackage {

    override fun createJSModules(): List<Class<out JavaScriptModule>> {
        return emptyList<Class<out JavaScriptModule>>()
    }

    override fun createViewManagers(reactContext: ReactApplicationContext): List<ViewManager<View, ReactShadowNode>> {
        return emptyList<ViewManager<View, ReactShadowNode>>()
    }

    override fun createNativeModules(reactContext: ReactApplicationContext): List<NativeModule> {
        val modules = ArrayList<NativeModule>()
        modules.add(ReactDeviceOwnerManager(reactContext))
        return modules
    }

}
