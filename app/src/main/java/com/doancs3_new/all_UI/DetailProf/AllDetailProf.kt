package com.doancs3_new.all_UI.DetailProf

import android.annotation.SuppressLint
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
import com.doancs3_new.Data.Local.Dao.UserDao
import com.doancs3_new.Viewmodel.SharedViewModel
import com.doancs3_new.Viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAllDetailProfile() {

}

@SuppressLint("NewApi")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AllDetailProfile(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    userViewModel: UserViewModel,
    coroutineScope: CoroutineScope,
    ) {
    val pagerState = rememberPagerState(initialPage = 0) { 9 }// Đảm bảo số trang là hợp lý

    Column {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { page ->
            when (page) {
                0 -> Gender(navController, pagerState) // Chuyển trang đúng
                1 -> Name(navController, pagerState, sharedViewModel, userViewModel)
                2 -> Ages(navController, pagerState)
                3 -> Location(navController, pagerState)
                4 -> PhysLvl(navController, pagerState)
                5 -> WeightCurrent(navController, pagerState, sharedViewModel, coroutineScope)
                6 -> HeightCurrent(navController, pagerState, sharedViewModel, coroutineScope)
                7 -> WeightFuture(navController, pagerState, sharedViewModel, coroutineScope)
                8 -> Aim(navController, pagerState, sharedViewModel, userViewModel)
            }
        }
    }
}
