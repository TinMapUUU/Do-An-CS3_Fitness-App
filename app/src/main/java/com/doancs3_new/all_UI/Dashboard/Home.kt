package com.doancs3_new.all_UI.Dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.doancs3_new.Data.Logic.BMICalculator
import com.doancs3_new.R
import com.doancs3_new.Viewmodel.SharedViewModel

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeSummaryScreenPreview() {
    val sharedViewModel: SharedViewModel = viewModel()
//    HomeSummaryScreen(
//        name = sharedViewModel.name,
//        currentWeight = sharedViewModel.currentWeight,
//        targetWeight = sharedViewModel.targetWeight,
//        heightCm = sharedViewModel.heightCm,
//        durationMonths = 6,
//        workoutLocation = "Fitness Center"
//    )

    // Dữ liệu fake
    HomeSummaryScreen(
        name = "Nam Nguyễn",
        currentWeight = 68,
        targetWeight = 60,
        heightCm = 175,
        durationMonths = 6,
        workoutLocation = "Fitness Center"
    )
}

@Composable
fun HomeSummaryScreen(
    name: String,
    currentWeight: Int,
    targetWeight: Int,
    heightCm: Int,
    durationMonths: Int,
    workoutLocation: String

    navController: NavController = rememberNavController()
) {
    val bmiResult = BMICalculator.analyzeBMI(
        currentWeight = currentWeight,
        targetWeight = targetWeight,
        heightCm = heightCm
    )

    Scaffold(
        bottomBar = {
            BottomNavigationBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(20.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // Để cho phép cuộn

        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = "Chào mừng trở lại,", color = Color.Gray, fontSize = 14.sp)
                    Text(text = name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }

                IconButton(onClick = { }) {
                    Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text("Quá trình", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(10.dp))

            // Mục tiêu + Placeholder biểu đồ
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFE7EBFF)),
                contentAlignment = Alignment.Center
            ) {
                when (bmiResult.goal.lowercase()) {
                    "tăng cân" -> Text("Mục tiêu: Tăng cân", color = Color(0xFF4CAF50), fontSize = 16.sp)
                    "giảm cân" -> Text("Mục tiêu: Giảm cân", color = Color(0xFFF44336), fontSize = 16.sp)
                    "giữ dáng" -> Text("Mục tiêu: Giữ dáng", color = Color(0xFF2196F3), fontSize = 16.sp)
                    else -> Text("Biểu đồ đường sẽ hiển thị tại đây", color = Color.Gray)
                }
            }

            // Thêm thông tin người dùng nếu muốn
//            Spacer(modifier = Modifier.height(20.dp))
//            Text("Thông tin tập luyện", fontWeight = FontWeight.Bold, fontSize = 16.sp)
//            Spacer(modifier = Modifier.height(10.dp))
//            Text("Cân nặng hiện tại: $currentWeight kg")
//            Text("Cân nặng mong muốn: $targetWeight kg")
//            Text("Chiều cao: $heightCm cm")
//            Text("Thời gian tập: $durationMonths tháng")
//            Text("Địa điểm: $workoutLocation")
        }
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            selected = true,
            onClick = { /* TODO: điều hướng */ },
            icon = { Icon(painterResource(R.drawable.house_svg), contentDescription = "Trang chủ") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = true,
            onClick = { /* TODO: điều hướng */ },
            icon = { Icon(painterResource(R.drawable.house_svg), contentDescription = "Trang chủ") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* TODO: điều hướng */ },
            icon = { Icon(painterResource(R.drawable.house_svg), contentDescription = "Biểu đồ") },
            label = { Text("Chart") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* TODO: điều hướng */ },
            icon = { Icon(painterResource(R.drawable.house_svg), contentDescription = "Hồ sơ") },
            label = { Text("Profile") }
        )
    }
}

