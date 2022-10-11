package com.search.ui.fragments

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.SubcomposeAsyncImage
import com.data.di.component.AppDataContract
import com.di.app.AppContract
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.logger.ServerLogger
import com.router.RouteManager
import com.search.R
import com.search.di.components.DaggerSearchComponent
import com.search.domain.FollowDomainData
import com.search.domain.FollowPaginatedDataInitial
import com.search.domain.NotLoggedInData
import com.search.ui.viewmodels.RecomposeRequiredException
import com.search.ui.viewmodels.SearchViewModel
import com.unsplash.UnsplashContract
import com.utils.onNoRippleClick
import com.utils.toDp
import com.utils.toPx
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
                UnsplashHomeComposeLayout(
                )
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val appComponent = (context.applicationContext as AppContract).provideAppComponent()
        val unsplashComponent =
            (context.applicationContext as UnsplashContract).provideUnsplashComponent()
        val appDataComponent =
            (context.applicationContext as AppDataContract).provideAppDataComponent()
        DaggerSearchComponent.builder()
            .appComponent(appComponent)
            .appDataComponent(appDataComponent)
            .unsplashComponent(unsplashComponent)
            .build()
            .inject(this)

        searchViewModel = ViewModelProvider(this, viewModelFactory)[SearchViewModel::class.java]
    }

}

@Composable
fun UnsplashHomeComposeLayout(
) {

    MaterialTheme(content = {
        Scaffold(
            content = { padding ->
                HomeScreen(
                    Modifier.padding(padding),
                )
            })
    })
}

@Composable
fun HomeScreen(
    modifier: Modifier,
) {

    val heightOfSearchBarComponent = remember {
        mutableStateOf(0)
    }
    val scrollChange = remember {
        mutableStateOf(1f)
    }

    Box {
        if (heightOfSearchBarComponent.value > 0f) {
            HeaderWithPhotosList(
                modifier,
                heightOfSearchBarComponent.value.toFloat()
            ) {
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
    heightOfSearchBarComponent: Float,
    scrollChangeCallback: (Float) -> Unit
) {

    ScrollableContent(
        modifier = modifier,
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
                painter = painterResource(id = R.drawable.search_ic_unsplash),
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
                    .background(colorResource(id = R.color.search_grey), CircleShape)
                    .clip(CircleShape)
                    .size(36.dp)
                    .align(Alignment.CenterVertically)
                    .padding(vertical = 9.dp),
                colorFilter = ColorFilter.tint(colorResource(id = android.R.color.white)),
                contentDescription = null,
            )
        },
        backgroundColor = colorResource(id = android.R.color.white),
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
            UnsplashViewPager()
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
            style = com.core.compose.theme.typography.h1
        )
        Text(
            modifier = Modifier.padding(end = 73.dp),
            text =
            "Beautiful, free photos.\n" +
                    "Gifted by the world’s most generous community of photographers.",
            style = com.core.compose.theme.typography.caption,
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
                    color = colorResource(id = android.R.color.black),
                    modifier = Modifier.width(dividerWidth.toFloat().toDp().dp)
                )
            }
            Text(
                text = text,
                style = com.core.compose.theme.typography.body2,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = android.R.color.black),
                modifier = Modifier.padding(top = 11.dp),
                onTextLayout = {
                    dividerWidth = it.size.width
                })
        } else {
            Text(
                text = text,
                style = com.core.compose.theme.typography.body2,
                color = colorResource(id = R.color.search_grey_5)
            )
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun PhotosDisplayList(photosFlow: Flow<PagingData<String>>, data: Int, recompose: () -> Unit) {
    val dataList: LazyPagingItems<String> = photosFlow.collectAsLazyPagingItems()
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
            columns = GridCells.Fixed(1),
            modifier = Modifier
                .nestedScroll(nestedScrollConnection),
            state = gridState,
            content = {
                item {
                    CreateListItemStatesHeader(dataList.loadState.prepend) {
                        dataList.retry()
                        if (dataList.loadState.refresh is LoadState.Error) {
                            val error = (dataList.loadState.refresh as LoadState.Error).error
                            if (error is RecomposeRequiredException) {
                                recompose.invoke()
                            }
                        }
                    }
                }
                items(
                    dataList.itemCount,
                    span = { index ->
                        if (index % 3 == 0) {
                            GridItemSpan(maxColumnSpan)
                        } else {
                            GridItemSpan(minColumnSpan)
                        }
                    },
                    itemContent = { index ->
                        ServerLogger.d(
                            "PhotosListItem",
                            "index=$index, data=${dataList[index]}, count=${dataList.itemCount}"
                        )
                        val listItemData = dataList[index]
                        if (listItemData != null) {
                            PhotosListItem(listItemData)
                        } else {
                            //Do nothing
//                            val message = "index=$index, data=${dataList[index]}, count=${dataList.itemCount}"
//                            Text(text = "Item is dropped, $message")
//                            dataList.loadState.source
                        }

                    }) //TODO Rahul why force null?
                item {
                    CreateListItemStatesCenter(
                        dataList.loadState.refresh,
                        dataList.loadState.append
                    ) {
                        dataList.retry()
                        if (dataList.loadState.refresh is LoadState.Error) {
                            val error = (dataList.loadState.refresh as LoadState.Error).error
                            if (error is RecomposeRequiredException) {
                                recompose.invoke()
                            }
                        }
                    }
                }
            })
    }
}

@Composable
fun CreateListItemStatesHeader(loadState: LoadState, retry: () -> Unit) {
    when (loadState) {
        is LoadState.Loading -> Box(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.Black, modifier = Modifier.height(36.dp))
        }
        is LoadState.Error -> ErrorListItem(retry)
        else -> {}
    }
}

@Composable
fun CreateListItemStatesCenter(refreshState: LoadState, appendState: LoadState, retry: () -> Unit) {
    when (refreshState) {
        is LoadState.Loading -> Box(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Loading your data ...", modifier = Modifier.align(Alignment.Center))
        }
        is LoadState.Error -> Box(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            ErrorListItem(retry)
        }
        else -> {
            CreateListItemStatesHeader(appendState, retry = retry)
        }
    }
}

@Composable
fun ErrorListItem(onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Unable to load data. Please retry after some time",
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        UnifyButton("Retry", onClick)
        Spacer(modifier = Modifier.height(24.dp))
    }
}

const val PHOTO_LIST_ITEM_HEIGHT = 300

fun getRandomColors(): Color {
    val colorsArray =
        arrayOf(Color.Cyan, Color.Red, Color.Yellow, Color.Gray, Color.Green, Color.Magenta)
    return colorsArray[(colorsArray.indices).random()]
}

@Composable
fun PhotosListItem(url: String) {
    Box(modifier = Modifier.border(width = 2.dp, color = getRandomColors())) {
        SubcomposeAsyncImage(
            model = url,
            contentDescription = null,
            modifier = Modifier.height(PHOTO_LIST_ITEM_HEIGHT.dp),
            contentScale = ContentScale.Crop,
            loading = {
                Text(text = "Loading Image")
            },
            error = {
                Text(text = "Unable to load Image")
            }
        )
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun UnsplashViewPager() {
    val pagerState = rememberPagerState()
    HorizontalPager(count = 2, state = pagerState, verticalAlignment = Alignment.Top) { page ->
        if (page == 0) {
            var forceRecompose by remember {
                ServerLogger.d("Unsplash", "forceRecompose PhotosDisplayList")
                mutableStateOf(0)
            }
            ServerLogger.d("Unsplash", "Render PhotosDisplayList")
            PhotosDisplayList(
                photosFlow = viewModel(
                    modelClass = SearchViewModel::class.java
                ).photosFlow, forceRecompose
            ) {

                ServerLogger.d("Unsplash", "Hello PhotosDisplayList")
                forceRecompose += 1
                ServerLogger.d("Unsplash", "World PhotosDisplayList")
            }
        } else {
            FollowersUi()
        }
    }
}

@Composable
fun FollowersUi(
    viewModel: SearchViewModel = viewModel(
        modelClass = SearchViewModel::class.java
    )
) {
    val context = LocalContext.current
    val state by viewModel.followersFlow.collectAsState(initial = FollowPaginatedDataInitial)
    RenderFollowersUi(state) {
        //TODO Rahul uncomment and fix
//        RouteManager.getInstance().route(context, "login-unsplash")
    }
}

@Composable
fun RenderFollowersUi(state: FollowDomainData, onButtonClick: () -> Unit) {
    when (state) {

        FollowPaginatedDataInitial -> {
            var screenHeight = 0f
            LocalConfiguration.current.apply {
                screenHeight = screenHeightDp.toFloat()
            }
            Box(
                modifier = Modifier
                    .height(screenHeight.dp)
//                    .background(color = Color.Cyan)
            ) {
                PleaseLogin()
            }
        }
        NotLoggedInData -> Button(onClick = {
            onButtonClick()
        }, content = { Text(text = "Please Login") })
        else -> Text(text = "Else")
    }
}

@Composable
fun PleaseLogin() {
    val context = LocalContext.current
    Column(
        Modifier
            .height(300.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Please Login to see your following contents")
        Spacer(modifier = Modifier.height(24.dp))
        UnifyButton("Login") {
            RouteManager.getInstance().route(context, "login-unsplash")
        }
    }
}

@Composable
fun UnifyButton(text: String, onClick: () -> Unit) {
    Button(
        contentPadding = PaddingValues(horizontal = 46.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Black,
            contentColor = Color.White
        ),
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = text)
        }

    }
}