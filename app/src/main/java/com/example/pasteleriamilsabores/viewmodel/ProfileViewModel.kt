package com.example.pasteleriamilsabores.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pasteleriamilsabores.data.MilSaboresDatabase
import com.example.pasteleriamilsabores.model.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val usuarioDao = MilSaboresDatabase.getDatabase(application).usuarioDao()

    private val _usuario = MutableStateFlow<Usuario?>(null)
    val usuario = _usuario.asStateFlow()

    private val _mensaje = MutableStateFlow("")
    val mensaje = _mensaje.asStateFlow()

    fun cargarUsuario(id: Int) {
        viewModelScope.launch {
            _usuario.value = usuarioDao.obtenerPorId(id)
        }
    }

    fun actualizarUsuario(usuarioEditado: Usuario) {
        viewModelScope.launch {
            usuarioDao.actualizar(usuarioEditado)
            _mensaje.value = "Datos actualizados correctamente"
            _usuario.value = usuarioEditado
        }
    }
}
