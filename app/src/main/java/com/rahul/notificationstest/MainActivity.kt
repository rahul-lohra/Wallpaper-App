package com.rahul.notificationstest

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.dehaat.logger.ServerLogger
import com.dehaat.logger.data.Priority
import com.google.android.gms.common.GoogleApiAvailability
import com.rahul.notificationstest.logger.DatadogLogger
import com.rahul.notificationstest.network.NetworkClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {
    lateinit var btn:Button
    val TAG = "MainActivity"
    lateinit var datadog: DatadogLogger
    val networkClient = NetworkClient()
    lateinit var okHttpClient:OkHttpClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val playServiceResult = GoogleApiAvailability.getInstance()
            .isGooglePlayServicesAvailable(this)
        datadog = DatadogLogger()
        datadog.setupDatadog(this)
        okHttpClient = networkClient.setupOkHttp()

        Log.d("Noob","is play service available = $playServiceResult")
        btn = findViewById(R.id.btn)
        btn.setOnClickListener {
            sendButtonTapLogs()
            makeApiCall()
        }
    }

    fun makeApiCall(){
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val request: Request = Request.Builder()
                    .url("https://baconipsum.com/api/?type=meat-and-filler")
                    .method("GET",null)
                    .build()

                val response = okHttpClient.newCall(request).execute()
                Log.d(TAG,"Got response")
                ServerLogger.d(Priority.P2,TAG,"response - ${response.body?.string()}")
            }catch (ex:Exception){
                ServerLogger.e(Priority.P2,TAG,"error in api call", ex)
            }

        }

    }

    fun sendButtonTapLogs(){
        ServerLogger.d(Priority.P1,TAG,"btn click")

//            val log = Logger.Builder()
//                .setServiceName(packageName)
//                .setNetworkInfoEnabled(true)
//                .setLogcatLogsEnabled(true)
////                .setDatadogLogsEnabled(!BuildConfig.DEBUG)
//                .setDatadogLogsEnabled(true)
//                .setBundleWithTraceEnabled(true)
////                .setLoggerName("appData.loggerName")
//                .build()
////            log.addAttribute("version_name", appData.versionName)
////            log.addAttribute("version_code", appData.versionCode)
//            log.d("Hello")

    }


}