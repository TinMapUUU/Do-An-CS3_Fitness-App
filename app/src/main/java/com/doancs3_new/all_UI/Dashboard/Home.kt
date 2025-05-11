package com.doancs3_new.all_UI.Dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.doancs3_new.Data.Logic.BMICalculator
import com.doancs3_new.R
import com.doancs3_new.Viewmodel.SharedViewModel

@Composable
fun Home(
    viewModel: SharedViewModel
) {
    val name = viewModel.name
    val currentWeight = viewModel.currentWeight
    val targetWeight = viewModel.targetWeight
    val heightCm = viewModel.heightCm

    val bmiResult = if (
        currentWeight != null && targetWeight != null && heightCm != null
    ) BMICalculator.analyzeBMI(currentWeight, targetWeight, heightCm) else null

    Scaffold(
        bottomBar = { BottomNavigationBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(20.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // HEADER
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    if (name.isNotBlank()) {
                        Text("Chào mừng trở lại,", color = Color.Gray, fontSize = 14.sp)
                        Text(
                            text = name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    } else {
                        Text(
                            "Chào mừng đến với ứng dụng!",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.Gray
                        )
                    }
                }
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Notifications, contentDescription = "Thông báo")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // MỤC TIÊU
            Text("Quá trình", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFE7EBFF)),
                contentAlignment = Alignment.Center
            ) {
                if (bmiResult != null) {
                    val color = when (bmiResult.goal.lowercase()) {
                        "tăng cân" -> Color(0xFF4CAF50)
                        "giảm cân" -> Color(0xFFF44336)
                        "giữ dáng" -> Color(0xFF2196F3)
                        else -> Color.Gray
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Mục tiêu: ${bmiResult.goal}",
                            color = color,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "BMI hiện tại: %.2f".format(bmiResult.currentBMI),
                            fontSize = 16.sp,
                            color = Color.DarkGray
                        )
                        Text(
                            text = "BMI mục tiêu: %.2f".format(bmiResult.targetBMI),
                            fontSize = 16.sp,
                            color = Color.DarkGray
                        )
                        Text(
                            text = "Tình trạng: ${bmiResult.description}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        )
                    }
                } else {
//                    Text(text = "Đây là phần của line chart", fontSize = 16.sp)
                    Image(
                        painter = painterResource(id = R.drawable.chart_line),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(440.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Nội dung bài tập", fontSize = 16.sp)

            //fake UI workout
            SimpleExerciseDayUI()
            SimpleExerciseDayUI()
            SimpleExerciseDayUI()

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
            onClick = { },
            icon = { Icon(painterResource(R.drawable.house_svg), contentDescription = "Trang chủ") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(painterResource(R.drawable.house_svg), contentDescription = "Lịch") },
            label = { Text("Lịch ") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(painterResource(R.drawable.house_svg), contentDescription = "Hồ sơ") },
            label = { Text("Hồ sơ") }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeSummaryScreenPreview() {
    val viewModel: SharedViewModel = viewModel()
    Home(viewModel = viewModel)
}

// fake UI
// Dữ liệu tạm
//data class Task(
//    val id: Int,
//    val name: String,
//    val description: String,
//    var isChecked: Boolean = false
//)

@Composable
fun SimpleExerciseDayUI() {
    val taskList = remember {
        mutableStateListOf(
            mutableStateOf(Triple("Hít đất", "Thực hiện 3 hiệp, mỗi hiệp 15 cái. Nghỉ 1 phút.", false)),
            mutableStateOf(Triple("Gập bụng", "Gập 4 hiệp, mỗi hiệp 20 cái. Giữ nhịp thở đều.", false)),
            mutableStateOf(Triple("Plank", "Giữ plank 1 phút, lặp lại 3 lần.", false))
        )
    }

    var expandedIndex by remember { mutableStateOf(-1) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .background(Color(0xFFE8F5E9), RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text("Ngày 1", fontSize = 18.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(8.dp))

        taskList.forEachIndexed { index, taskState ->
            val (title, description, checked) = taskState.value
            val isExpanded = expandedIndex == index

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painterResource(R.drawable.gym_svg),
                    contentDescription = "",
                    modifier = Modifier.size(30.dp)
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp)
                ) {
                    Text(title, fontWeight = FontWeight.SemiBold)

                    val desc = if (isExpanded || description.length <= 40)
                        description
                    else
                        description.take(40) + "..."

                    Text(
                        text = desc,
                        fontSize = 13.sp,
                        color = Color.Gray,
                        modifier = Modifier.clickable {
                            expandedIndex = if (isExpanded) -1 else index
                        }
                    )
                }

                Checkbox(
                    checked = checked,
                    onCheckedChange = {
                        taskList[index].value = taskState.value.copy(third = it)
                    }
                )
            }

            Divider()
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
}


