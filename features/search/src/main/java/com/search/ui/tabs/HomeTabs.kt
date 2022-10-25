import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.core.compose.theme.typography
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.logger.ServerLogger
import com.search.data.datasource.DummyDataProvider
import com.search.ui.NoRippleTheme
import com.utils.toDp
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun TabViewSmallTextContainer(onTabSelected: (Int) -> Unit, selectedTab: Int) {
    val lazyDataItems = DummyDataProvider().getTabViewItems()
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LazyRow(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(end = 17.dp, bottom = 12.dp),
        state = listState,
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom,
        content = {
            items(lazyDataItems.size, itemContent = { index ->
                val textToDisplay = lazyDataItems[index]
                val selected = selectedTab.absoluteValue == index
                TabViewSmallText(text = textToDisplay, selected, {
                    onTabSelected(index)
                }, { performScroll ->
                    coroutineScope.launch {
                        listState.scrollToItem(index)
                    }


                })
                Spacer(modifier = Modifier.width(20.dp))
            })
        })
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabViewSmallTextContainer2(pagerState: PagerState) {
    val lazyDataItems = DummyDataProvider().getTabViewItems()
    val coroutineScope = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.BottomCenter) {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = MaterialTheme.colors.background,
            indicator = {},
            divider = {}) {
            lazyDataItems.forEachIndexed { index, item ->
                CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
                    val selected = pagerState.currentPage == index
                    Tab( modifier = Modifier.padding(horizontal = 1.dp), selected = pagerState.currentPage == index, onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(index)
                        }
                    }) {
                        TabViewSmallText2(text = item, selected)
                    }
                }
            }
        }
    }
}

@Composable
fun TabViewSmallText2(
    text: String,
    selected: Boolean,
) {
    var dividerWidth by remember { mutableStateOf(0) }

    val selectedColor = MaterialTheme.colors.onBackground
    val unSelectedColor = MaterialTheme.colors.onSurface
    val textColor = if (selected) selectedColor else unSelectedColor
    val dividerColor = if (selected) selectedColor else Color.Transparent

    Column {
        Divider(
            color = dividerColor,
            modifier = Modifier.width(dividerWidth.toFloat().toDp().dp)
        )
        Text(
            text = text,
            style = typography.body2,
            fontWeight = FontWeight.Bold,
            color = textColor,
            modifier = Modifier.padding(top = 11.dp, bottom = 11.dp),
            onTextLayout = {
                dividerWidth = it.size.width
            })
    }
}

@Composable
fun TabViewSmallText(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    performScrollToDisplayTab: (Float) -> Unit
) {
    var dividerWidth by remember { mutableStateOf(0) }

    val selectedColor = MaterialTheme.colors.onBackground
    val unSelectedColor = MaterialTheme.colors.onSurface
//    var attachGlobalPosition by remember(text) { mutableStateOf(selected) }

    val modifier = if (selected) {
        Modifier
//            .onNoRippleClick { onClick() }
            .onGloballyPositioned {
                //TODO Rahul
                val parentWidth = it.parentCoordinates?.size?.width ?: 0
                val parentX = it.parentCoordinates?.positionInParent()?.x ?: 0f
                val parentRight = parentX + parentWidth
                val childLeft = it.positionInParent().x
                val childRight = childLeft + (it.size.width)
                if (childRight > parentRight) {
                    //Perform shift
                    performScrollToDisplayTab(childRight - parentRight)
                    ServerLogger.d(
                        "TabViewSmallText",
                        "perform left shift, text=$text, shift=${childRight - parentRight}"
                    )
//                    attachGlobalPosition.apply {
//                        attachGlobalPosition = false
//                    }
                } else if (childLeft < 0) {
                    performScrollToDisplayTab(childLeft)
                    ServerLogger.d(
                        "TabViewSmallText",
                        "perform right shift, text=$text, shift=$childLeft"
                    )
                }
            }
    } else {
        Modifier
    }
    Column(modifier = modifier) {
        if (selected) {
            if (dividerWidth > 0) {
                Divider(
                    color = selectedColor,
                    modifier = Modifier.width(dividerWidth.toFloat().toDp().dp)
                )
            }
            Text(
                text = text,
                style = typography.body2,
                fontWeight = FontWeight.Bold,
                color = selectedColor,
                modifier = Modifier.padding(top = 11.dp),
                onTextLayout = {
                    dividerWidth = it.size.width
                })
        } else {
            Text(
                text = text,
                style = typography.body2,
                color = unSelectedColor
            )
        }
    }
}