package com.doancs3_new.RegLogFor

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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.doancs3_new.R
import com.doancs3_new.ui.theme.Gray1
import com.doancs3_new.ui.theme.LavenderPink

@Preview(showBackground = true)
@Composable
fun PreviewForgetPass() {
    val navController = rememberNavController()
    ForgetPass(navController)
}

@Composable
fun ForgetPass(navController: NavController) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Quên mật khẩu?", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        Text("Nhập email để khôi phục mật khẩu\n",
                fontSize = 16.sp,
                color = Gray1)
        Spacer(modifier = Modifier.height(16.dp))

        // Email Text Field
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            placeholder = { Text("Nhập email của bạn") },
            colors = rememberTextFieldColors(),
            leadingIcon = {
                Icon(
                    painterResource(R.drawable.ic_email),
                    contentDescription = null,
                    tint = Gray1
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Gray1, RoundedCornerShape(15.dp)),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email,)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Send code Button với Gradient
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(gradient(), shape = RoundedCornerShape(28.dp)) // Áp dụng gradient
                .clip(RoundedCornerShape(28.dp)) // Bo góc để khớp với button
        ) {
            // Button Send code
            Button(
                onClick = { navController.navigate("RLF - Verification Code") },
                modifier = Modifier.fillMaxSize(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent), // Để màu nền của Button trong suốt
            ) {
                Text("Gửi mã", fontSize = 22.sp, color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Return Login
        TextButton(onClick = {
            navController.navigate("RLF - Login")
        }) {
            Text("Quay về Đăng Nhập", fontSize = 16.sp, color = LavenderPink )
        }
    }
}
