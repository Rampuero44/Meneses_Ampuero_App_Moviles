package com.example.pasteleriamilsabores.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pasteleriamilsabores.data.MilSaboresDatabase
import com.example.pasteleriamilsabores.model.DetallePedido
import com.example.pasteleriamilsabores.model.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TiendaViewModel (application: Application): AndroidViewModel(application) {
    private val database = MilSaboresDatabase.getDatabase(application)
    private val productoDao = database.productoDao()

    private val _productos = MutableStateFlow(emptyList<Producto>())
    val productos: StateFlow<List<Producto>> = _productos

    fun cargarProductos(){
        viewModelScope.launch {
            val lista = productoDao.obtenerTodos()
            _productos.value = lista
        }
    }

    fun agregarAlCarrito(producto: Producto) {
        viewModelScope.launch {

            val pedidoId = 1
            val detallePedidoDao = database.detallePedidoDao()


            val detalleExistente = detallePedidoDao.obtenerDetallePorProducto(pedidoId, producto.id)

            if (detalleExistente != null) {
                val detalleActualizado = detalleExistente.copy(
                    cantidad = detalleExistente.cantidad + 1
                )
                detallePedidoDao.actualizarDetalle(detalleActualizado)
            } else {
                val nuevoDetalle = DetallePedido(
                    pedidoId = pedidoId,
                    productoId = producto.id,
                    cantidad = 1,
                    precioUnitario = producto.precio
                )
                detallePedidoDao.insertarDetalle(nuevoDetalle)
            }


        }
    }
}
