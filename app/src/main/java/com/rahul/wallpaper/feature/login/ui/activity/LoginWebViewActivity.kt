package com.rahul.wallpaper.feature.login.ui.activity

import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.ViewModelProvider
import com.rahul.wallpaper.App
import com.rahul.wallpaper.BaseActivity
import com.rahul.wallpaper.feature.login.di.DaggerLoginComponent
import com.rahul.wallpaper.feature.login.viewmodel.LoginWebViewViewModel
import javax.inject.Inject

class LoginWebViewActivity : BaseActivity() {

    lateinit var viewModel: LoginWebViewViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerLoginComponent.factory()
            .create((application as App).appComponent)
            .inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[LoginWebViewViewModel::class.java]

        setContent {
            Scaffold(content = { padding ->
                LoginPage(Modifier.padding(padding), viewModel.getLoginUrl())
            })
        }
    }
}

@Composable
fun LoginPage(modifier: Modifier, url: String) {
    AndroidView(factory = { context ->
        WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
        }
    }, update = {
        it.loadUrl(url)
    })
}