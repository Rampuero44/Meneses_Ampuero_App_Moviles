package com.example.pasteleriamilsabores.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Producto")
data class Producto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val tipo: String,
    val precio: Int,
    val imagen: String?,
    val categoriaId: Int,
    val descripcion: String?
)
