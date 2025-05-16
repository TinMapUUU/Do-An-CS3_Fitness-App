package com.doancs3_new.all_UI.Dashboard

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.doancs3_new.Data.Logic.ChangeCheckpointLine
import com.doancs3_new.Data.Model.Workout
import com.doancs3_new.R
import com.doancs3_new.Viewmodel.ProgressLogViewModel
import com.doancs3_new.Viewmodel.SharedViewModel
import com.doancs3_new.Viewmodel.WorkoutsViewModel
import com.doancs3_new.ui.theme.SteelBlue
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.github.mikephil.charting.data.Entry

@Composable
fun Home(
    viewModel: ProgressLogViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel,
    workoutViewModel: WorkoutsViewModel = hiltViewModel(),
    navController: NavHostController,
    modifier: Modifier = Modifier // ✅ thêm dòng này
) {
    var nickname by remember { mutableStateOf("") }
    var currentWeight by remember { mutableStateOf<Double?>(null) }
    var targetWeight by remember { mutableStateOf<Double?>(null) }
    var currentHeight by remember { mutableStateOf<Double?>(null) }
    var currentBMI by remember { mutableStateOf<Double?>(null) }
    var targetBMI by remember { mutableStateOf<Double?>(null) }

    val selectedAim by sharedViewModel.selectedAim.collectAsState()
    // Lấy dữ liệu từ Firestore khi người dùng đã đăng nhập
    val currentUser = FirebaseAuth.getInstance().currentUser
    val uid = currentUser?.uid ?: ""

//    // Lấy aim từ Firestore
//    val fetchedAim by remember { mutableStateOf<String?>(null) }

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

        if (uid.isNotBlank() && selectedAim == null) {
            fetchUserAim { fetchedAim ->
                fetchedAim?.let {
                    sharedViewModel.setSelectedAim(it) // Cập nhật vào ViewModel
                    Log.d("Home", "Aim được khôi phục từ Firestore: $it")
                }
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

    // Cập nhật progress logs
    LaunchedEffect(progressLogs) {
        if (uid.isNotBlank()) {
            val docRef = FirebaseFirestore.getInstance().collection("users").document(uid)
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        currentWeight = document.getDouble("currentWeight")
                        targetWeight = document.getDouble("targetWeight")
                    }
                }
        }
    }

    // CHUYỂN DỮ LIỆU THÀNH ENTRIES (LineChartEntry)
    val entries = progressLogs.mapIndexed { index, log ->
        Entry(index.toFloat(), log.weight.toFloat())
    }

    val dateLabels = progressLogs.map { it.date }

    val checkpointUpdater = remember { ChangeCheckpointLine(FirebaseFirestore.getInstance()) }

    Scaffold(
        bottomBar = { }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(20.dp)
                .fillMaxSize()
        ) {
            // HEADER
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
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

            // Lấy bài tập cho mục tiêu đã chọn
            LaunchedEffect(selectedAim) {
                selectedAim?.let {
                    workoutViewModel.loadWorkouts(it)
                }
            }

            // Quan sát và hiển thị các bài tập
            val workouts by workoutViewModel.workouts.collectAsState()
            val plan = remember(selectedAim, workouts) {
                selectedAim?.let { generateWorkoutPlan(it, workouts) } ?: emptyList()
            }

            // Khởi tạo ma trận checked 24x3
            val checkedMatrix = remember { mutableStateListOf<MutableList<Boolean>>() }
            if (checkedMatrix.isEmpty()) {
                repeat(24) { checkedMatrix.add(MutableList(3) { false }) }
            }

            Text("Kế hoạch bài tập",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            // Hiển thị danh sách bài tập
            LazyColumn {
                items(24) { dayIndex ->
                    WorkoutView(
                        dayNumber = dayIndex + 1,
                        workouts = plan.getOrNull(dayIndex) ?: emptyList(),
                        checkedStates = checkedMatrix[dayIndex],
                        onCheckChanged = { index, isChecked ->
                            checkedMatrix[dayIndex][index] = isChecked
                            val checkpointIndex = (dayIndex + 1) / 3
                            val start = checkpointIndex * 3
                            val allChecked = checkedMatrix
                                .subList(start, start + 3)
                                .all { it.all { it } }
                            if (allChecked) {
                                checkpointUpdater.updateCheckpointProgress(uid, checkpointIndex)
                            }
                        },
                        onAllChecked = {
                            checkpointUpdater.updateCheckpointProgress(uid, checkpointIndex = dayIndex)
                        }
                    )
                }
            }
        }
    }
}
//Hàm lấy Aim - user để render bài tập
fun fetchUserAim(onResult: (String?) -> Unit) {
    val uid = FirebaseAuth.getInstance().currentUser?.uid
    val db = FirebaseFirestore.getInstance()
    if (uid != null) {
        db.collection("users").document(uid)
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val aim = document.getString("aim")
                    onResult(aim)
                } else {
                    Log.w("fetchUserAim", "Tài liệu không tồn tại.")
                    onResult(null)
                }
            }
            .addOnFailureListener { exception ->
                Log.e("fetchUserAim", "Lỗi khi lấy aim", exception)
                onResult(null)
            }
    } else {
        Log.e("fetchUserAim", "UID null.")
        onResult(null)
    }
}
//Hàm render bài tập
fun generateWorkoutPlan(aim: String, allWorkouts: List<Workout>, totalDays: Int = 24): List<List<Workout>> {
    val filtered = allWorkouts.filter { it.type.equals(aim, ignoreCase = true) }
    if (filtered.size < 3) return emptyList()
    // Tạo danh sách các bộ 3 bài tập ngẫu nhiên (ví dụ chỉ tạo 10 bộ)
    val generatedWorkouts = List(4) { dayIndex ->
        val random = java.util.Random((dayIndex + 1).toLong())
        filtered.shuffled(random).take(3)
    }
    // Lặp lại danh sách generatedWorkouts để đủ totalDays
    return List(totalDays) { dayIndex ->
        generatedWorkouts[dayIndex % generatedWorkouts.size]
    }
}
//Thanh bar
@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        BottomNavItem(
            "Trang Chủ",
            iconPainter = painterResource(id = R.drawable.house_svg),
            route = "Home"
        ),
        BottomNavItem(
            "Hồ Sơ",
            iconPainter = painterResource(id = R.drawable.person_svg),
            route = "Profile"
        ),
        BottomNavItem(
            "Bài Tập",
            iconPainter = painterResource(id = R.drawable.listbook_svg),
            route = "ListWorkout"
        )
    )
// SET UP BAR
    BottomNavigation(
        backgroundColor = backgroundColor,
        modifier = modifier
    ) {
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = @androidx.compose.runtime.Composable {
                    item.iconPainter?.let {
                        Icon(painter = it, contentDescription = item.title)
                    } ?: item.iconVector?.let {
                        Icon(imageVector = it, contentDescription = item.title)
                    }
                },
                label = { Text(item.title) },
                selected = currentDestination == item.route,
                onClick = {
                    if (currentDestination != item.route) {
                        navController.navigate(item.route) {
                            popUpTo("Home") { inclusive = false }
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}
data class BottomNavItem(
    val title: String,
    val iconVector: ImageVector? = null,
    val iconPainter: Painter? = null,
    val route: String
)







