package com.rahul.notificationstest.feature.search.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.rahul.notificationstest.App
import com.rahul.notificationstest.R
import com.rahul.notificationstest.di.component.AppComponent
import com.rahul.notificationstest.feature.search.data.datasource.DummyDataProvider
import com.rahul.notificationstest.feature.search.di.components.DaggerSearchComponent
import com.rahul.notificationstest.feature.search.ui.viewmodels.SearchViewModel
import com.rahul.notificationstest.ui.theme.typography
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UnsplashHomeFragment : Fragment() {

    @Inject
    lateinit var searchViewModel: SearchViewModel

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
                UnsplashHomeComposeLayout(photosFlow = searchViewModel.photosFlow)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerSearchComponent.factory()
            .create((context.applicationContext as App).appComponent)
            .inject(this)
    }

}

@Composable
fun UnsplashHomeComposeLayout(photosFlow: Flow<PagingData<String>>) {
    MaterialTheme(content = {
        Scaffold(
            topBar = { HomeToolbar() },
            content = { padding -> HomeScreen(Modifier.padding(padding), photosFlow) })
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
fun HomeScreen(modifier: Modifier = Modifier, photosFlow: Flow<PagingData<String>>) {
    Column {
        Header()
        WeekdaysList()
        PhotosDisplayList(photosFlow = photosFlow)
    }
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

@Preview
@Composable
fun WeekdaysList() {
    val lazyDataItems = DummyDataProvider().getDataArrayList()
    val maxColumnSpan = 2
    val minColumnSpan = 1
    LazyVerticalGrid(columns = GridCells.Fixed(maxColumnSpan), content = {
        items(lazyDataItems.size, span = { index ->
            if (index % 3 == 0) {
                GridItemSpan(maxColumnSpan)
            } else {
                GridItemSpan(minColumnSpan)
            }
        }, itemContent = { index -> Text(text = lazyDataItems[index]) })
    })
}

@Composable
fun PhotosDisplayList(photosFlow: Flow<PagingData<String>>) {
    val dataList = photosFlow.collectAsLazyPagingItems()
    val maxColumnSpan = 2
    val minColumnSpan = 1
    LazyVerticalGrid(columns = GridCells.Fixed(maxColumnSpan), content = {
        items(
            dataList.itemCount,
            span = { index ->
                if (index % 3 == 0) {
                    GridItemSpan(maxColumnSpan)
                } else {
                    GridItemSpan(minColumnSpan)
                }
            },
            itemContent = { index -> PhotosListItem(url = dataList[index]!!) }) //TODO Rahul why force null?
    })
}


@Composable
fun PhotosListItem(url: String) {
    AsyncImage(model = url, contentDescription = null)
}