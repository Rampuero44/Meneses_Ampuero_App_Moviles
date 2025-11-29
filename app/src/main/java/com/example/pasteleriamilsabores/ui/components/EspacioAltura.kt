package com.example.pasteleriamilsabores.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EspacioAltura(alturaDp: Int) {
    Spacer(modifier = Modifier.height(alturaDp.dp))
}

@Composable
fun EspacioAncho(anchoDp: Int) {
    Spacer(modifier = Modifier.height(anchoDp.dp))
}