package com.dtools.DeviceOwner

import android.app.admin.*
import android.content.*

class DeviceOwnerReceiver : DeviceAdminReceiver() {

    override fun onProfileProvisioningComplete(context: Context, intent: Intent) {
        val manager = context.getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        val componentName = getComponentName(context)
        manager.setProfileName(componentName, "DTools")
    }

    companion object {

        fun getComponentName(context: Context): ComponentName {
            return ComponentName(context.applicationContext, DeviceOwnerReceiver::class.java)
        }
    }

}