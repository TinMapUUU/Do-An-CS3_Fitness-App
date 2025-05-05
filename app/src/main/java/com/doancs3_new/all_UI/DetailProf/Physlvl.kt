package com.doancs3_new.all_UI.DetailProf

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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

@RequiresApi(Build.VERSION_CODES.M)
@Preview(showBackground = true)
@Composable
fun PreviewPhysLvl() {
    PhysLvl(

    )
}

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun PhysLvl(
//    onNextClick: () -> Unit,
    navController: NavController = rememberNavController(),
    pagerState: PagerState = rememberPagerState(initialPage = 4) { 8 }
) {
    var selectedPhysLevel by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    data class PhysLevelOption(
        val title: String,
        val subtitle: String,
        val iconResId: Int
    )
    // Tạo danh sách các tùy chọn mức độ thể chất
    val physLevelOptions = listOf(
        PhysLevelOption(
            "Mới bắt đầu",
            "Chưa từng tâp luyện hoặc rất ít kinh nghiệm",
            R.drawable.battery_1_svg),
        PhysLevelOption("Trung bình",
            "Luyện tập đều đặn",
            R.drawable.battery_5_svg),
        PhysLevelOption("Nâng cao",
            "Tôi là một người vô thần chuyên nghiệp",
            R.drawable.battery_10_svg)
    )
    // Hiển thị nội dung
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Chọn mức độ thể chất của bạn",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Hiển thị các tùy chọn mức độ thể chất
            physLevelOptions.forEach { physLevelOption ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .clickable { selectedPhysLevel = physLevelOption.title } // Xử lý khi chọn một tùy chọn
                        .border(2.dp, Gray1, RoundedCornerShape(15.dp)),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (selectedPhysLevel == physLevelOption.title) Gray1 else Color.White
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(LightPeriwinkleBlue, shape = RoundedCornerShape(18.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = physLevelOption.iconResId),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))
                         // Hiển thị tiêu đề và mô tả tùy chọn
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = physLevelOption.title,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Text(
                                text = physLevelOption.subtitle,
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }

                        RadioButton(
                            selected = selectedPhysLevel == physLevelOption.title,
                            onClick = { selectedPhysLevel = physLevelOption.title },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = SkyBlue
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(150.dp))
        }
        //
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
        //
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
                        if (pagerState.currentPage < pagerState.pageCount - 1) { // <4 vì đây đang ở page 3
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
