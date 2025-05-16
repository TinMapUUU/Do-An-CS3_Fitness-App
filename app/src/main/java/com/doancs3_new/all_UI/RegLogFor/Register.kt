package com.doancs3_new.all_UI.RegLogFor

import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.doancs3_new.R
import com.doancs3_new.ui.theme.Gray1
import com.doancs3_new.ui.theme.LavenderPink
import com.doancs3_new.ui.theme.LightPeriwinkleBlue
import com.doancs3_new.ui.theme.SoftBlue
import com.google.firebase.auth.FirebaseAuth
import com.doancs3_new.Viewmodel.UserViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


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
fun Register(
    navController: NavController,
    userViewModel: UserViewModel = hiltViewModel() // Đảm bảo import UserViewModel
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
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

// Tên Text Field
        TextField(
            value = firstName,
            onValueChange = {
                firstName = it

            },
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

// Họ Text Field
        TextField(
            value = lastName,
            onValueChange = {
                lastName = it

            },
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

// Email Text Field
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

// Password Text Field
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            placeholder = { Text("Nhập Mật Khẩu của bạn") },
            colors = rememberTextFieldColors(),
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_lock),
                    contentDescription = null,
                    tint = Gray1
                )
            },
            trailingIcon = {
                val image = if (passwordVisible)
                    painterResource(id = R.drawable.pass_appear_svg)
                else
                    painterResource(id = R.drawable.pass_disappear_svg)

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = image,
                        contentDescription = if (passwordVisible) "Ẩn mật khẩu" else "Hiện mật khẩu"
                    )
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Gray1, RoundedCornerShape(15.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

// Checkbox Accept Terms
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
        // Register Button với Gradient
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(gradient(), shape = RoundedCornerShape(28.dp)) // Áp dụng gradient
                .clip(RoundedCornerShape(28.dp)) // Bo góc để khớp với button
        ) {

            val firebaseAuth = FirebaseAuth.getInstance()
            val context = LocalContext.current
            val userViewModel: UserViewModel = hiltViewModel()

            Button(
                onClick = {
                    if (email.isNotBlank() && password.isNotBlank()
                        && firstName.isNotBlank() && lastName.isNotBlank()
                    ) {
                        firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val user = firebaseAuth.currentUser
                                    val uid = user?.uid ?: return@addOnCompleteListener

                                    val currentDate = SimpleDateFormat("dd/MM", Locale.getDefault()).format(
                                        Date()
                                    )

                                    // Lưu thông tin user lên Firestore
                                    val userData = hashMapOf(
                                        "uid" to uid,
                                        "email" to email,
                                        "firstName" to firstName.trim(),
                                        "lastName" to lastName.trim(),
                                        "date" to currentDate // <- Thêm dòng này để lưu thời điểm đăng ký
//
                                    )
                                    FirebaseFirestore.getInstance()
                                        .collection("users")
                                        .document(uid)
                                        .set(userData, SetOptions.merge()) // <- không ghi đè dữ liệu cũ
                                        .addOnSuccessListener {
                                            saveUser(context, email)
                                            Toast.makeText(context, "Đăng ký thành công", Toast.LENGTH_SHORT).show()
                                            navController.navigate("RLF - Login")
                                        }
                                        .addOnFailureListener { e ->
                                            Log.e("Firestore", "Lỗi lưu dữ liệu: ${e.message}")
                                            Toast.makeText(context, "Lỗi lưu dữ liệu: ${e.message}", Toast.LENGTH_SHORT).show()
                                        }
                                } else {
                                    Log.e("FirebaseAuth", "Đăng ký thất bại: ${task.exception?.message}")
                                    Toast.makeText(context, "Lỗi: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                                }
                            }
                    } else {
                        Toast.makeText(context, "Vui lòng nhập đầy đủ Email và Mật khẩu", Toast.LENGTH_SHORT).show()
                    }
                },
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
fun saveUser(context: Context, email: String) {
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    sharedPreferences.edit().putString("user_email", email).apply()
}


