package com.doancs3_new.all_UI.RegLogFor

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.doancs3_new.R
import com.doancs3_new.ui.theme.Gray1
import com.doancs3_new.ui.theme.LavenderPink
import com.google.firebase.auth.FirebaseAuth

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    val navController = rememberNavController()
    Login(navController)
}

@Composable
fun Login(navController: NavController) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
// Welcome Back
        Spacer(modifier = Modifier.height(16.dp))
        Text("Chào đằng đó,", fontSize = 16.sp, color = Color.Gray)
        Text("Chào mừng trở lại", fontSize = 24.sp, color = Color.Black,
                    style = TextStyle(fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.height(32.dp))

// Email Text Field
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            placeholder = { Text("Nhập Email của bạn") },
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

// Password Text Field
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            placeholder = { Text("Nhập Mật Khẩu của bạn") },
            colors = rememberTextFieldColors(),
            leadingIcon = {
                Icon(
                    painterResource(R.drawable.ic_lock),
                    contentDescription = null,
                    tint = Gray1 // Màu icon
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Gray1, RoundedCornerShape(15.dp)),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(12.dp))

// Forget Password
        TextButton(onClick = {
            navController.navigate("RLF - Forget Password")
        }) {
            Text("Quên mật khẩu ?", fontSize = 16.sp, color = LavenderPink )
        }
        Spacer(modifier = Modifier.height(32.dp))

// Login Button với Gradient
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(gradient(), shape = RoundedCornerShape(28.dp)) // Áp dụng gradient
                .clip(RoundedCornerShape(28.dp)) // Bo góc để khớp với button
        ) {

            //
            val firebaseAuth = FirebaseAuth.getInstance()
            val context = LocalContext.current
            Button(
                onClick = {
                    if (email.isNotBlank() && password.isNotBlank()) {
                        firebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(context, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
                                    navController.navigate("All Detail Profile") // Chuyển tiếp
                                } else {
                                    Toast.makeText(context, "Sai thông tin đăng nhập", Toast.LENGTH_SHORT).show()
                                }
                            }
                    } else {
                        Toast.makeText(context, "Vui lòng nhập đầy đủ Email và Mật khẩu", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxSize(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent), // Để màu nền của Button trong suốt
            ) {
                Text("Đăng nhập", fontSize = 22.sp, color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("hoặc", fontSize = 16.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))

// Social Function Link
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
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
        Spacer(modifier = Modifier.height(28.dp))

// Chuyển đến màn hình Register
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Chưa có tài khoản? ", fontSize = 16.sp, color = Color.Gray)
            TextButton(onClick = {
                navController.navigate("RLF - Register")
            }) {
                Text("Đăng ký", fontSize = 16.sp, color = LavenderPink )
            }
        }
    }
}

