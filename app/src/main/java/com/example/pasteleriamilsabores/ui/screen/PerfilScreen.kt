package com.example.pasteleriamilsabores.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.pasteleriamilsabores.ui.components.BotonPrincipal
import com.example.pasteleriamilsabores.ui.components.CampoTextoSoloLectura
import com.example.pasteleriamilsabores.ui.components.DatoPerfil
import com.example.pasteleriamilsabores.ui.components.EspacioAltura
import com.example.pasteleriamilsabores.ui.components.TituloText
import com.example.pasteleriamilsabores.ui.theme.MilSaboresFont
import com.example.pasteleriamilsabores.viewmodel.ProfileViewModel
import com.example.pasteleriamilsabores.viewmodel.SesionViewModel


@Composable
fun PerfilScreen(
    navController: NavController,
    sesionViewModel: SesionViewModel
) {
    val profileViewModel: ProfileViewModel = viewModel()

    val usuarioSesion by sesionViewModel.usuarioActual.collectAsState()

    val usuario by profileViewModel.usuario.collectAsState()

    LaunchedEffect(usuarioSesion?.id) {
        val id = usuarioSesion?.id
        if (id != null) {
            profileViewModel.cargarUsuario(id)
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Mi Perfil",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = MilSaboresFont
            )

            CampoTextoSoloLectura(
                valor = usuario?.nombre ?: "Cargando...",
                etiqueta = "Nombre"
            )

            usuario?.let {
                if (it.correo != null) {
                    CampoTextoSoloLectura(
                        valor = it.correo,
                        etiqueta = "Correo"
                    )
                }
            }

            Spacer(modifier = Modifier.height(2.dp))

            Button(onClick = { navController.navigate("editarperfil") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8B4513)
                )
                ) {
                Text("Editar Perfil")
            }


            Button(onClick = { navController.navigate("login") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8B4513)
                )
            ) {
                Text("Cerrar Sesión")
            }



            Button(onClick = { navController.navigate("home") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8B4513)
                )
            ) {
                Text("Volver a la Pastelería")
            }







        }
    }
}
