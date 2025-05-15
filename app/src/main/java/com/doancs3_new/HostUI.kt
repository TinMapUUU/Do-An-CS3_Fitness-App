package com.doancs3_new

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.doancs3_new.Data.Model.WorkoutSeeder
import com.doancs3_new.Viewmodel.SharedViewModel
import com.doancs3_new.Viewmodel.UserViewModel
import com.doancs3_new.all_UI.Dashboard.BottomNavigationBar
import com.doancs3_new.all_UI.Dashboard.Home
import com.doancs3_new.all_UI.Dashboard.Profile
import com.doancs3_new.all_UI.DetailProf.AllDetailProfile
import com.doancs3_new.all_UI.Onboarding.OnboardingPagerScreen
import com.doancs3_new.all_UI.Onboarding.StartScreen
import com.doancs3_new.all_UI.RegLogFor.ForgetPass
import com.doancs3_new.all_UI.RegLogFor.Login
import com.doancs3_new.all_UI.RegLogFor.Register
import com.doancs3_new.all_UI.RegLogFor.ResetPass
import com.doancs3_new.all_UI.RegLogFor.VerifiCode
import com.doancs3_new.ui.theme.SteelBlue
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var workoutSeeder: WorkoutSeeder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            HostUI(navController)
        }
    }
}

@Composable
fun HostUI(navController: NavHostController) {
    val sharedViewModel: SharedViewModel = hiltViewModel()
    val coroutineScope = rememberCoroutineScope()

    NavHost(
        navController = navController,
        startDestination = "startScreen"
    ) {
        // ONBOARDING + LOGIN + PROFILE DETAIL...
        composable("startScreen") { StartScreen(navController) }
        composable("onboarding") { OnboardingPagerScreen(navController) }
        composable("RLF - Register") { Register(navController) }
        composable("RLF - Login") { Login(navController) }
        composable("RLF - Forget Password") { ForgetPass(navController) }
        composable("RLF - Verification Code") { VerifiCode(navController) }
        composable("RLF - Reset Password") { ResetPass(navController) }
        composable("All Detail Profile") {
            val userViewModel: UserViewModel = hiltViewModel()
            AllDetailProfile(
                navController = navController,
                sharedViewModel = sharedViewModel,
                coroutineScope = coroutineScope,
                userViewModel = userViewModel
            )
        }

        // HOME composable có BottomBar
        composable("Home") {
            Scaffold(
                bottomBar = {
                    Box(
                        Modifier
                            .padding(bottom = 18.dp) // Đẩy thanh lên trên 12dp so với mép dưới
                            .fillMaxWidth()
                    ) {
                        BottomNavigationBar(
                            navController = navController,
                            backgroundColor = SteelBlue
                        )
                    }
                }
            ) { innerPadding ->
                Home(
                    sharedViewModel = sharedViewModel,
                    navController = navController,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

        // PROFILE composable có BottomBar
        composable("Profile") {
            Scaffold(
                bottomBar = {
                    Box(
                        Modifier
                            .padding(bottom = 18.dp) // Đẩy thanh lên trên 12dp so với mép dưới
                            .fillMaxWidth()
                    ) {
                        BottomNavigationBar(
                            navController = navController,
                            backgroundColor = SteelBlue
                        )
                    }
                }
            ) { innerPadding ->
                Profile(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}




     
