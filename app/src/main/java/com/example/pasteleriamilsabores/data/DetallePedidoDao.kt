package com.example.pasteleriamilsabores.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.pasteleriamilsabores.model.DetallePedido
import kotlinx.coroutines.flow.Flow

@Dao
interface DetallePedidoDao {

    @Insert
    suspend fun insertarDetalle(detalle: DetallePedido)

    @Update
    suspend fun actualizarDetalle(detalle: DetallePedido)

    @Delete
    suspend fun eliminar(detalle: DetallePedido)


    @Query("SELECT * FROM DetallePedido WHERE pedidoId = :pedidoId AND productoId = :productoId LIMIT 1")
    suspend fun obtenerDetallePorProducto(pedidoId: Int, productoId: Int): DetallePedido?


    @Query("SELECT * FROM DetallePedido WHERE pedidoId = :pedidoId")
    suspend fun obtenerDetallesPorPedido(pedidoId: Int): List<DetallePedido>

    @Query("SELECT * FROM DetallePedido WHERE pedidoId = :pedidoId AND productoId = :productoId LIMIT 1")
    suspend fun obtenerItem(pedidoId: Int, productoId: Int): DetallePedido?

    @Query("SELECT COALESCE(SUM(cantidad), 0) FROM DetallePedido WHERE pedidoId = :pedidoId")
    fun obtenerCantidadTotal(pedidoId: Int): Flow<Int>


    @Query("SELECT COALESCE(SUM(cantidad * precioUnitario), 0) FROM DetallePedido WHERE pedidoId = :pedidoId")
    fun obtenerTotalCarrito(pedidoId: Int): Flow<Int>

    @Query("DELETE FROM DetallePedido WHERE pedidoId = :pedidoId")
    suspend fun limpiarPedido(pedidoId: Int)
}
