package com.doancs3_new.all_UI.DetailProf

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.doancs3_new.Viewmodel.SharedViewModel

import kotlinx.coroutines.CoroutineScope

@Preview(showBackground = true)
@Composable
fun PreviewHeightCurrent() {

}

@Composable
fun HeightCurrent(
    navController: NavController,
    pagerState: PagerState,
    sharedViewModel: SharedViewModel,
    coroutineScope: CoroutineScope
) {
    val height = sharedViewModel.heightInput
    Input_crW_H_futW(
        label = "Chiều cao (cm)",
        value = height,
        onValueChange = { newValue ->
            sharedViewModel.updateHeightInput(newValue)
        },
        pagerState = pagerState,
        navController = navController,
        coroutineScope = coroutineScope,

    )
}

