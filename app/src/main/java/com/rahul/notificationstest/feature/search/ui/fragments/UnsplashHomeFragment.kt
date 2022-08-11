package com.rahul.notificationstest.feature.search.ui.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.scaleMatrix
import androidx.fragment.app.Fragment
import com.rahul.notificationstest.R
import com.rahul.notificationstest.ui.theme.DarkColorPalette
import com.rahul.notificationstest.ui.theme.LightColorPalette
import com.rahul.notificationstest.ui.theme.MySootheTheme
import com.rahul.notificationstest.ui.theme.typography
import java.util.*

class UnsplashHomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                UnsplashHomeComposeLayout()
            }
        }
    }

}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun UnsplashHomeComposeLayout() {
    MaterialTheme(content = {
        Scaffold(
            topBar = { HomeToolbar() },
            content = { padding -> HomeScreen(Modifier.padding(padding)) })
    })
}

@Preview
@Composable
fun HomeToolbar() {
    TopAppBar(

        title = {},
        navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_unsplash),
                tint = Color.Black,
                contentDescription = null,
                modifier = Modifier.padding(start = 17.dp)
            )
        },
        actions = {
            Image(
                imageVector = Icons.Default.Search,
                modifier = Modifier
                    .padding(end = 36.dp)
                    .background(colorResource(id = R.color.grey), CircleShape)
                    .clip(CircleShape)
                    .size(36.dp)
                    .padding(vertical = 9.dp),
                colorFilter = ColorFilter.tint(colorResource(id = R.color.white)),
                contentDescription = null,
            )
        },
        backgroundColor = colorResource(id = R.color.white)
    )
}


@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Header()
}

@Preview
@Composable
fun Header() {
    Column() {
        Text(
            "UnSplash",
            style = typography.h1
        )
        Text(
            "Beautiful, free photos.\n" +
                    "Gifted by the world’s most generous community of photographers.",
            style = typography.caption,
        )
    }
}