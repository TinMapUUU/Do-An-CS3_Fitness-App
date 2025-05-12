package com.doancs3_new.all_UI.DetailProf

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.doancs3_new.Viewmodel.SharedViewModel
import kotlinx.coroutines.CoroutineScope

@Preview(showBackground = true)
@Composable
fun PreviewWeightFuture() {

}

@Composable
fun WeightFuture(
    navController: NavController,
    pagerState: PagerState,
    sharedViewModel: SharedViewModel,
    coroutineScope: CoroutineScope
) {
    val target = sharedViewModel.targetWeightInput
    Input_crW_H_futW(
        label = "Cân nặng mong muốn (kg)",
        value = target,
        onValueChange = { sharedViewModel.updateTargetWeightInput(it) },
        pagerState = pagerState,
        navController = navController,
        coroutineScope = coroutineScope
    )
}

