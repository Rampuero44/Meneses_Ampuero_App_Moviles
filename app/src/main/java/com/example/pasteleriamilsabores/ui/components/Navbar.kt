package com.example.pasteleriamilsabores.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pasteleriamilsabores.viewmodel.ProfileViewModel
import com.example.pasteleriamilsabores.viewmodel.SesionViewModel

@Composable
fun Navbar(
    navController: NavController,
    sesionViewModel: SesionViewModel,
    profileViewModel: ProfileViewModel = viewModel()
) {
    val usuarioSesion by sesionViewModel.usuarioActual.collectAsState()
    val usuario by profileViewModel.usuario.collectAsState()

    val userName = usuarioSesion?.nombre ?: (usuario?.nombre ?: "")
    val userId = usuarioSesion?.id ?: 0

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 4.dp,
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Bienvenido, $userName",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF8B4513),
                fontSize = 18.sp
            )
            Row {
                IconButton(onClick = { navController.navigate("perfil") }) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "Perfil",
                        tint = Color(0xFF8B4513)
                    )
                }
                IconButton(onClick = { navController.navigate("carrito") }) {
                    Icon(
                        Icons.Default.ShoppingCart,
                        contentDescription = "Carrito",
                        tint = Color(0xFF8B4513)
                    )
                }
            }
        }
    }
}