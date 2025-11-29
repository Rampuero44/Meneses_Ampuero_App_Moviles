package com.example.pasteleriamilsabores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.pasteleriamilsabores.navigation.AppNavigation
import com.example.pasteleriamilsabores.ui.theme.MilSaboresTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MilSaboresTheme{AppNavigation()}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    MilSaboresTheme{}//LoginScreen()
}
