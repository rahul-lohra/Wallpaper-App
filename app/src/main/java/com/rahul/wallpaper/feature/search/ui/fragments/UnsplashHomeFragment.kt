package com.rahul.wallpaper.feature.search.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.*
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.rahul.wallpaper.*
import com.rahul.wallpaper.R
import com.rahul.wallpaper.feature.search.di.components.DaggerSearchComponent
import com.rahul.wallpaper.feature.search.ui.viewmodels.SearchViewModel
import com.rahul.wallpaper.ui.theme.typography
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject
import kotlin.math.absoluteValue

class UnsplashHomeFragment : Fragment() {


    lateinit var searchViewModel: SearchViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

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

        searchViewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
    }

}

@Composable
fun UnsplashHomeComposeLayout(photosFlow: Flow<PagingData<String>>) {

    MaterialTheme(content = {
        Scaffold(
            content = { padding -> HomeScreen(Modifier.padding(padding), photosFlow) })
    })
}

@Composable
fun HomeScreen(modifier: Modifier, photosFlow: Flow<PagingData<String>>) {

    val heightOfSearchBarComponent = remember {
        mutableStateOf(0)
    }
    val scrollChange = remember {
        mutableStateOf(1f)
    }

    Box {
        if (heightOfSearchBarComponent.value > 0f) {
            HeaderWithPhotosList(modifier, photosFlow, heightOfSearchBarComponent.value.toFloat()) {
                scrollChange.value = it
            }
        }

        SearchBarWithHorizontalTabs(scrollChange.value) {
            heightOfSearchBarComponent.value = it
        }
    }
}

@Composable
fun HeaderWithPhotosList(
    modifier: Modifier,
    photosFlow: Flow<PagingData<String>>,
    heightOfSearchBarComponent: Float,
    scrollChangeCallback: (Float) -> Unit
) {

    ScrollableContent(
        modifier = modifier,
        photosFlow = photosFlow,
        heightOfSearchBarComponent = heightOfSearchBarComponent,
    ) {
        scrollChangeCallback(it)
    }
}

@Composable
fun SearchBarWithHorizontalTabs(scrollChange: Float, heightOfComponentCallback: (Int) -> Unit) {

    val heightOfSearchBarComponent = remember {
        mutableStateOf(0)
    }


    Column(modifier = Modifier
        .alpha(1f - scrollChange)
        .graphicsLayer {
            translationY = -scrollChange * heightOfSearchBarComponent.value
        }
        .onGloballyPositioned {
            val h = it.size.height
            heightOfSearchBarComponent.value = h
            heightOfComponentCallback(heightOfSearchBarComponent.value)
        }
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        SearchView(Modifier.padding(start = 16.dp, end = 16.dp, bottom = 10.dp))
        ScrollableTabLayout()
    }
}

@Composable
fun HomeToolbar(modifier: Modifier) {
    TopAppBar(
        elevation = 0.dp,
        title = {},
        navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_unsplash),
                tint = Color.Black,
                contentDescription = null,
                modifier = modifier.padding(start = 17.dp)
            )
        },
        actions = {
            Image(
                imageVector = Icons.Default.Search,
                modifier = Modifier
                    .padding(end = 36.dp, top = 6.dp)
                    .background(colorResource(id = R.color.grey), CircleShape)
                    .clip(CircleShape)
                    .size(36.dp)
                    .align(Alignment.CenterVertically)
                    .padding(vertical = 9.dp),
                colorFilter = ColorFilter.tint(colorResource(id = R.color.white)),
                contentDescription = null,
            )
        },
        backgroundColor = colorResource(id = R.color.white),
    )
}

var firstItemVisibleOffset = 0

fun getValidOffset(tempYOffset: Float, maxScroll: Float, minScroll: Float): Float {
    return if (tempYOffset >= maxScroll) {
        maxScroll
    } else if (tempYOffset <= minScroll) {
        minScroll
    } else {
        tempYOffset
    }
}

fun getAlpha(tempYOffset: Float, minScroll: Float): Float {
    return 1f - tempYOffset / minScroll
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ScrollableContent(
    modifier: Modifier,
    photosFlow: Flow<PagingData<String>>,
    heightOfSearchBarComponent: Float,
    scrollIngPercentage: (Float) -> Unit,
) {

    var screenHeight = 0
    val combinedHeightToolbarAndHeader = remember {
        mutableStateOf(0)
    }

    val maxScroll = 0f
    val minScroll = -(Math.max(
        combinedHeightToolbarAndHeader.value,
        heightOfSearchBarComponent.toInt()
    ) - Math.min(
        combinedHeightToolbarAndHeader.value,
        heightOfSearchBarComponent.toInt()
    )).toFloat()
    val headerScrollRange = (minScroll..maxScroll)
    val midPointOfScroll = minScroll / 2

    LocalConfiguration.current.apply {
        screenHeight = screenHeightDp
    }

    var yOffset by rememberSaveable { mutableStateOf(0f) }
    val nestedScrollConnection =
        object : NestedScrollConnection {

            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {

                val delta = available.y
                val tempYOffset = yOffset + delta
                if (firstItemVisibleOffset == 0) {
                    yOffset = getValidOffset(tempYOffset, maxScroll, minScroll)
                    scrollIngPercentage.invoke(getAlpha(yOffset, minScroll))
                }
                Timber.d("onPreScroll delta = $delta, yOffset = $yOffset,tempYOffset = $tempYOffset, tempYOffset in range = ${tempYOffset in headerScrollRange}, firstItemVisibleOffset = $firstItemVisibleOffset")

                if (tempYOffset in headerScrollRange && firstItemVisibleOffset == 0) {
                    return Offset(0f, delta)
                } else {
                    return Offset.Zero
                }
            }
        }
    val scrollableContentHeight = minScroll.absoluteValue
    val requiredHeight = (screenHeight.toFloat().toPx() + scrollableContentHeight).toDp().dp
    val topPadding = (scrollableContentHeight.toDp()).dp
    Box(contentAlignment = Alignment.TopStart) {
        Column(
            modifier
                .motionEventSpy {
                    //snap behaviour
                    when (it.action) {
                        MotionEvent.ACTION_UP -> {
                            Timber.d("action up = $yOffset")
                            //midPointOfScroll will be negative like -300 and yOffset will range from 0 to -290
                            if (yOffset != minScroll || yOffset != maxScroll) {
                                if (yOffset > midPointOfScroll) {
                                    yOffset = maxScroll
                                } else {
                                    yOffset = minScroll
                                }
                            }
                        }
                    }
                }
                .graphicsLayer {
                    translationY = yOffset
                }
                .padding(top = topPadding)
                .requiredHeight(requiredHeight) //screen height(735.dp) in pixel 3a emulator + scrollable content height
                .nestedScroll(nestedScrollConnection),
            verticalArrangement = Arrangement.aligned(Alignment.Top),
        ) {
            val alphaModifier = Modifier.graphicsLayer { alpha = getAlpha(yOffset, minScroll) }
            FirstToolbarWithHeader(alphaModifier) {
                if (combinedHeightToolbarAndHeader.value == 0) {
                    combinedHeightToolbarAndHeader.value = it
                }
            }
            UnsplashViewPager(photosFlow)
        }
    }
}

@Composable
fun FirstToolbarWithHeader(modifier: Modifier, onHeightChangeCallback: (Int) -> Unit) {
    Column(modifier.onGloballyPositioned {
        onHeightChangeCallback(it.size.height)
    }) {
        HomeToolbar(modifier)
        Header(modifier)
    }
}

@Composable
fun Header(modifier: Modifier) {
    Column(
        modifier = modifier
            .padding(top = 0.dp, start = 18.dp)
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
                color = colorResource(id = R.color.black),
                modifier = Modifier.padding(top = 11.dp),
                onTextLayout = {
                    dividerWidth = it.size.width
                })
        } else {
            Text(text = text, style = typography.body2, color = colorResource(id = R.color.grey_5))
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun PhotosDisplayList(photosFlow: Flow<PagingData<String>>) {
    val dataList = photosFlow.collectAsLazyPagingItems()
    val maxColumnSpan = 2
    val minColumnSpan = 1
    val gridState = rememberLazyGridState()
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                Timber.d("grid delta = ${delta}")
                firstItemVisibleOffset = gridState.firstVisibleItemScrollOffset
                Timber.d("firstItemScrollOffset = ${gridState.firstVisibleItemScrollOffset}, firstVisibleItemIndex = ${gridState.firstVisibleItemIndex}")
                return Offset.Zero
            }
        }
    }

    CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(maxColumnSpan),
            modifier = Modifier
                .nestedScroll(nestedScrollConnection),
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

}

const val PHOTO_LIST_ITEM_HEIGHT = 300

@Composable
fun PhotosListItem(url: String) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = Modifier.height(PHOTO_LIST_ITEM_HEIGHT.dp),
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