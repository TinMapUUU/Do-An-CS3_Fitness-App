package com.doancs3_new.all_UI.Onboarding

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.doancs3_new.R
import com.doancs3_new.ui.theme.Gray1
import com.doancs3_new.ui.theme.LightPeriwinkleBlue
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.WormIndicatorType
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.doancs3_new.ui.theme.SkyBlue

@Preview(showBackground = true)
@Composable
fun PreviewScreen1() {
    val navController = rememberNavController()
    val pagerState = rememberPagerState(initialPage = 0) { 4 }
    Screen1(navController = navController, pagerState = pagerState)
}
@Composable
fun Screen1(navController: NavController, pagerState: PagerState) {
    val coroutineScope = rememberCoroutineScope()

    // Box tổng chứa tất cả các thành phần : ảnh + text + button
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Box chứa ảnh
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(390.dp)
                .clipToBounds()
        ) {
            Image(
                painter = painterResource(id = R.drawable.screen1),
                contentDescription = "Screen 1 Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(440.dp)
            )
        }

        // Column chứa nội dung text
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 35.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(440.dp))

            Text(
                text = "Theo dõi mục tiêu",
                fontSize = 25.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Đừng lo lắng nếu bạn gặp khó khăn trong việc xác định mục tiêu của mình. " +
                        "Chúng tôi có thể giúp bạn xác định mục tiêu của mình và theo dõi mục tiêu của bạn",
                fontSize = 15.sp,
                color = Gray1
            )
        }

        // Nút chuyển screen
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.BottomCenter)
                .padding(bottom = 45.dp, start = 35.dp, end = 35.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        if (pagerState.currentPage < 3) {
                            pagerState.animateScrollToPage(
                                page = pagerState.currentPage + 1,
                                animationSpec = tween(durationMillis = 1000) // CHẬM HƠN
                            )
                        } else {
                            // Nếu hết trang rồi
                            navController.navigate("RLF - Login") {
                                popUpTo("onboarding") { inclusive = true }
                            }
                        }
                    }
                },

                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                colors = ButtonDefaults.buttonColors(containerColor = LightPeriwinkleBlue)
            ) {
                Text(text = ">", fontSize = 20.sp, color = Color.White)
            }
        }

        // --- Dots Indicator nằm đáy chính giữa ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp),
            contentAlignment = Alignment.Center
        ) {
            DotsIndicator(
                dotCount = 4,
                pagerState = pagerState,
                type = WormIndicatorType(
                    dotsGraphic = DotGraphic(
                        size = 16.dp,
                        borderWidth = 2.dp,
                        borderColor = LightPeriwinkleBlue,
                        color = Color.Transparent
                    ),
                    wormDotGraphic = DotGraphic(
                        size = 16.dp,
                        color = SkyBlue
                    )
                )
            )
        }
    }
}
