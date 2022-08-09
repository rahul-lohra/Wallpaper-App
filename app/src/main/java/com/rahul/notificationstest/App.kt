package com.rahul.notificationstest

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.moengage.core.MoEngage
import com.moengage.firebase.MoEFireBaseHelper
import com.moengage.pushbase.MoEPushHelper
import com.moengage.pushbase.push.PushMessageListener
import com.rahul.notificationstest.di.component.DaggerAppComponent
import com.rahul.notificationstest.logger.ServerLoggerInitializerImpl

class App : Application() {

    var isMoengageInitialised = false
    var fcmToken = ""
    override fun onCreate() {
        super.onCreate()
        initDagger()
        checkFcm()
        initialiseMoEngage()
        initDataDog()
    }

    fun initDagger() {
        val component = DaggerAppComponent.create()
        component.inject(this)
    }

    fun initDataDog() {
        ServerLoggerInitializerImpl(this)
            .initialize()
    }

    fun checkFcm() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.d("Noob", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg = "Fcm token $token"
            Log.d("Noob", msg)
            connectMoengageWithFcm(token)
            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })

    }

    fun connectMoengageWithFcm(token: String) {
        if (isMoengageInitialised && token.isNotEmpty()) {
            MoEFireBaseHelper.getInstance().passPushToken(applicationContext, token)
            Log.d("Noob", "connectMoengageWithFcm")
        }
    }

//    private fun notificationConfig() = NotificationConfig(
//        R.drawable.ic_custom_notification,
//        R.drawable.ic_custom_notification,
//        -1,
//        null,
//        isMultipleNotificationInDrawerEnabled = true,
//        isBuildingBackStackEnabled = true, isLargeIconDisplayEnabled = true
//    )

    private fun initialiseMoEngage() {
        val moEngage: MoEngage = MoEngage.Builder(this, "7CPAV1P2UH4277BP5E9QIGYZ")
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
            .build()

        MoEngage.initialiseDefaultInstance(moEngage)
        MoEPushHelper.getInstance().registerMessageListener(CustomPushMessageListener())

        isMoengageInitialised = true
        connectMoengageWithFcm(fcmToken)
    }

    class CustomPushMessageListener : PushMessageListener() {

        override fun onNotificationClick(activity: Activity, payload: Bundle) {
            Log.d("Noob", "onHandleRedirection")
        }

        override fun onNotificationReceived(context: Context, payload: Bundle) {
            super.onNotificationReceived(context, payload)
            Log.d("Noob", "onNotificationReceived")
        }
    }
}