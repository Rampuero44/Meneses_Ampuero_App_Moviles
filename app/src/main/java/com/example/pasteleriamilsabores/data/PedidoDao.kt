package com.example.pasteleriamilsabores.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pasteleriamilsabores.model.Pedido

@Dao
interface PedidoDao {

    @Insert
    suspend fun insertar(pedido: Pedido)

    @Update
    suspend fun actualizar(pedido: Pedido)

    @Delete
    suspend fun eliminar(pedido: Pedido)

    @Query("SELECT * FROM Pedido")
    suspend fun obtenerTodos(): List<Pedido>

    @Query("SELECT * FROM Pedido WHERE id = :id")
    suspend fun obtenerPorId(id: Int): Pedido?

    @Query("SELECT * FROM Pedido WHERE usuarioId = :usuarioId")
    suspend fun obtenerPorUsuario(usuarioId: Int): List<Pedido>
}
