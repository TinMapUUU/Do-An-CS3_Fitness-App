package com.doancs3_new.all_UI.DetailProf

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.doancs3_new.Viewmodel.SharedViewModel
import kotlinx.coroutines.CoroutineScope

@Preview(showBackground = true)
@Composable
fun PreviewWeightCurrent() {

}

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


