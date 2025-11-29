package com.example.pasteleriamilsabores.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pasteleriamilsabores.model.Categoria

@Dao
interface CategoriaDao {

    @Insert
    suspend fun insertar(categoria: Categoria)

    @Update
    suspend fun actualizar(categoria: Categoria)

    @Delete
    suspend fun eliminar(categoria: Categoria)

    @Query("SELECT * FROM Categoria")
    suspend fun obtenerTodas(): List<Categoria>

    @Query("SELECT * FROM Categoria WHERE id = :id")
    suspend fun obtenerPorId(id: Int): Categoria?
}
