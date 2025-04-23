package com.doancs3_new

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.doancs3_new.DetailProf.AllDetailProfile
import com.doancs3_new.DetailProf.Gender
import com.doancs3_new.Onboarding.OnboardingPagerScreen
import com.doancs3_new.Onboarding.StartScreen
import com.doancs3_new.RegLogFor.Login
import com.doancs3_new.RegLogFor.Register
import com.doancs3_new.Onboarding.Screen1
import com.doancs3_new.Onboarding.Screen2
import com.doancs3_new.Onboarding.Screen3
import com.doancs3_new.Onboarding.Screen4
import com.doancs3_new.RegLogFor.ForgetPass
import com.doancs3_new.RegLogFor.ResetPass
import com.doancs3_new.RegLogFor.VerifiCode

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
    }
}
