package com.example.pasteleriamilsabores.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pasteleriamilsabores.model.DetallePedido

@Dao
interface DetallePedidoDao {

    @Insert
    suspend fun insertar(detalle: DetallePedido)

    @Update
    suspend fun actualizar(detalle: DetallePedido)

    @Delete
    suspend fun eliminar(detalle: DetallePedido)

    @Query("SELECT * FROM DetallePedido")
    suspend fun obtenerTodos(): List<DetallePedido>

    @Query("SELECT * FROM DetallePedido WHERE pedidoId = :pedidoId")
    suspend fun obtenerPorPedido(pedidoId: Int): List<DetallePedido>

    @Query("SELECT * FROM DetallePedido WHERE productoId = :productoId")
    suspend fun obtenerPorProducto(productoId: Int): List<DetallePedido>
}
