package com.example.pasteleriamilsabores.ui.components

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pasteleriamilsabores.R
import com.example.pasteleriamilsabores.model.Producto
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ProductoCard(producto: Producto, agregarAlCarrito: (Producto) -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(2.dp, Color(0xFFFFC7C7)),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFF5EE)
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Row(modifier = Modifier.padding(16.dp)) {

            val context = LocalContext.current
            val imageId = obtieneImagen(context, producto.nombre)

            Image(
                painter = painterResource(id = imageId),
                contentDescription = producto.nombre,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = producto.nombre,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6A3A1A)
                )

                Text(
                    text = producto.tipo,
                    fontSize = 14.sp,
                    color = Color(0xFF8E7C7C)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = formatearCLP(producto.precio),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFFDB4978)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { agregarAlCarrito(producto) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF8B4513)
                    ))
                {
                    Text("Agregar")
                }
            }
        }
    }
}

private fun normalizarNombre(nombre: String): String {
    return nombre
        .lowercase()
        .replace("á", "a")
        .replace("é", "e")
        .replace("í", "i")
        .replace("ó", "o")
        .replace("ú", "u")
        .replace("ñ", "n")
        .replace("[^a-z0-9]+".toRegex(), "_")
        .trim('_')
}

private fun obtieneImagen(context: Context, imagen: String?): Int {
    val base = imagen?.substringBeforeLast('.') ?: "item_no_encontrado"
    val nombreNormalizado = normalizarNombre(base)
    val resourceId = context.resources.getIdentifier(nombreNormalizado, "drawable", context.packageName)
    return if (resourceId == 0) R.drawable.item_no_encontrado else resourceId
}

private fun formatearCLP(valor: Int): String {
    val format = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
    return format.format(valor)
}
