package com.example.pasteleriamilsabores.ui.components

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun CampoTexto(
    valor: String,
    onValorCambio: (String) -> Unit,
    etiqueta: String,
    visualTransformation: VisualTransformation = VisualTransformation.None
){
    OutlinedTextField(
        value = valor,
        onValueChange = onValorCambio,
        label = { Text(etiqueta)},
        visualTransformation = visualTransformation
    )
}

@Composable
fun CampoTextoSoloLectura(
    valor: String,
    etiqueta: String
) {
    OutlinedTextField(
        value = valor,
        onValueChange = {},
        label = { Text(etiqueta) },
        readOnly = true
    )
}