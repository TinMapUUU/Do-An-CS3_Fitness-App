package com.doancs3_new.RegLogFor

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.doancs3_new.ui.theme.Gray1
import com.doancs3_new.ui.theme.LavenderPink

@Preview(showBackground = true)
@Composable
fun PreviewSendCode() {
    val navController = rememberNavController()
    VerifiCode(navController)
}

@Composable
fun VerifiCode(navController: NavController) {
    var otpCode by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Nhập mã xác nhận
        Text("Nhập mã xác minh", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        // Thông báo về mã OTP
        Text("Vui lòng nhập mã được gửi đến email của bạn", fontSize = 16.sp, color = Gray1)
        Spacer(modifier = Modifier.height(24.dp))

        // Mã OTP
        TextField(
            value = otpCode,
            onValueChange = { otpCode = it },
            label = { Text("Mã OTP") },
            singleLine = true,
            colors = rememberTextFieldColors(),
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Gray1, RoundedCornerShape(15.dp)),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Confirm Button with Gradient
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(gradient(), shape = RoundedCornerShape(28.dp))
                .clip(RoundedCornerShape(28.dp))
        ) {
            // Button Confirm
            Button(
                onClick = {
                    if (otpCode == "123456") {
                        Toast.makeText(context, "Xác nhận thành công!", Toast.LENGTH_SHORT).show()
                        navController.navigate("RLF - Reset Password")
                    } else {
                        Toast.makeText(context, "Mã OTP không khớp!", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxSize(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent) // Transparent background
            ) {
                Text("Xác nhận", fontSize = 22.sp, color = Color.White)
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Gửi lại mã",
            color = LavenderPink,
            fontSize = 16.sp,
            modifier = Modifier
                .clickable {
                    Toast.makeText(context, "Mã OTP đã được gửi lại", Toast.LENGTH_LONG).show()
                }
                .padding(8.dp)
        )
    }
}
