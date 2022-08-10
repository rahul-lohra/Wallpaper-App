package com.rahul.notificationstest.feature.search.ui.fragments

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.rahul.notificationstest.R
import com.rahul.notificationstest.ui.theme.MySootheTheme
import java.util.*

class UnsplashHomeFragment: Fragment() {

}

//@Preview
@Composable
fun MySootheApp() {
    Text("Hello world")
//    MySootheTheme() {
//        Scaffold(bottomBar = {  }) { padding ->
////            HomeScreen(Modifier.padding(padding))
//        }
//    }
}

//@Composable
//fun HomeScreen(modifier: Modifier = Modifier) {
//    Column(
//        modifier
//            .verticalScroll(rememberScrollState())
//            .padding(vertical = 16.dp)
//    )
//    {
//        SearchBar(Modifier.padding(horizontal = 16.dp))
//        HomeSection(text = R.string.align_your_body) {
//            AlignYourBodyRow()
//        }
//        HomeSection(text = R.string.favorite_collections) {
//            FavoriteCollectionsGrid()
//        }
//        Spacer(modifier = Modifier.height(16.dp))
//    }
//}
//
//@Composable
//fun HomeSection(
//    @StringRes text: Int,
//    modifier: Modifier = Modifier,
//    content: @Composable () -> Unit
//) {
//    Column(modifier) {
//        Text(
//            stringResource(id = text).uppercase(Locale.getDefault()),
//            style = MaterialTheme.typography.h2,
//            modifier = Modifier
//                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
//                .padding(horizontal = 16.dp)
//        )
//        content()
//
//    }
//}
//
//// Step: Align your body row - Arrangements
//@Composable
//fun AlignYourBodyRow(
//    modifier: Modifier = Modifier
//) {
////    LazyRow(
////        horizontalArrangement = Arrangement.spacedBy(8.dp),
////        contentPadding = PaddingValues(horizontal = 16.dp)
////    ) {
////        items(alignYourBodyData) { item ->
////            AlignYourBodyElement(drawable = item.drawable, text = item.text)
////        }
////    }
//}
//
//// Step: Favorite collections grid - LazyGrid
//@Composable
//fun FavoriteCollectionsGrid(
//    modifier: Modifier = Modifier
//) {
////    LazyHorizontalGrid(
////        rows = GridCells.Fixed(2),
////        contentPadding = PaddingValues(horizontal = 16.dp),
////        horizontalArrangement = Arrangement.spacedBy(8.dp),
////        verticalArrangement = Arrangement.spacedBy(8.dp),
////        modifier = modifier.height(120.dp)
////    ) {
////        items(favoriteCollectionsData) { item ->
////            FavoriteCollectionCard(
////                drawable = item.drawable,
////                text = item.text,
////                modifier = Modifier.height(56.dp)
////            )
////        }
////    }
//}
//
//// Step: Search bar - Modifiers
//@Composable
//fun SearchBar(
//    modifier: Modifier = Modifier
//) {
//    TextField(
//        value = "",
//        onValueChange = {},
//        leadingIcon = {
//            Icon(
//                imageVector = Icons.Default.Search,
//                contentDescription = null
//            )
//        },
//        colors = TextFieldDefaults.textFieldColors(
//            backgroundColor = MaterialTheme.colors.surface
//        ),
//        placeholder = {
//            Text(stringResource(R.string.placeholder_search))
//        },
//        modifier = modifier
//            .fillMaxWidth()
//            .heightIn(min = 56.dp)
//    )
//}
//
//@Composable
//private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
//    BottomNavigation(
//        modifier = modifier,
//        backgroundColor = MaterialTheme.colors.background,
//    ) {
//        BottomNavigationItem(
//            icon = {
//                Icon(imageVector = Icons.Default.Spa, contentDescription = null)
//            }, selected = true, onClick = {},
//            label = { Text(text = "Home") }
//        )
//        BottomNavigationItem(
//            icon = {
//                Icon(
//                    imageVector = Icons.Default.AccountCircle,
//                    contentDescription = null
//                )
//            },
//            label = {
//                Text("Profile")
//            },
//            selected = false,
//            onClick = {}
//        )
//    }
//}