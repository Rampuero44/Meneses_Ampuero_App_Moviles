package com.example.pasteleriamilsabores.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


data class UsuarioSesion(
    val id: Int,
    val nombre: String,
    val correo: String? = null,
    val contrasena: String
)

class SesionViewModel : ViewModel() {

    private val _usuarioActual = MutableStateFlow<UsuarioSesion?>(null)
    val usuarioActual: StateFlow<UsuarioSesion?> = _usuarioActual

    fun setUsuario(usuario: UsuarioSesion) {
        _usuarioActual.value = usuario
    }

    fun cerrarSesion() {
        _usuarioActual.value = null
    }
}