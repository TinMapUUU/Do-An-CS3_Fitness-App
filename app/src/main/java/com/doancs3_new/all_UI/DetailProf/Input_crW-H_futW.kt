package com.doancs3_new.all_UI.DetailProf

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.doancs3_new.all_UI.RegLogFor.textFieldBorder
import com.doancs3_new.ui.theme.LightPeriwinkleBlue
import com.doancs3_new.ui.theme.SkyBlue
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.WormIndicatorType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Input_crW_H_futW(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    pagerState: PagerState,
    navController: NavController,
    coroutineScope: CoroutineScope,
    onDone: (() -> Unit)? = null

) {
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
                text = label,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(40.dp))

            // TextField nhập tên
            TextField(
                value = value,
                onValueChange = onValueChange, // Dữ liệu đã cập nhật ở đây rồi
                label = { Text("Nhập $label") },
                placeholder = { Text("") },
                colors = rememberTextFieldColors(),
                modifier = textFieldBorder()
            )

            Spacer(modifier = Modifier.height(150.dp))
        }

        // DotsIndicator (giữ nguyên)
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

        // Button Tiếp theo
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
                    val valueInt = value.toIntOrNull()// Chuyển đổi giá trị từ String sang Int

                    if (valueInt != null) {
                        coroutineScope.launch {
                            // Cập nhật vào Firestore
                            val uid = FirebaseAuth.getInstance().currentUser?.uid
                            if (uid != null) {
                                val userRef = FirebaseFirestore.getInstance().collection("users").document(uid)

                                // Cập nhật giá trị chiều cao hoặc cân nặng tương ứng
                                val updateField = when (label) {
                                    "Cân nặng" -> mapOf("currentWeight" to valueInt)
                                    "Chiều cao" -> mapOf("currentHeight" to valueInt)
                                    "Cân nặng mong muốn" -> mapOf("targetWeight" to valueInt)
                                    else -> emptyMap()
                                }

                                userRef.set(updateField, SetOptions.merge())
                                    .addOnSuccessListener {
                                        Log.d("Firebase", "$label updated successfully")

                                    }
                                    .addOnFailureListener {
                                        Log.e("Firebase", "Error updating $label", it)
                                    }
                            }

                            // Chuyển trang hoặc gọi onDone
                            if (pagerState.currentPage < pagerState.pageCount - 1) {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage + 1,
                                    animationSpec = tween(durationMillis = 1000)
                                )
                            } else {
                                onDone?.invoke() // Gọi callback khi nhập xong bước cuối
                                navController.navigate("Home") {
                                    popUpTo("All Detail Profile") { inclusive = true }
                                }
                            }

                        }


                    } else {
                        // Nếu không thể chuyển đổi sang Double, thông báo lỗi
                        Toast.makeText(navController.context, "Vui lòng nhập giá trị hợp lệ", Toast.LENGTH_SHORT).show()
                    }
                },
                enabled = value.isNotBlank(),
                modifier = Modifier.fillMaxSize(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    disabledContainerColor = Color.LightGray
                ),
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

