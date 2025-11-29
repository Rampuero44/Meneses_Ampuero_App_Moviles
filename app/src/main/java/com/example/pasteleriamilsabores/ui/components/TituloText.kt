package com.example.pasteleriamilsabores.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TituloText(titulo : String){
    Text(
        text = titulo,
        style = MaterialTheme.typography.headlineSmall
    )
}