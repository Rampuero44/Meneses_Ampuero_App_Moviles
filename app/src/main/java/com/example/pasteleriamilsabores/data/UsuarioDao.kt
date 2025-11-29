package com.example.pasteleriamilsabores.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pasteleriamilsabores.model.Usuario

@Dao
interface UsuarioDao {

    @Insert
    suspend fun insertar(usuario: Usuario)

    @Update
    suspend fun actualizar(usuario: Usuario)

    @Delete
    suspend fun eliminar(usuario: Usuario)

    @Query("SELECT * FROM Usuario")
    suspend fun obtenerTodos(): List<Usuario>

    @Query("SELECT * FROM Usuario WHERE id = :id")
    suspend fun obtenerPorId(id: Int): Usuario?

    @Query("SELECT * FROM Usuario WHERE correo = :correo")
    suspend fun obtenerPorCorreo(correo: String): Usuario?

    @Query("SELECT * FROM Usuario WHERE nombre = :nombre AND contrasena = :contrasena")
    suspend fun login(nombre: String, contrasena: String): Usuario?
}
