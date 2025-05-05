package com.doancs3_new

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.doancs3_new.Viewmodel.SharedViewModel
import com.doancs3_new.all_UI.Dashboard.HomeSummaryScreen
import com.doancs3_new.all_UI.DetailProf.AllDetailProfile
import com.doancs3_new.all_UI.Onboarding.OnboardingPagerScreen

import com.doancs3_new.all_UI.Onboarding.StartScreen
import com.doancs3_new.all_UI.RegLogFor.ForgetPass
import com.doancs3_new.all_UI.RegLogFor.Login
import com.doancs3_new.all_UI.RegLogFor.Register
import com.doancs3_new.all_UI.RegLogFor.ResetPass
import com.doancs3_new.all_UI.RegLogFor.VerifiCode


class MainActivity : ComponentActivity() { // Đổi tên thành MainActivity
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            HostUI(navController)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun HostUI(navController: NavHostController) {
    val pagerState = rememberPagerState(initialPage = 0) { 4 } // thêm dòng này
    NavHost(
        navController = navController,
        startDestination = "startScreen"
//        startDestination = "All Detail Profile"
    ) {
        // ON BOARDING
        composable("startScreen") { StartScreen(navController) }
        composable("onboarding") { OnboardingPagerScreen(navController) } // <-- đây gọi gói luôn HorizontalPager

        // REGISTER - LOGIN - FORGET pass
        composable("RLF - Register") { Register(navController) }
        composable("RLF - Login") { Login(navController) }
        composable("RLF - Forget Password") { ForgetPass(navController) }
        composable("RLF - Verification Code") { VerifiCode(navController) }
        composable("RLF - Reset Password") { ResetPass(navController) }

        //DETAIL PROFILE
        composable("All Detail Profile") { AllDetailProfile( navController) }

        //DASH BOARD
        composable("Home") {
            val sharedViewModel: SharedViewModel = viewModel()
            HomeSummaryScreen(
                name = sharedViewModel.name,
                currentWeight = sharedViewModel.currentWeight,
                targetWeight = sharedViewModel.targetWeight,
                heightCm = sharedViewModel.heightCm,
                durationMonths = 6, // Nếu có lưu thời gian tập
                workoutLocation = "Fitness Center", // Nếu có
                navController = navController
            )
        }

    }
}
