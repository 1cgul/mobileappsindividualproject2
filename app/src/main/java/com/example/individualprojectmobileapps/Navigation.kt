package com.example.individualprojectmobileapps
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay


@Composable
fun Navigation() {
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = "main_screen"  ){
        composable("splash_screen"
        ) {
            SplashScreen(navController)

        }
        composable("registration_screen") {
            RegistrationPage(navController)
        }
       composable("login_screen"){
            LoginPage(navController)
        }
        composable("rules_screen") {
            RulesScreen(navController)

        }
        composable("main_screen") {
            MainScreen(navController)

        }

    }

}


@Composable
fun SplashScreen(navController: NavController){
    val scale= remember {
        Animatable(0f, 1f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.5f,
            animationSpec = tween(durationMillis = 1000,0, easing = {
                OvershootInterpolator(2f).getInterpolation(it)
            }
            ))
        delay(1000)
        navController.navigate("login_screen")
    }

    Box (contentAlignment = Alignment.Center){
        Image(painter = painterResource(id = R.drawable.logo), contentDescription ="" )
    }
}