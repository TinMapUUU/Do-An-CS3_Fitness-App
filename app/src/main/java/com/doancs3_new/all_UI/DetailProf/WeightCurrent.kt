package com.doancs3_new.all_UI.DetailProf

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.doancs3_new.Viewmodel.SharedViewModel
import kotlinx.coroutines.CoroutineScope

@RequiresApi(Build.VERSION_CODES.M)
@Preview(showBackground = true)
@Composable
fun PreviewWeightCurrent() {

}

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun WeightCurrent(
    navController: NavController,
    pagerState: PagerState,
    sharedViewModel: SharedViewModel,
    coroutineScope: CoroutineScope
) {
    val weight = sharedViewModel.currentWeightInput
    Input_crW_H_futW(
        label = "Cân nặng hiện tại (kg)",
        value = weight,
        onValueChange = { sharedViewModel.updateCurrentWeightInput(it) },
        pagerState = pagerState,
        navController = navController,
        coroutineScope = coroutineScope
    )
}


