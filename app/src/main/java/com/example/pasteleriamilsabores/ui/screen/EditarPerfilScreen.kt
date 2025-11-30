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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pasteleriamilsabores.ui.components.BotonPrincipal
import com.example.pasteleriamilsabores.ui.components.CampoTexto
import com.example.pasteleriamilsabores.ui.components.CampoTextoSoloLectura
import com.example.pasteleriamilsabores.ui.components.DatoPerfil
import com.example.pasteleriamilsabores.ui.components.EspacioAltura
import com.example.pasteleriamilsabores.ui.components.TituloText
import com.example.pasteleriamilsabores.ui.theme.MilSaboresFont
import com.example.pasteleriamilsabores.viewmodel.ProfileViewModel
import com.example.pasteleriamilsabores.viewmodel.SesionViewModel


@Composable
fun EditarPerfilScreen(
    navController: NavController,
    sesionViewModel: SesionViewModel
) {
    val profileViewModel: ProfileViewModel = viewModel()

    val usuarioSesion by sesionViewModel.usuarioActual.collectAsState()
    val usuario by profileViewModel.usuario.collectAsState()

    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var repetirContrasena by remember { mutableStateOf("") }


    LaunchedEffect(usuarioSesion?.id) {
        val id = usuarioSesion?.id
        if (id != null) {
            profileViewModel.cargarUsuario(id)
        }
    }

    LaunchedEffect(usuario) {
        usuario?.let {
            nombre = it.nombre
            correo = it.correo ?: ""
            contrasena = it.contrasena
            repetirContrasena = it.contrasena
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
                text = "Editar Perfil",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = MilSaboresFont
            )

            CampoTexto(
                valor = nombre,
                onValorCambio = { nombre = it },
                etiqueta = "Nombre"
            )

            CampoTexto(
                valor = correo,
                onValorCambio = { correo = it },
                etiqueta = "Correo"
            )

            CampoTexto(
                valor = contrasena,
                onValorCambio = { contrasena = it },
                etiqueta = "Contraseña"
            )

            CampoTexto(
                valor = repetirContrasena,
                onValorCambio = { repetirContrasena = it },
                etiqueta = "Repetir contraseña"
            )

            Spacer(modifier = Modifier.height(2.dp))

            Button(
                onClick = { /* Aquí luego agregamos guardar cambios */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8B4513)
                )
            ) {
                Text("Guardar Cambios")
            }

            Button(
                onClick = { navController.navigate("login") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B4513))
            ) {
                Text("Deshacer Cambios")
            }

            Button(
                onClick = { navController.navigate("home") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B4513))
            ) {
                Text("Volver a la Pastelería")
            }
        }
    }
}