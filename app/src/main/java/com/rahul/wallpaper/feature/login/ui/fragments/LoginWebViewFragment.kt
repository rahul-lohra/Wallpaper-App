package com.rahul.wallpaper.feature.login.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rahul.wallpaper.App
import com.rahul.wallpaper.feature.login.di.DaggerLoginComponent
import com.rahul.wallpaper.feature.login.ui.LoginPage
import com.rahul.wallpaper.feature.login.viewmodel.LoginWebViewViewModel
import javax.inject.Inject

class LoginWebViewFragment : Fragment() {
    lateinit var viewModel: LoginWebViewViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                Scaffold(content = { padding ->
                    LoginPage(Modifier.padding(padding), viewModel.getLoginUrl())
                })
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerLoginComponent.factory()
            .create((context.applicationContext as App).appComponent)
            .inject(this)


        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginWebViewViewModel::class.java)
    }
}