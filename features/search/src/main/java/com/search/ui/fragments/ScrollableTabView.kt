package com.search.ui.fragments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.search.R
import com.search.data.datasource.DummyDataProvider
import com.utils.onNoRippleClick
import com.utils.toDp

@Preview
@Composable
fun ScrollableTabLayout() {
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
                    if (textToDisplay == "-") {
                        Divider(
                            modifier = Modifier
                                .height(35.dp)
                                .width(1.dp)
                                .padding(top = 2.dp),
                            color = colorResource(id = R.color.search_grey_3)
                        )
                    } else {
                        ScrollableTabView(
                            text = textToDisplay,
                            index == 0
                        )
                    }

                })
        })
}


@Composable
fun ScrollableTabView(text: String, selected: Boolean) {
    TabViewText(text, selected) {}
}

@Composable
fun TabViewText(text: String, selected: Boolean, onClick: () -> Unit) {
    var dividerWidth by remember { mutableStateOf(0) }

    Column(modifier = Modifier
        .onNoRippleClick { onClick() }
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
