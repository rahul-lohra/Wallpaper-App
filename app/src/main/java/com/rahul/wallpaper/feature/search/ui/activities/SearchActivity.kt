package com.rahul.wallpaper.feature.search.ui.activities

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.rahul.wallpaper.BaseActivity
import com.rahul.wallpaper.R
import com.rahul.wallpaper.feature.search.ui.fragments.UnsplashHomeFragment

class SearchActivity : BaseActivity() {
    lateinit var frameLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        frameLayout = findViewById(R.id.container)


        if (savedInstanceState == null) {

            supportFragmentManager.beginTransaction()
//                .add(R.id.container, SearchFragment())
                .add(R.id.container, UnsplashHomeFragment())
                .commit()
        }
    }
}