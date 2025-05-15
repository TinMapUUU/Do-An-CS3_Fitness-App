package com.doancs3_new.all_UI.Dashboard

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.doancs3_new.Viewmodel.ProfileViewModel
import com.doancs3_new.ui.theme.Green
import com.doancs3_new.ui.theme.Pink40

@Composable
fun InfoField(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, Green, RoundedCornerShape(15.dp))
            .padding(12.dp)
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            color = Pink40
        )
        Text(
            text = value,
            color = Color.Black
        )
    }
}

@Composable
fun Profile(
    viewModel: ProfileViewModel = hiltViewModel(),
    modifier: Modifier = Modifier // ✅ thêm dòng này
) {
    val uiState by viewModel.userData.collectAsState()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Cột trái
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    InfoField("Email", uiState.email ?: "Không có dữ liệu")
                    InfoField("Họ", uiState.lastName ?: "Không có dữ liệu")
                    InfoField("Tên", uiState.firstName ?: "Không có dữ liệu")
                    InfoField("Nick name", uiState.nickname ?: "Không có dữ liệu")
                    InfoField("Mục tiêu", uiState.aim ?: "Không có dữ liệu")
                }

                // Cột phải
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    InfoField("Cân nặng hiện tại", uiState.currentWeight?.toString() ?: "Không có dữ liệu")
                    InfoField("Cân nặng mong muốn", uiState.targetWeight?.toString() ?: "Không có dữ liệu")
                    InfoField("Chiều cao hiện tại", uiState.currentHeight?.toString() ?: "Không có dữ liệu")
                    InfoField("BMI hiện tại", uiState.currentBMI?.toString() ?: "Không có dữ liệu")
                    InfoField("BMI mục tiêu", uiState.targetBMI?.toString() ?: "Không có dữ liệu")
                }
            }
        }
    }
}




