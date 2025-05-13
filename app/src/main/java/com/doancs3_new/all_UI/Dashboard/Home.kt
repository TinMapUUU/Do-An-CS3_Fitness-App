package com.doancs3_new.all_UI.Dashboard

import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.doancs3_new.R
import com.doancs3_new.Viewmodel.ProgressLogViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.github.mikephil.charting.data.Entry

@Composable
fun Home(
    viewModel: ProgressLogViewModel = hiltViewModel(),
) {
    var nickname by remember { mutableStateOf("") }
    var currentWeight by remember { mutableStateOf<Double?>(null) }
    var targetWeight by remember { mutableStateOf<Double?>(null) }
    var currentHeight by remember { mutableStateOf<Double?>(null) }
    var currentBMI by remember { mutableStateOf<Double?>(null) }
    var targetBMI by remember { mutableStateOf<Double?>(null) }

    // Lấy dữ liệu từ Firestore khi người dùng đã đăng nhập
    val currentUser = FirebaseAuth.getInstance().currentUser
    val uid = currentUser?.uid ?: ""

    LaunchedEffect(uid) {
        if (uid.isNotBlank()) {
            val docRef = FirebaseFirestore.getInstance().collection("users").document(uid)
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        nickname = document.getString("nickname") ?: ""
                        currentWeight = document.getDouble("currentWeight")
                        targetWeight = document.getDouble("targetWeight")
                        currentHeight = document.getDouble("currentHeight")
                        currentBMI = document.getDouble("currentBMI")
                        targetBMI = document.getDouble("targetBMI")
                    }
                }
                .addOnFailureListener {
                    Log.e("Firebase", "Lỗi khi tải dữ liệu người dùng", it)
                }
        }
    }

    // Lấy các logs tiến trình từ ViewModel
    val progressLogs by viewModel.progressLogs.collectAsState(initial = emptyList())

    // Lắng nghe sự thay đổi dữ liệu từ Firestore
    LaunchedEffect(uid) {
        if (uid.isNotBlank()) {
            viewModel.startListeningProgressLogs(uid)
        }
    }

    // CHUYỂN DỮ LIỆU THÀNH ENTRIES (LineChartEntry)
    val entries = progressLogs.mapIndexed { index, log ->
        Entry(index.toFloat(), log.weight.toFloat())
    }

    val dateLabels = progressLogs.map { it.date }

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
                    if (nickname.isNotBlank()) {
                        Text("Chào mừng trở lại,", color = Color.Gray, fontSize = 14.sp)
                        Text(
                            text = nickname,
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

            Button(onClick = {
                currentUser?.uid?.let { uid ->
                    viewModel.startListeningProgressLogs(uid)
                }
            }) {
                Text("Tải biểu đồ")
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFE7EBFF)),
                contentAlignment = Alignment.Center
            ) {
// Khu vực hiển thị line chart
                if (progressLogs.isNotEmpty()) {
                    LineChartView(
                        entries = entries,
                        dateLabels = dateLabels,
                        targetWeight = targetWeight?.toFloat(), // truyền vào đây
                        context = LocalContext.current
                    )
                } else {
                    Text("Chưa có dữ liệu cân nặng.")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Nội dung bài tập", fontSize = 16.sp)


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

}

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


