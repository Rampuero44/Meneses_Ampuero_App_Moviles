package com.example.pasteleriamilsabores.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pasteleriamilsabores.ui.screen.CarritoScreen
import com.example.pasteleriamilsabores.ui.screen.HomeScreen
import com.example.pasteleriamilsabores.ui.screen.LoginScreen
import com.example.pasteleriamilsabores.ui.screen.PerfilScreen
import com.example.pasteleriamilsabores.ui.screen.RegistroScreen
import com.example.pasteleriamilsabores.ui.screen.SplashScreen
import com.example.pasteleriamilsabores.viewmodel.SesionViewModel


@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    val sesionViewModel: SesionViewModel = viewModel()

    NavHost(navController = navController, startDestination = "splash"){
        composable ("splash"){ SplashScreen(navController = navController) }
        composable ("login"){ LoginScreen(navController = navController, sesionViewModel = sesionViewModel) }
        composable ("home"){ HomeScreen(navController = navController, sesionViewModel = sesionViewModel) }
        composable ("registro"){ RegistroScreen(navController = navController) }
        composable ("carrito"){ CarritoScreen(navController = navController, sesionViewModel = sesionViewModel) }
        composable ("perfil"){ PerfilScreen(navController = navController, sesionViewModel = sesionViewModel) }

    }
}