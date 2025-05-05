package com.doancs3_new.all_UI.Onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.doancs3_new.ui.theme.*

// Hàm nhớ gradient để tái sử dụng
@Composable
fun rememberGradient(): Brush {
    return remember {
        Brush.linearGradient(
            colors = listOf(SoftBlue, LightPeriwinkleBlue),
            start = Offset(0f, Float.POSITIVE_INFINITY),
            end = Offset(Float.POSITIVE_INFINITY, 0f)
        )
    }
}

// Hàm hiển thị text "FitnestX"
@Composable
fun FitnestXText() {
    val fitnestXText = buildAnnotatedString {
        withStyle(SpanStyle(color = Color.Black, fontSize = 30.sp, fontWeight = FontWeight.Bold)) {
            append("Fitnest")
        }
        withStyle(SpanStyle(color = Color.White, fontSize = 36.sp, fontWeight = FontWeight.Bold)) {
            append("X")
        }
    }

    Text(
        text = fitnestXText,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(vertical = 16.dp)
    )
}

@Composable
fun StartScreen( navController: NavController) {
    val context = LocalContext.current
    val gradientColor = rememberGradient()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Gọi hàm FitnestX
            FitnestXText()

            Text(
                text = "Mọi người đều có thể đào tạo",
                fontSize = 23.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }

        // Nút Get Started
        Button(
            onClick = {
                navController.navigate("onboarding") // Chuyển sang Screen1
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 35.dp)
                .width(360.dp)
                .height(60.dp)
        ) {
            Text(
                text = "Get Started",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(brush = gradientColor)
            )
        }
    }
}
