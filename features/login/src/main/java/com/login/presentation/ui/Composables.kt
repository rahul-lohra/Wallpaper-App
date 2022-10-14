package com.login.presentation.ui

import android.net.Uri
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.logger.ServerLogger

@Composable
fun LoginPage(
    modifier: Modifier, url: String, onPageLoadFinish: (url: String) -> Unit
) {
    AndroidView(factory = { context ->
        WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            clearCache(true)
            clearFormData()
            clearHistory()

            webViewClient = object : WebViewClient() {
                override fun shouldInterceptRequest(
                    view: WebView?,
                    request: WebResourceRequest?
                ): WebResourceResponse? {
                    ServerLogger.d("Webview","shouldInterceptRequest url = ${request?.url?.toString()}")
                    return super.shouldInterceptRequest(view, request)
                }

                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    ServerLogger.d("Webview","shouldOverrideUrlLoading url = ${request?.url?.toString()}")
                    return super.shouldOverrideUrlLoading(view, request)
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    ServerLogger.d("Webview","onPageFinished url = ${url}")
                    if(url!=null)
                        onPageLoadFinish(url)
                    super.onPageFinished(view, url)
                }
            }
        }
    }, update = {
        it.loadUrl(url)
    })
}