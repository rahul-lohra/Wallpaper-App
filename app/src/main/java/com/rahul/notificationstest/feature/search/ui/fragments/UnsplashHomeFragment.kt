package com.rahul.notificationstest.feature.search.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.rahul.notificationstest.App
import com.rahul.notificationstest.R
import com.rahul.notificationstest.feature.search.data.datasource.DummyDataProvider
import com.rahul.notificationstest.feature.search.di.components.DaggerSearchComponent
import com.rahul.notificationstest.feature.search.ui.viewmodels.SearchViewModel
import com.rahul.notificationstest.onNoRippleClick
import com.rahul.notificationstest.toDp
import com.rahul.notificationstest.ui.theme.typography
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.math.absoluteValue

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
        UnsplashViewPager(photosFlow)
//        PhotosDisplayList(photosFlow = photosFlow)
    }
}

@Preview
@Composable
fun Header() {
    Column(
        modifier = Modifier
            .padding(top = 14.dp, start = 18.dp)
            .height(165.dp)
    ) {
        Text(
            "UnSplash",
            style = typography.h1
        )
        Text(
            modifier = Modifier.padding(end = 73.dp),
            text =
            "Beautiful, free photos.\n" +
                    "Gifted by the world’s most generous community of photographers.",
            style = typography.caption,
        )
        TabViewSmallTextContainer()
    }
}

@Composable
fun TabViewSmallTextContainer() {
    var selected by rememberSaveable { mutableStateOf(0) }

    Row(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(end = 17.dp, bottom = 12.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom
    ) {
        TabViewSmallText(text = "Editorial", selected.absoluteValue == 0) {
            selected = 0
        }
        Box(modifier = Modifier.width(20.dp))
        TabViewSmallText(text = "Following", selected.absoluteValue == 1) {
            selected = 1
        }
    }
}

@Composable
fun TabViewSmallText(text: String, selected: Boolean, onClick: () -> Unit) {
    var dividerWidth by remember { mutableStateOf(0) }

    Column(modifier = Modifier.onNoRippleClick { onClick() }) {
        if (selected) {
            if (dividerWidth > 0) {
                Divider(
                    color = colorResource(id = R.color.black),
                    modifier = Modifier.width(dividerWidth.toFloat().toDp().dp)
                )
            }
            Text(
                text = text,
                style = typography.body2,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 11.dp),
                onTextLayout = {
                    dividerWidth = it.size.width
                    Timber.d("$text width = ${it.size.width}")
                })
        } else {
            Text(text = text, style = typography.body2)
        }

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

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun PhotosDisplayList(photosFlow: Flow<PagingData<String>>) {
    val dataList = photosFlow.collectAsLazyPagingItems()
    val maxColumnSpan = 2
    val minColumnSpan = 1
    val coroutineScope = rememberCoroutineScope()
    val gridState = rememberLazyGridState()
    coroutineScope.launch {

        gridState.scroll {
            val endOffset = gridState.layoutInfo.viewportEndOffset
            val startOffset = gridState.layoutInfo.viewportStartOffset
            Timber.d("startOffset = $startOffset, endOffset = $endOffset")
        }
    }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y

                Timber.d("delta Y = ${delta}, index = ${gridState.firstVisibleItemIndex}, offset = ${gridState.firstVisibleItemScrollOffset}")
                return Offset.Zero
            }
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(maxColumnSpan),
        modifier = Modifier.nestedScroll(nestedScrollConnection),
        state = gridState,
        content = {
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
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = Modifier.height(300.dp),
        contentScale = ContentScale.Crop
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun UnsplashViewPager(photosFlow: Flow<PagingData<String>>) {
    val pagerState = rememberPagerState()
    HorizontalPager(count = 2, state = pagerState) {
        PhotosDisplayList(photosFlow = photosFlow)
    }
}