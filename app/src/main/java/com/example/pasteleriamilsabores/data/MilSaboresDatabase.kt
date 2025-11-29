package com.example.pasteleriamilsabores.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.pasteleriamilsabores.model.Categoria
import com.example.pasteleriamilsabores.model.DetallePedido
import com.example.pasteleriamilsabores.model.Pedido
import com.example.pasteleriamilsabores.model.Producto
import com.example.pasteleriamilsabores.model.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        Usuario::class,
        Categoria::class,
        Producto::class,
        Pedido::class,
        DetallePedido::class
    ],
    version = 5

)
abstract class MilSaboresDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun categoriaDao(): CategoriaDao
    abstract fun productoDao(): ProductoDao
    abstract fun pedidoDao(): PedidoDao
    abstract fun detallePedidoDao(): DetallePedidoDao

    companion object {

        private var database: MilSaboresDatabase? = null

        fun getDatabase(context: Context): MilSaboresDatabase {
            if (database == null) {
                database = Room.databaseBuilder(
                    context,
                    MilSaboresDatabase::class.java,
                    "milsabores.db"
                )
                    .createFromAsset("milsabores_db.db")
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return database!!
        }


    }
}
