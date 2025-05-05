package com.doancs3_new.all_UI.Onboarding

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.doancs3_new.R
import com.doancs3_new.ui.theme.Gray1
import com.doancs3_new.ui.theme.LightPeriwinkleBlue
import com.doancs3_new.ui.theme.SkyBlue
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.WormIndicatorType
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun PreviewScreen2() {
    val navController = rememberNavController()
    val pagerState = rememberPagerState(initialPage = 1) { 4 }
    Screen2(navController = navController, pagerState = pagerState)
}
@Composable
fun Screen2(navController: NavController, pagerState: PagerState) {
    val coroutineScope = rememberCoroutineScope()
    Box( // Box tổng chứa tất cả các thành phần : ảnh + text + button
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Box chứa ảnh (nằm trên cùng)
        Box(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Image(
                painter = painterResource(id = R.drawable.screen2),
                contentDescription = "Screen 2 Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(440.dp) // Ảnh cao hơn Box để phần trên bị cắt
                    .align(Alignment.TopCenter)
                    .offset(y = (-40).dp) // Dịch ảnh lên trên 50dp để cắt phần trên
            )
        }

        // Column chứa nội dung text
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 35.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(440.dp)) // Đẩy nội dung xuống dưới

            Text(
                text = "Đốt cháy",
                fontSize = 25.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Hãy tiếp tục đốt cháy, để đạt được các mục tiêu của bạn, nó chỉ bị đau tạm thời, " +
                        "Nếu bạn từ bỏ bây giờ, bạn sẽ bị đau mãi mãi",
                fontSize = 15.sp,
                color = Gray1
            )
        }

        // Box chứa button (nằm dưới cùng)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.BottomCenter) // Đảm bảo Box này nằm ở đáy màn hình
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

