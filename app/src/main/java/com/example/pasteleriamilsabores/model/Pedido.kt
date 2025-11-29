package com.example.pasteleriamilsabores.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pedido")
data class Pedido(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val usuarioId: Int,
    val fecha: String,
    val total: Int
)
