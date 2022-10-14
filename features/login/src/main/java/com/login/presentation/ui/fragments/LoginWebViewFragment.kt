package com.login.presentation.ui.fragments

import LogData
import android.content.Context
import android.os.Build.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.core.compose.theme.AppTheme
import com.data.di.component.AppDataContract
import com.di.app.AppContract
import com.logger.ServerLogger
import com.logger.data.Priority
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
        if (VERSION.SDK_INT >= VERSION_CODES.R) {
//            activity?.window?.setDecorFitsSystemWindows(true)
            activity?.window
                ?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        } else {
            activity?.window
                ?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }

        viewModel.clearCookies()
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                AppTheme() {
                    Scaffold(content = { padding ->
                        Column {
                            AppToolbar(Modifier, Color.Black) {
                                backPress()
                            }
                            val uiState = viewModel.uiState
                            when (uiState) {
                                is UiStateInitial -> {}
                                is UiStateLoading -> {
                                    CircularProgressIndicator()
                                }
                                is UiStateSuccess -> {
                                    HandleUiStateSuccess(padding)
                                    if (uiState.isLoggedIn) {
                                        ServerLogger.d("Login", "Success")
                                        backPress()
                                    } else {
                                        ServerLogger.d("Login", "Not Success")
                                    }
                                }
                                is UiStateFail -> {
                                    HandleUiStateSuccess(padding)
                                    ServerLogger.e(
                                        Priority.P1,
                                        LogData("Login", "UIStateFail"),
                                        uiState.th
                                    )
                                }
                            }
                        }
                    })
                }
            }
        }


    }

    @Composable
    fun HandleUiStateSuccess(padding: PaddingValues) {
        LoginPage(Modifier.padding(padding), viewModel.getLoginUrl()) {
            viewModel.processPageFinishedUrl(it)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val appComponent = (context.applicationContext as AppContract).provideAppComponent()
        val unsplashComponent =
            (context.applicationContext as UnsplashContract).provideUnsplashComponent()
        val appDataComponent =
            (context.applicationContext as AppDataContract).provideAppDataComponent()
        DaggerLoginComponent.builder()
            .appComponent(appComponent)
            .unsplashComponent(unsplashComponent)
            .appDataComponent(appDataComponent)
            .build()
            .inject(this)


        viewModel = ViewModelProvider(this, viewModelFactory)[LoginWebViewViewModel::class.java]
    }

    private fun backPress() {
        activity?.finish()
    }
}

@Composable
fun AppToolbar(
    modifier: Modifier,
    iconColor: Color = MaterialTheme.colors.onBackground,
    onClick: () -> Unit
) {
    TopAppBar(
        elevation = 0.dp,
        title = {},
        navigationIcon = {
            IconButton(onClick) {
                Icon(
                    painter = painterResource(id = com.core.R.drawable.unsplash_back),
                    tint = iconColor,
                    contentDescription = null,
                    modifier = modifier.padding(start = 17.dp)
                )
            }

        },
        backgroundColor = MaterialTheme.colors.background,
    )
}