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
import com.rahul.wallpaper.R
import com.rahul.wallpaper.feature.login.di.DaggerLoginComponent
import com.rahul.wallpaper.feature.login.ui.LoginPage
import com.rahul.wallpaper.feature.login.ui.fragments.LoginWebViewFragment
import com.rahul.wallpaper.feature.login.viewmodel.LoginWebViewViewModel
import com.rahul.wallpaper.feature.search.ui.fragments.UnsplashHomeFragment
import javax.inject.Inject

class LoginWebViewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, LoginWebViewFragment())
                .commit()
        }
    }
}