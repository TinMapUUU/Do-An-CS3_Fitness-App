package com.doancs3_new.Onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.WormIndicatorType
import kotlinx.coroutines.launch

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewOnboardingPager() {
    val navController = rememberNavController()
    OnboardingPagerScreen(navController = navController)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingPagerScreen(navController: NavController) {
    val pagerState = rememberPagerState(initialPage = 0) { 4 } // <- truyền số lượng trang ở đây
    val coroutineScope = rememberCoroutineScope()

    Column {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { page ->
            when (page) {
                0 -> Screen1(navController, pagerState)
                1 -> Screen2(navController, pagerState)
                2 -> Screen3(navController, pagerState)
                3 -> Screen4(navController, pagerState)
            }
        }
    }
}

