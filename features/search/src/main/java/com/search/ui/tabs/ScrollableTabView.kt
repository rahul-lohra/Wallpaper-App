@file:OptIn(ExperimentalPagerApi::class)

package com.search.ui.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.search.R
import com.search.data.datasource.DummyDataProvider
import com.search.ui.NoRippleTheme
import com.utils.onNoRippleClick
import com.utils.toDp
import kotlinx.coroutines.launch

@Composable
fun ScrollableTabLayout(selectIndex: Int) {
    val lazyDataItems = DummyDataProvider().getTabViewItems()

    LazyRow(
        modifier = Modifier.height(44.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        content = {
            items(
                lazyDataItems.size,
                itemContent = { index ->
                    val textToDisplay = lazyDataItems[index]
                    Row() {
                        ScrollableTabView(
                            text = textToDisplay,
                            index == selectIndex
                        )
                        if (index == 1) {
                            Spacer(modifier = Modifier.width(16.dp))
                            Divider(
                                modifier = Modifier
                                    .height(35.dp)
                                    .width(1.dp)
                                    .padding(top = 2.dp),
                                color = colorResource(id = R.color.search_grey_3)
                            )
                        }
                    }
                })
        })
}

@Composable
fun ScrollableTabLayout2(pagerState: PagerState) {
    val lazyDataItems = DummyDataProvider().getTabViewItems()
    val coroutineScope = rememberCoroutineScope()
    Box(modifier = Modifier.height(44.dp), contentAlignment = Alignment.BottomCenter) {
        ScrollableTabRow(selectedTabIndex = pagerState.currentPage, backgroundColor = MaterialTheme.colors.background,indicator = {},
            divider = {}) {
            lazyDataItems.forEachIndexed { index, item ->
                CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
                    val selected = pagerState.currentPage == index
                    Tab(modifier = Modifier.padding(horizontal = 1.dp), selected = pagerState.currentPage == index, onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(index)
                        }
                    }) {
                        Row() {
                            ScrollableTabView(
                                text = item,
                                selected
                            )
                            if (index == 1) {
                                Spacer(modifier = Modifier.width(16.dp))
                                Divider(
                                    modifier = Modifier
                                        .height(35.dp)
                                        .width(1.dp)
                                        .padding(top = 2.dp),
                                    color = colorResource(id = R.color.search_grey_3)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
//    LazyRow(
//        modifier = Modifier.height(44.dp),
//        horizontalArrangement = Arrangement.spacedBy(16.dp),
//        contentPadding = PaddingValues(horizontal = 16.dp),
//        content = {
//            items(
//                lazyDataItems.size,
//                itemContent = { index ->
//                    val textToDisplay = lazyDataItems[index]
//                    Row() {
//                        ScrollableTabView(
//                            text = textToDisplay,
//                            index == selectIndex
//                        )
//                        if (index == 1) {
//                            Spacer(modifier = Modifier.width(16.dp))
//                            Divider(
//                                modifier = Modifier
//                                    .height(35.dp)
//                                    .width(1.dp)
//                                    .padding(top = 2.dp),
//                                color = colorResource(id = R.color.search_grey_3)
//                            )
//                        }
//                    }
//                })
//        })
}


@Composable
fun ScrollableTabView(text: String, selected: Boolean) {
    TabViewText(text, selected)
}

@Composable
fun TabViewText(text: String, selected: Boolean) {
    var dividerWidth by remember { mutableStateOf(0) }

    Column(modifier = Modifier
        .fillMaxHeight()) {
        val tvModifier = Modifier.padding(top = 11.dp)

        if (selected) {
            Text(
                text = text,
                style = typography.body2,
                fontWeight = FontWeight.Bold,
                modifier = tvModifier,
                onTextLayout = {
                    dividerWidth = it.size.width
                })
            if (dividerWidth > 0) {

                Divider(
                    color = colorResource(id = android.R.color.black),
                    modifier = Modifier
                        .width(
                            dividerWidth
                                .toFloat()
                                .toDp().dp
                        )
                        .fillMaxHeight()
                        .padding(top = 14.dp)
                )
            }
        } else {
            Text(
                text = text,
                color = colorResource(id = R.color.search_grey_4),
                style = typography.body2,
                modifier = tvModifier
            )
        }
    }
}

@Composable
fun SearchView(modifier: Modifier) {

    Row(
        modifier = modifier
            .height(36.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(colorResource(id = R.color.search_grey_1)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = Icons.Default.Search,
            colorFilter = ColorFilter.tint(colorResource(id = R.color.search_grey_2)),
            contentDescription = "Search",
            modifier = Modifier.padding(start = 9.dp)
        )

        BasicTextField(
            value = "Search",
            onValueChange = {},
            textStyle = typography.body1.copy(color = colorResource(id = R.color.search_grey_2)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 9.dp)

        )
    }
}
