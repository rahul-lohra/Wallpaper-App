package com.login.presentation.ui.activity

import android.os.Bundle
import com.core.activities.BaseActivity
import com.login.R
import com.login.presentation.ui.fragments.LoginWebViewFragment

class LoginWebViewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity_search)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, LoginWebViewFragment())
                .commit()
        }
    }
}