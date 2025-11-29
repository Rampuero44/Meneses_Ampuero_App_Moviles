package com.example.pasteleriamilsabores.model

import androidx.room.Entity

@Entity(
    tableName = "DetallePedido",
    primaryKeys = ["pedidoId", "productoId"]
)
data class DetallePedido(
    val pedidoId: Int,
    val productoId: Int,
    val cantidad: Int,
    val precioUnitario: Int
)
