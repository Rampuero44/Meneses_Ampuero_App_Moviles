package com.example.pasteleriamilsabores.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import com.example.pasteleriamilsabores.data.MilSaboresDatabase
import com.example.pasteleriamilsabores.model.Producto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.pasteleriamilsabores.model.DetallePedido


class CarritoViewModel(application: Application) : AndroidViewModel(application) {
    private val database = MilSaboresDatabase.getDatabase(application)
    private val detallePedidoDao = database.detallePedidoDao()

    private val _carritoItems = MutableStateFlow<List<DetallePedido>>(emptyList())
    val carritoItems: StateFlow<List<DetallePedido>> = _carritoItems

    fun agregarAlCarrito(pedidoId: Int, producto: Producto, cantidad: Int = 1) {
        viewModelScope.launch {
            val itemExistente = detallePedidoDao.obtenerItem(pedidoId, producto.id)

            if (itemExistente != null) {
                val nuevoItem = itemExistente.copy(
                    cantidad = itemExistente.cantidad + cantidad
                )
                detallePedidoDao.actualizarDetalle(nuevoItem)
            } else {
                val nuevoItem = DetallePedido(
                    pedidoId = pedidoId,
                    productoId = producto.id,
                    cantidad = cantidad,
                    precioUnitario = producto.precio
                )
                detallePedidoDao.insertarDetalle(nuevoItem)
            }
            cargarCarrito(pedidoId)
        }
    }

    fun actualizarCantidad(pedidoId: Int, item: DetallePedido, nuevaCantidad: Int) {
        viewModelScope.launch {
            if (nuevaCantidad > 0) {
                val itemActualizado = item.copy(cantidad = nuevaCantidad)
                detallePedidoDao.actualizarDetalle(itemActualizado)
            } else {
                detallePedidoDao.eliminar(item)
            }
            cargarCarrito(pedidoId)
        }
    }

    fun eliminarDelCarrito(pedidoId: Int, item: DetallePedido) {
        viewModelScope.launch {
            detallePedidoDao.eliminar(item)
            cargarCarrito(pedidoId)
        }
    }

    fun cargarCarrito(pedidoId: Int) {
        viewModelScope.launch {
            _carritoItems.value = detallePedidoDao.obtenerDetallesPorPedido(pedidoId)
        }
    }

    fun obtenerCantidadTotal(pedidoId: Int): Flow<Int> {
        return detallePedidoDao.obtenerCantidadTotal(pedidoId)
    }

    fun obtenerTotalCarrito(pedidoId: Int): Flow<Int> {
        return detallePedidoDao.obtenerTotalCarrito(pedidoId)
    }

    fun limpiarCarrito(pedidoId: Int) {
        viewModelScope.launch {
            detallePedidoDao.limpiarPedido(pedidoId)
            cargarCarrito(pedidoId)
        }
    }

    private val productoDao = database.productoDao()

    suspend fun obtenerProducto(productoId: Int): Producto? {
        return productoDao.obtenerPorId(productoId)
    }
}