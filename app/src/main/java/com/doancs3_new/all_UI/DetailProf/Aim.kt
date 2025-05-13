package com.doancs3_new.all_UI.DetailProf

import android.util.Log
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.doancs3_new.Data.Logic.BMICalculator
import com.doancs3_new.Viewmodel.SharedViewModel
import com.doancs3_new.ui.theme.Gray1
import com.doancs3_new.ui.theme.LightPeriwinkleBlue
import com.doancs3_new.ui.theme.SkyBlue
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.WormIndicatorType
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.doancs3_new.Data.Model.User
import com.doancs3_new.Viewmodel.ProgressLogViewModel
import com.doancs3_new.Viewmodel.UserViewModel


import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Preview(showBackground = true)
@Composable
fun PreviewAim() {

}

@Composable
fun Aim(
    navController: NavController = rememberNavController(),
    pagerState: PagerState = rememberPagerState(initialPage = 8) { 9 },
    sharedViewModel: SharedViewModel,
    progressLogViewModel: ProgressLogViewModel = hiltViewModel()
) {
    var selectedAim by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    data class AimOption(
        val title: String,
        val subtitle: String
    )

    val aimOptions = listOf(
        AimOption("Giảm cân", "Tập trung đốt mỡ, giảm cân hiệu quả"),
        AimOption("Cải thiện thành phần cơ thể", "Cải thiện hình thể hiện tại"),
        AimOption("Tăng cơ bắp", "Xây dựng cơ bắp và tăng cân"),
        AimOption("Giữ vóc dáng", "Duy trì sức khỏe và hình thể hiện tại")
    )

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
                text = "Chọn mục tiêu của bạn",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(40.dp))

            aimOptions.forEach { aimOption ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .clickable { selectedAim = aimOption.title }
                        .border(2.dp, Gray1, RoundedCornerShape(15.dp)),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (selectedAim == aimOption.title) Gray1 else Color.White
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = aimOption.title,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Text(
                                text = aimOption.subtitle,
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }

                        RadioButton(
                            selected = selectedAim == aimOption.title,
                            onClick = { selectedAim = aimOption.title },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = SkyBlue
                            )
                        )
                    }
                }
            }

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
            //
            Button(
                onClick = {
                    coroutineScope.launch {
                        val uid = FirebaseAuth.getInstance().currentUser?.uid
                        val selectedAimNotNull = selectedAim

                        if (uid != null && selectedAimNotNull != null) {
                            val db = FirebaseFirestore.getInstance()
                            val userRef = db.collection("users").document(uid)

                            userRef.get()
                                .addOnSuccessListener { document ->
                                    val user = document.toObject(User::class.java)
                                    if (user != null) {
                                        val currentWeight = sharedViewModel.currentWeight ?: 0f
                                        val height = sharedViewModel.currentHeight ?: 0f
                                        val targetWeight = sharedViewModel.targetWeight  ?: 0f

                                        val currentBMI = BMICalculator.calculateBMI(currentWeight.toInt(), height.toInt())
                                        val targetBMI = BMICalculator.calculateBMI(targetWeight.toInt(), height.toInt())

                                        val formatter = SimpleDateFormat("dd/MM - HH:mm", Locale.getDefault())
                                        val formattedDate = formatter.format(Date())

                                        val updateMap = mapOf(
                                            "currentWeight" to currentWeight,
                                            "targetWeight" to targetWeight,
                                            "currentHeight" to height,
                                            "currentBMI" to currentBMI,
                                            "targetBMI" to targetBMI,
                                            "aim" to selectedAimNotNull // cập nhật luôn mục tiêu nếu cần
                                        )

                                        val progressLog = hashMapOf(
                                            "weight" to currentWeight,
                                            "date" to formattedDate, // hoặc sử dụng Timestamp.now()
                                            "uid" to uid
                                        )

                                        progressLogViewModel.addProgressLog(uid, currentWeight.toDouble())

                                        db.collection("progressLogs")
                                            .add(progressLog)
                                            .addOnSuccessListener {
                                                Log.d("Firebase", "Initial progress log created")
                                            }
                                            .addOnFailureListener {
                                                Log.e("Firebase", "Failed to create initial progress log", it)
                                            }


                                        userRef.set(updateMap, SetOptions.merge())
                                            .addOnSuccessListener {
                                                Log.d("Firebase", "User data updated with BMI")
                                                navController.navigate("Home") {
                                                    popUpTo("All Detail Profile") { inclusive = true }
                                                }
                                            }
                                            .addOnFailureListener {
                                                Log.e("Firebase", "Failed to save user data", it)
                                            }
                                    } else {
                                        Log.e("Firebase", "User data not found")
                                    }
                                }
                                .addOnFailureListener {
                                    Log.e("Firebase", "Failed to fetch user data", it)
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

