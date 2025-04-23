package com.doancs3_new.DetailProf

import com.doancs3_new.RegLogFor.textFieldBorder
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.doancs3_new.ui.theme.Gray1
import com.doancs3_new.ui.theme.LightPeriwinkleBlue
import com.doancs3_new.ui.theme.SkyBlue
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.WormIndicatorType
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.M)
@Preview(showBackground = true)
@Composable
fun PreviewName() {
    val navController = rememberNavController()
    val pagerState = rememberPagerState(initialPage = 1) { 9}
    Name(navController = navController, pagerState = pagerState)
}

// Hàm design TextField
@Composable
fun rememberTextFieldColors(): TextFieldColors {
    return TextFieldDefaults.colors(
        focusedContainerColor = Color.Transparent, // Không có màu nền
        unfocusedContainerColor = Color.Transparent, // Màu nền khi chưa focus
//        disabledContainerColor = Color.Transparent,
//        errorContainerColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent, // Viền khi chưa focus
        focusedIndicatorColor = LightPeriwinkleBlue, // Viền khi focus
        cursorColor = LightPeriwinkleBlue, // Màu con trỏ
        focusedLabelColor = Gray1,
        unfocusedLabelColor = Gray1
    )
}

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun Name(
    navController: NavController = rememberNavController(),
    pagerState: PagerState = rememberPagerState(initialPage = 1) { 9 }
) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Tên của bạn là gì ?",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(40.dp))

            // TextField nhập tên
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Tên đầy đủ") },
                placeholder = { Text("Nhập tên của bạn") },
                colors = com.doancs3_new.RegLogFor.rememberTextFieldColors(),

                modifier = textFieldBorder() // Chỉnh Border Text Field
            )



            Spacer(modifier = Modifier.height(150.dp))
        }

        // Dots Indicator
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 70.dp),
            contentAlignment = Alignment.Center
        ) {
            DotsIndicator(
                dotCount = pagerState.pageCount,
                pagerState = pagerState,
                type = WormIndicatorType(
                    dotsGraphic = DotGraphic(
                        size = 10.dp,
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

        // Button Next
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.BottomCenter)
                .background(gradient(), shape = RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp))
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        if (pagerState.currentPage < pagerState.pageCount - 1) {
                            pagerState.animateScrollToPage(
                                page = pagerState.currentPage + 1,
                                animationSpec = tween(durationMillis = 1000)
                            )
                        } else {
                            navController.navigate("Home") {
                                popUpTo("All Detail Profile") { inclusive = true }
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxSize(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                shape = RoundedCornerShape(18.dp),
                contentPadding = PaddingValues()
            ) {
                Text(
                    text = "Tiếp theo",
                    fontSize = 22.sp,
                    color = Color.White,
                )
            }
        }
    }
}