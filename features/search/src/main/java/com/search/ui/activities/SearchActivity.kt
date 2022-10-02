package com.search.ui.activities

import android.os.Bundle
import android.widget.FrameLayout
import com.core.activities.BaseActivity
import com.search.ui.fragments.UnsplashHomeFragment
import com.search.R

class SearchActivity : BaseActivity() {
    lateinit var frameLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity_search)
        frameLayout = findViewById(R.id.container)


        if (savedInstanceState == null) {
            launchFragment()
        }

    }

    private fun launchActivityB(){
//        val intent = Intent(this,LoginWebViewActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
//        startActivity(intent)
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