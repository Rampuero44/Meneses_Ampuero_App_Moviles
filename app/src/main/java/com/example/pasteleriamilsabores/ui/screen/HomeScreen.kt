package com.example.pasteleriamilsabores.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pasteleriamilsabores.ui.components.ProductoCard
import com.example.pasteleriamilsabores.viewmodel.TiendaViewModel

@Composable
fun HomeScreen(navController: NavController){
    val viewModel : TiendaViewModel = viewModel()
    val productos by viewModel.productos.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarProductos()
    }

    Column (
        modifier = Modifier
            .padding(50.dp)
            .fillMaxSize()
    ){
        Text(
            text = "Bienvenido/a a nuestra Pasteleria",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xD0801111)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Nuestros Productos",
            fontSize = 18.sp,
            color = Color(0xD0A99898)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(productos) { producto ->
                ProductoCard(producto)
            }
        }
    }
}
