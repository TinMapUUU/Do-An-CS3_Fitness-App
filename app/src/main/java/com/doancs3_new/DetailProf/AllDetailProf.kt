package com.doancs3_new.DetailProf

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.doancs3_new.Onboarding.Screen3
import com.doancs3_new.Onboarding.Screen4

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAllDetailProfile() {
    val navController = rememberNavController()
    AllDetailProfile(navController = navController)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AllDetailProfile(navController: NavController) {
    val pagerState = rememberPagerState(initialPage = 0) { 9 } // Đảm bảo số trang là hợp lý
    val coroutineScope = rememberCoroutineScope()

    Column {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { page ->
            when (page) {
                0 -> Gender(navController, pagerState) // Chuyển trang đúng
                1 -> Name(navController, pagerState)
                2 -> Ages(navController, pagerState)
                3 -> Location(navController, pagerState)
                4 -> PhysLvl(navController, pagerState)
                5 -> WeightCurrent(navController, pagerState)
                6 -> HeightCurrent(navController, pagerState)
                7 -> WeightFuture(navController, pagerState)
                8 -> Aim(navController, pagerState)
            }
        }
    }
}
