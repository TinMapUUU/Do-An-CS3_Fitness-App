package com.doancs3_new.all_UI.DetailProf

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.doancs3_new.all_UI.RegLogFor.textFieldBorder
import com.doancs3_new.Viewmodel.SharedViewModel
import com.doancs3_new.ui.theme.LightPeriwinkleBlue
import com.doancs3_new.ui.theme.SkyBlue
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.WormIndicatorType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.M)
@Preview(showBackground = true)
@Composable
fun PreviewWeightFuture() {

}

@RequiresApi(Build.VERSION_CODES.M)
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

