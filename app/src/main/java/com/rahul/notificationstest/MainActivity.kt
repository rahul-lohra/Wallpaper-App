package com.rahul.notificationstest

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.datadog.android.log.Logger
import com.google.android.gms.common.GoogleApiAvailability
import com.rahul.notificationstest.logger.DatadogLogger

class MainActivity : AppCompatActivity() {
    lateinit var btn:Button
    val TAG = "MainActivity"
    lateinit var datadog: DatadogLogger
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val playServiceResult = GoogleApiAvailability.getInstance()
            .isGooglePlayServicesAvailable(this)
        datadog = DatadogLogger()
        datadog.setupDatadog(this)
        Log.d("Noob","is play service available = $playServiceResult")
//        GoogleApiAvailability.getInstance()
//            .makeGooglePlayServicesAvailable(this)
        btn = findViewById(R.id.btn)
        btn.setOnClickListener {

//            ServerLogger.track(Priority.P1,TAG,"btn click")

            val log = Logger.Builder()
                .setServiceName(packageName)
                .setNetworkInfoEnabled(true)
                .setLogcatLogsEnabled(true)
//                .setDatadogLogsEnabled(!BuildConfig.DEBUG)
                .setDatadogLogsEnabled(true)
                .setBundleWithTraceEnabled(true)
//                .setLoggerName("appData.loggerName")
                .build()
//            log.addAttribute("version_name", appData.versionName)
//            log.addAttribute("version_code", appData.versionCode)
            log.d("Hello")
        }
    }


}