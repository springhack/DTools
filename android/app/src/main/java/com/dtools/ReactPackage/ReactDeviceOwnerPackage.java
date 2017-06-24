package com.dtools.ReactPackage;

import com.facebook.react.*;
import com.facebook.react.bridge.*;
import com.facebook.react.uimanager.*;
import java.util.*;

public class ReactDeviceOwnerPackage implements ReactPackage {

    @Override
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();
        modules.add(new ReactDeviceOwnerManager(reactContext));
        return modules;
    }

}
