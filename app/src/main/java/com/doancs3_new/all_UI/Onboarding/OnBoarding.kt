package com.doancs3_new.all_UI.Onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

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

