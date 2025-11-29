package com.example.pasteleriamilsabores.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pasteleriamilsabores.ui.components.BotonPrincipal
import com.example.pasteleriamilsabores.ui.components.CampoTexto
import com.example.pasteleriamilsabores.ui.components.EspacioAltura
import com.example.pasteleriamilsabores.ui.components.TituloText
import com.example.pasteleriamilsabores.viewmodel.ProfileViewModel

@Composable
fun EditarPerfilScreen(
    viewModel: ProfileViewModel = viewModel(),
    correoUsuario: String,
    onVolver: () -> Unit
) {
    val usuario by viewModel.usuario.collectAsState()
    var nombre by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    val mensaje by viewModel.mensaje.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarUsuario(correoUsuario)
    }

    usuario?.let { user ->

        LaunchedEffect(user) {
            nombre = user.nombre
            contrasena = user.contrasena
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {

            TituloText("Editar Perfil")

            EspacioAltura(16)

            CampoTexto(
                valor = nombre,
                onValorCambio = { nombre = it },
                etiqueta = "Nombre"
            )

            EspacioAltura(8)

            CampoTexto(
                valor = contrasena,
                onValorCambio = { contrasena = it },
                etiqueta = "Contraseña"
            )

            EspacioAltura(24)

            BotonPrincipal(texto = "Guardar Cambios") {

                val usuarioEditado = user.copy(
                    nombre = nombre,
                    contrasena = contrasena
                )

                viewModel.actualizarUsuario(usuarioEditado)
            }

            if (mensaje.isNotEmpty()) {
                EspacioAltura(12)
                Text(text = mensaje, color = Color.Green)
            }

            EspacioAltura(16)

            BotonPrincipal(texto = "Volver") {
                onVolver()
            }
        }
    }
}
