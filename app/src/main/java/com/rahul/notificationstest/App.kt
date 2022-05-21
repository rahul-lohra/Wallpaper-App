package com.rahul.notificationstest

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.moengage.core.DataCenter
import com.moengage.core.LogLevel
import com.moengage.core.MoEngage
import com.moengage.core.config.*
import com.moengage.pushbase.MoEPushHelper
import com.moengage.pushbase.push.PushMessageListener

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        initialiseMoEngage()
    }

    private fun notificationConfig() = NotificationConfig(
        R.drawable.ic_custom_notification,
        R.drawable.ic_custom_notification,
        -1,
        null,
        isMultipleNotificationInDrawerEnabled = true,
        isBuildingBackStackEnabled = true, isLargeIconDisplayEnabled = true
    )

    private fun initialiseMoEngage() {
//        val moEngage: MoEngage = MoEngage.Builder(this, "getString(R.string.moengage_app_id)")
//            .configureNotificationMetaData(notificationConfig())
//            .configureMiPush(
//                MiPushConfig(
//                    AppConstant.XIAOMI_APP_ID,
//                    AppConstant.XIAOMI_APP_KEY,
//                    true
//                )
//            )
//            .configurePushKit(PushKitConfig())
//            .configureFcm(FcmConfig(false))
//            .configureLogs(LogConfig(LogLevel.VERBOSE, false))
//            .setDataCenter(DataCenter.DATA_CENTER_3)
//            .build()
//        MoEPushHelper.getInstance().messageListener =
//            MyFirebaseMessagingService.CustomPushMessageListener()
//        MoEngage.initialise(moEngage)
    }

    class CustomPushMessageListener : PushMessageListener() {

        override fun onHandleRedirection(activity: Activity, payload: Bundle) {

        }
    }
}