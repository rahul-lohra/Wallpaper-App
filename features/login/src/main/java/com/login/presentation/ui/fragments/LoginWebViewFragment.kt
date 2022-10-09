package com.login.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.data.di.component.AppDataContract
import com.di.app.AppContract
import com.login.di.DaggerLoginComponent
import com.login.presentation.ui.*
import com.login.presentation.viewmodel.LoginWebViewViewModel
import com.unsplash.UnsplashContract
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
        viewModel.clearCookies()
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                Scaffold(content = { padding ->
                    val uiState = viewModel.uiState
                    when (uiState) {
                        is UiStateInitial -> {}
                        is UiStateLoading -> {
                            CircularProgressIndicator()
                        }
                        is UiStateSuccess -> {
                            LoginPage(Modifier.padding(padding), viewModel.getLoginUrl(), {
                                viewModel.processPageFinishedUrl(it)
                            }, {

                            })
                        }
                        is UiStateFail -> {}
                    }
                })
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val appComponent = (context.applicationContext as AppContract).provideAppComponent()
        val unsplashComponent =
            (context.applicationContext as UnsplashContract).provideUnsplashComponent()
        val appDataComponent = (context.applicationContext as AppDataContract).provideAppDataComponent()
        DaggerLoginComponent.builder()
            .appComponent(appComponent)
            .unsplashComponent(unsplashComponent)
            .appDataComponent(appDataComponent)
            .build()
            .inject(this)


        viewModel = ViewModelProvider(this, viewModelFactory)[LoginWebViewViewModel::class.java]
    }
}