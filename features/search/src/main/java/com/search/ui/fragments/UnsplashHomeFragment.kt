package com.search.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.SubcomposeAsyncImage
import com.core.compose.theme.AppTheme
import com.data.di.component.AppDataContract
import com.di.app.AppContract
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.logger.ServerLogger
import com.router.RouteManager
import com.search.R
import com.search.data.datasource.DummyDataProvider
import com.search.di.components.DaggerSearchComponent
import com.search.ui.models.*
import com.search.ui.viewmodels.SearchViewModel
import com.unsplash.UnsplashContract
import com.utils.onNoRippleClick
import com.utils.toDp
import com.utils.toPx
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
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

    AppTheme(content = {
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
    val selectedTabIndex = remember {
        mutableStateOf(0)
    }

    Box {
        if (heightOfSearchBarComponent.value > 0f) {
            HeaderWithPhotosList(
                modifier,
                heightOfSearchBarComponent.value.toFloat(),
                {
                    scrollChange.value = it
                }, {
                    selectedTabIndex.value = it
                })
        }

        SearchBarWithHorizontalTabs(scrollChange.value, selectedTabIndex.value) {
            heightOfSearchBarComponent.value = it
        }
    }
}

@Composable
fun HeaderWithPhotosList(
    modifier: Modifier,
    heightOfSearchBarComponent: Float,
    scrollChangeCallback: (Float) -> Unit,
    onTabSelected: (Int) -> Unit
) {

    ScrollableContent(
        modifier = modifier,
        heightOfSearchBarComponent = heightOfSearchBarComponent,
        {
            scrollChangeCallback(it)
        }, onTabSelected
    )
}

@Composable
fun SearchBarWithHorizontalTabs(
    scrollChange: Float,
    selectIndex: Int,
    heightOfComponentCallback: (Int) -> Unit
) {

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
        ScrollableTabLayout(selectIndex)
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
                tint = MaterialTheme.colors.onBackground,
                contentDescription = null,
                modifier = modifier.padding(start = 17.dp)
            )
        },
        actions = {
            Image(
                imageVector = Icons.Default.Search,
                modifier = Modifier
                    .padding(end = 36.dp, top = 6.dp)
                    .background(MaterialTheme.colors.surface, CircleShape)
                    .clip(CircleShape)
                    .size(36.dp)
                    .align(Alignment.CenterVertically)
                    .padding(vertical = 9.dp),
                colorFilter = ColorFilter.tint(Color.White),
                contentDescription = null,
            )
        },
        backgroundColor = MaterialTheme.colors.background,
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

@OptIn(ExperimentalComposeUiApi::class, ExperimentalPagerApi::class)
@Composable
fun ScrollableContent(
    modifier: Modifier,
    heightOfSearchBarComponent: Float,
    scrollIngPercentage: (Float) -> Unit,
    onTabSelected: (Int) -> Unit
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

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    var selectedTab by rememberSaveable { mutableStateOf(0) }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.distinctUntilChanged().collect { page ->
            selectedTab = page
            onTabSelected(page)
        }
    }

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
            FirstToolbarWithHeader(alphaModifier, selectedTab.absoluteValue, {
                if (combinedHeightToolbarAndHeader.value == 0) {
                    combinedHeightToolbarAndHeader.value = it
                }
            }, { onTabSelectedIndex ->
                coroutineScope.launch {
                    pagerState.scrollToPage(onTabSelectedIndex)
                    selectedTab = onTabSelectedIndex
                }
            })
            UnsplashViewPager(pagerState)
        }
    }
}

@Composable
fun FirstToolbarWithHeader(
    modifier: Modifier,
    selectedTab: Int,
    onHeightChangeCallback: (Int) -> Unit,
    onTabSelectedIndex: (Int) -> Unit
) {
    Column(modifier.onGloballyPositioned {
        onHeightChangeCallback(it.size.height)
    }) {
        HomeToolbar(modifier)
        Header(modifier, onTabSelectedIndex, selectedTab)
    }
}

@Composable
fun Header(modifier: Modifier, onTabSelected: (Int) -> Unit, selectedTab: Int) {
    Column(
        modifier = modifier
            .padding(top = 0.dp, start = 18.dp)
            .height(165.dp)
    ) {
        Text(
            "UnSplash",
            style = com.core.compose.theme.typography.h1,
            color = MaterialTheme.colors.onBackground
        )
        Text(
            modifier = Modifier.padding(end = 73.dp),
            text =
            "Beautiful, free photos.\n" +
                    "Gifted by the world’s most generous community of photographers.",
            style = com.core.compose.theme.typography.caption,
            color = MaterialTheme.colors.onSurface
        )
        TabViewSmallTextContainer(onTabSelected, selectedTab)
    }
}

@Composable
fun TabViewSmallTextContainer(onTabSelected: (Int) -> Unit, selectedTab: Int) {
    val lazyDataItems = DummyDataProvider().getTabViewItems()

    LazyRow(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(end = 17.dp, bottom = 12.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom,
        content = {
            items(lazyDataItems.size, itemContent = { index ->
                val textToDisplay = lazyDataItems[index]
                TabViewSmallText(text = textToDisplay, selectedTab.absoluteValue == index) {
                    onTabSelected(index)
                }
                Spacer(modifier = Modifier.width(20.dp))
            })
        })
}

@Composable
fun TabViewSmallText(text: String, selected: Boolean, onClick: () -> Unit) {
    var dividerWidth by remember { mutableStateOf(0) }

    val selectedColor = MaterialTheme.colors.onBackground
    val unSelectedColor = MaterialTheme.colors.onSurface
    Column(modifier = Modifier.onNoRippleClick { onClick() }) {
        if (selected) {
            if (dividerWidth > 0) {
                Divider(
                    color = selectedColor,
                    modifier = Modifier.width(dividerWidth.toFloat().toDp().dp)
                )
            }
            Text(
                text = text,
                style = com.core.compose.theme.typography.body2,
                fontWeight = FontWeight.Bold,
                color = selectedColor,
                modifier = Modifier.padding(top = 11.dp),
                onTextLayout = {
                    dividerWidth = it.size.width
                })
        } else {
            Text(
                text = text,
                style = com.core.compose.theme.typography.body2,
                color = unSelectedColor
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

@OptIn(ExperimentalPagerApi::class)
@Composable
fun UnsplashViewPager(pagerState: PagerState) {
    HorizontalPager(count = 8, state = pagerState, verticalAlignment = Alignment.Top) { page ->
        if (page == 0) {
            var forceRecompose by remember {
                mutableStateOf(0)
            }
            ServerLogger.d("Unsplash", "Render PhotosDisplayList")
            PhotosDisplayList(
                photosFlow = viewModel(
                    modelClass = SearchViewModel::class.java
                ).photosFlow, forceRecompose
            ) {
                forceRecompose += 1
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
    val state by viewModel.followersFlow.collectAsState(initial = FollowUiEntityInitial)
    RenderFollowersUi(state)
}

@Composable
fun RenderFollowersUi(state: FollowUiEntity) {
    when (state) {
        is FollowUiEntityInitial -> FollowInitialUi("Hang on we are getting your followings")
        is FollowUiEntityNotLoggedIn -> FollowUiEntityNotLoggedInUi()
        is FollowUiEntitySuccess -> FollowUiEntitySuccessUi(state.data)
        is FollowUiEntityError -> FollowUiEntityErrorUi()
    }
}

@Composable
fun FollowUiEntitySuccessUi(dataList: List<FollowListItemEntity>) {
    LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.fillMaxHeight(), content = {
        items(dataList.size, key = {
            dataList[it].name
        }, itemContent = {
            Column(
                modifier = Modifier
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                with(dataList[it]) {
                    SubcomposeAsyncImage(
                        model = url,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(64.dp)
                            .width(64.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = name, textAlign = TextAlign.Center, maxLines = 1)
                }
            }

        })
    })
}

@Composable
fun FollowUiEntityErrorUi() {
    FollowInitialUi("We are facing some issues to get your followings. Please try after some time")
}

@Composable
fun FollowUiEntityNotLoggedInUi() {
    var screenHeight = 0f
    LocalConfiguration.current.apply {
        screenHeight = screenHeightDp.toFloat()
    }
    Box(
        modifier = Modifier
            .height(screenHeight.dp)
    ) {
        PleaseLogin()
    }
}

@Composable
fun FollowInitialUi(text: String) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center, content = {
            item {
                Text(
                    text = text,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center
                )
            }
        })
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
        Text(
            text = "Please Login to see your following contents",
            color = MaterialTheme.colors.onBackground
        )
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
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
        ),
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = text)
        }
    }
}