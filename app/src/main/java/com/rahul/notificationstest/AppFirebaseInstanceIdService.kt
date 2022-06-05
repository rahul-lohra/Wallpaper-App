package com.rahul.notificationstest

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.moengage.firebase.MoEFireBaseHelper
import com.moengage.pushbase.MoEPushHelper

class AppFirebaseInstanceIdService: FirebaseMessagingService() {
    val TAG = "Noob"
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d(TAG,"onNewToken: $p0")

        (applicationContext as App).run{
            fcmToken = p0
            connectMoengageWithFcm(fcmToken)
        }
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        Log.d(TAG,"onMessageReceived: $p0")
        if (MoEPushHelper.getInstance().isFromMoEngagePlatform(p0.data)) {
            MoEFireBaseHelper.getInstance().passPushPayload(applicationContext, p0.data)
        } else {
            Log.d(TAG,"handle in app: $p0")
        }
    }
}