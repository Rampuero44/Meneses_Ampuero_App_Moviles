package com.example.pasteleriamilsabores.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pasteleriamilsabores.model.DetallePedido
import com.example.pasteleriamilsabores.viewmodel.CarritoViewModel
import kotlin.collections.isNotEmpty
import androidx.compose.foundation.lazy.items
import com.example.pasteleriamilsabores.viewmodel.SesionViewModel

@Composable
fun CarritoScreen(navController: NavController,
                  sesionViewModel: SesionViewModel
) {
    val usuarioSesion by sesionViewModel.usuarioActual.collectAsState()
    val pedidoId = usuarioSesion?.id ?: 0

    val carritoViewModel: CarritoViewModel = viewModel()
    val carritoItems by carritoViewModel.carritoItems.collectAsState()
    var total by remember { mutableStateOf(0) }

    LaunchedEffect(pedidoId) {
        carritoViewModel.cargarCarrito(pedidoId)
        carritoViewModel.obtenerTotalCarrito(pedidoId).collect {
            total = it
        }
    }

    Scaffold(
        topBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shadowElevation = 4.dp,
                color = Color.White
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Mi Carrito",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF8B4513),
                        fontSize = 20.sp
                    )
                }
            }
        },
        bottomBar = {
            if (carritoItems.isNotEmpty()) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    tonalElevation = 8.dp,
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Total: $${total}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF8B4513)
                        )
                        Button(
                            onClick = {
                                navController.navigate("home")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF8B4513)
                            )
                        ) {
                            Text("Seguir Comprando")
                        }

                        Button(
                            onClick = {
                                navController.navigate("home")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF8B4513)
                            )
                        ) {
                            Text("Pagar")
                        }
                    }
                }
            }
        }
    ) { padding ->
        if (carritoItems.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Default.ShoppingCart,
                        contentDescription = "Carrito vacío",
                        tint = Color(0xD0D97070),
                        modifier = Modifier.size(64.dp)
                    )
                    Text(
                        "Tu carrito está vacío",
                        fontSize = 18.sp,
                        color = Color(0xD0D97070),
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Button(
                        onClick = {
                            navController.navigate("home")
                        },
                        modifier = Modifier.padding(top = 16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF8B4513)
                        )
                    ) {
                        Text("Volver a la Pasteleria")
                    }
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(carritoItems) { item ->
                    CarritoCard(
                        item = item,
                        onEliminar = { carritoViewModel.eliminarDelCarrito(pedidoId, it) },
                        onActualizar = { nuevaCantidad ->
                            carritoViewModel.actualizarCantidad(pedidoId, item, nuevaCantidad)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CarritoCard(
    item: DetallePedido,
    onEliminar: (DetallePedido) -> Unit,
    onActualizar: (Int) -> Unit,
    carritoViewModel: CarritoViewModel = viewModel()
) {
    var nombreProducto by remember { mutableStateOf("Cargando...") }

    LaunchedEffect(item.productoId) {
        val producto = carritoViewModel.obtenerProducto(item.productoId)
        nombreProducto = producto?.nombre ?: "Producto desconocido"
    }
    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = nombreProducto,
                    fontWeight = FontWeight.Bold
                )
                Text("Cantidad: ${item.cantidad}")
                Text("Precio: $${item.precioUnitario}")
                Text("Subtotal: $${item.cantidad * item.precioUnitario}")
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = {
                        if (item.cantidad > 1) {
                            onActualizar(item.cantidad - 1)
                        } else {
                            onEliminar(item)
                        }
                    }
                ) {
                    Text("-", fontSize = 20.sp)
                }

                Text(
                    text = "${item.cantidad}",
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                IconButton(
                    onClick = { onActualizar(item.cantidad + 1) }
                ) {
                    Text("+", fontSize = 20.sp)
                }

                IconButton(
                    onClick = { onEliminar(item) }
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                }
            }
        }
    }
}
