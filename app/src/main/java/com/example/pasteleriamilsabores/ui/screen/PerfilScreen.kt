package com.example.pasteleriamilsabores.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pasteleriamilsabores.ui.components.BotonPrincipal
import com.example.pasteleriamilsabores.ui.components.DatoPerfil
import com.example.pasteleriamilsabores.ui.components.EspacioAltura
import com.example.pasteleriamilsabores.ui.components.TituloText
import com.example.pasteleriamilsabores.viewmodel.ProfileViewModel


@Composable
fun PerfilScreen(
    viewModel: ProfileViewModel = viewModel(),
    correoUsuario: String,
    onEditarPerfil: () -> Unit
) {

    val usuario by viewModel.usuario.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarUsuario(correoUsuario)
    }

    usuario?.let { user ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {

            TituloText("Mi Perfil")


            EspacioAltura(24)

            DatoPerfil(titulo = "Nombre", valor = user.nombre)
            DatoPerfil(titulo = "Correo", valor = user.correo)
            DatoPerfil(titulo = "Contraseña", valor = "********")

            EspacioAltura(32)

            BotonPrincipal(texto = "Editar Perfil") {
                onEditarPerfil()
            }
        }
    }
}
