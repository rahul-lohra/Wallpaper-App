package com.rahul.wallpaper.feature.login.presentation.ui.activity

import android.os.Bundle
import com.rahul.wallpaper.BaseActivity
import com.rahul.wallpaper.R
import com.rahul.wallpaper.feature.login.presentation.ui.fragments.LoginWebViewFragment

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