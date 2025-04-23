package com.doancs3_new.RegLogFor

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.doancs3_new.R
import com.doancs3_new.ui.theme.Gray1
import com.doancs3_new.ui.theme.LavenderPink
import com.doancs3_new.ui.theme.LightPeriwinkleBlue
import com.doancs3_new.ui.theme.SoftBlue

@Preview(showBackground = true)
@Composable
fun Register() {
    val navController = rememberNavController()
    Register(navController)
}

// Hàm nhớ gradient để tái sử dụng
@Composable
fun gradient(): Brush {
    return remember {
        Brush.linearGradient(
            colors = listOf(SoftBlue, LightPeriwinkleBlue),
            start = Offset(0f, Float.POSITIVE_INFINITY),
            end = Offset(Float.POSITIVE_INFINITY, 0f)
        )
    }
}

// Hàm design TextField
@Composable
fun rememberTextFieldColors(): TextFieldColors {
    return TextFieldDefaults.colors(
        focusedContainerColor = Color.Transparent, // Không có màu nền
        unfocusedContainerColor = Color.Transparent, // Màu nền khi chưa focus
//        disabledContainerColor = Color.Transparent,
//        errorContainerColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent, // Viền khi chưa focus
        focusedIndicatorColor = LightPeriwinkleBlue, // Viền khi focus
        cursorColor = LightPeriwinkleBlue, // Màu con trỏ
        focusedLabelColor = Gray1,
        unfocusedLabelColor = Gray1
    )
}

// Hàm design Border TextField
@Composable
fun textFieldBorder(): Modifier {
    return Modifier
        .fillMaxWidth()
        .border(2.dp, Gray1, RoundedCornerShape(15.dp))
}

@Composable
fun Register(navController: NavController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var acceptTerms by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Chào đằng đó,", fontSize = 16.sp, color = Color.Gray)
        Text("Tạo một tài khoản mới", fontSize = 24.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("Tên") },
            placeholder = { Text("Nhập Tên của bạn") },
            colors = rememberTextFieldColors(),
            leadingIcon = {
                Icon(
                    Icons.Default.Person,
                    contentDescription = null,
                    tint = Gray1 // Màu icon
                )
            },
            modifier = textFieldBorder() // Chỉnh Border Text Field
        )
        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Họ ") },
            placeholder = { Text("Nhập Họ của bạn") },
            colors = rememberTextFieldColors(),
            leadingIcon = {
                Icon(
                    Icons.Default.Person,
                    contentDescription = null,
                    tint = Gray1 // Màu icon
                )
            },
            modifier = textFieldBorder() // Chỉnh Border Text Field
        )
        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            placeholder = { Text("Nhập Email của bạn") },
            colors = rememberTextFieldColors(),
            leadingIcon = {
                Icon(
                    Icons.Default.Email,
                    contentDescription = null,
                    tint = Gray1
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = textFieldBorder() // Chỉnh Border Text Field
        )
        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Mật khẩu") },
            placeholder = { Text("Nhập Mật Khẩu của bạn") },
            colors = rememberTextFieldColors(),
            leadingIcon = {
                Icon(
                    Icons.Default.Lock,
                    contentDescription = null,
                    tint = Gray1
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = textFieldBorder() // Chỉnh Border Text Field
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = acceptTerms,
                onCheckedChange = { acceptTerms = it }
            )
            Text("Bằng cách tiếp tục, bạn chấp nhận chính sách bảo mật và các điều khoản sử dụng của chúng tôi",
                fontSize = 14.sp, color = Gray1)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Register Button với Gradient
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(gradient(), shape = RoundedCornerShape(28.dp)) // Áp dụng gradient
                .clip(RoundedCornerShape(28.dp)) // Bo góc để khớp với button
        ) {
            Button(
                onClick = { /* Handle login logic */ },
                modifier = Modifier.fillMaxSize(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent), // Để màu nền của Button trong suốt
            ) {
                Text("Đăng ký", fontSize = 22.sp, color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Hoặc", fontSize = 14.sp, color = Color.Gray, modifier = Modifier.align(Alignment.CenterHorizontally))

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            IconButton(onClick = { /* Handle Google login */ }) {
                Image(
                    painterResource(R.drawable.ic_google),
                    contentDescription = "Google Login",
                    modifier = Modifier.size(60.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))


            IconButton(onClick = { /* Handle Facebook login */ }) {
                Image(
                    painterResource(R.drawable.ic_facebook),
                    contentDescription = "Facebook Login",
                    modifier = Modifier.size(60.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text("Bạn đã có tài khoản? ", fontSize = 14.sp, color = Gray1)
            TextButton(
                onClick = { navController.navigate("RLF - Login") }
            ) {
                Text("Đăng nhập", fontSize = 14.sp, color = LavenderPink)
            }
        }
    }
}

