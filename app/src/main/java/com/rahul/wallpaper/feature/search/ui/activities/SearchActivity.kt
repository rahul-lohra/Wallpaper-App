package com.rahul.wallpaper.feature.search.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.rahul.wallpaper.BaseActivity
import com.rahul.wallpaper.R
import com.rahul.wallpaper.feature.login.presentation.ui.activity.LoginWebViewActivity
import com.rahul.wallpaper.feature.search.ui.fragments.SearchFragment
import com.rahul.wallpaper.feature.search.ui.fragments.UnsplashHomeFragment

class SearchActivity : BaseActivity() {
    lateinit var frameLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        frameLayout = findViewById(R.id.container)


        if (savedInstanceState == null) {
            launchFragment()
        }

    }

    private fun launchActivityB(){
        val intent = Intent(this,LoginWebViewActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }
    private fun launchFragment(){
        supportFragmentManager.beginTransaction()
//            .replace(R.id.container, SearchFragment())
//            .addToBackStack("search")
            .add(R.id.container, UnsplashHomeFragment())
            .addToBackStack("unsplash")
            .commit()

//        supportFragmentManager.beginTransaction()
////            .replace(R.id.container, SearchFragment())
////            .addToBackStack("search")
//            .replace(R.id.container, SearchFragment())
//            .addToBackStack("unsplash")
//            .commit()
    }
}