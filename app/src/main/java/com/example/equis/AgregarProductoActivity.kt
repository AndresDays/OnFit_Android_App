package com.example.equis

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AgregarProductoActivity : AppCompatActivity() {


    private lateinit var nombre : EditText
    private lateinit var cantidad : EditText
    private lateinit var accesorio : Switch
    private lateinit var suplemento : Switch
    private lateinit var precio : EditText
    private lateinit var enviar : Button
    private lateinit var regresar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_agregar_accesorio)

        nombre = findViewById(R.id.txtNombreProducto)
        cantidad = findViewById(R.id.txtCantidadProducto)
        accesorio = findViewById(R.id.switchAccesorio)
        suplemento = findViewById(R.id.switchSuplemento)
        precio = findViewById(R.id.txtPrecioProducto)
        enviar = findViewById(R.id.btnEnviarProducto)
        regresar = findViewById(R.id.btnRegresarProducto)

        enviar.setOnClickListener {
            agregarProducto()
        }

        regresar.setOnClickListener {
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }

    private fun agregarProducto() {
        val nombreProducto = nombre.text.toString()
        val cantidadProducto = cantidad.text.toString().toIntOrNull()
        val precioProducto = precio.text.toString().toDoubleOrNull()

        if (nombreProducto.isBlank() || cantidadProducto == null || precioProducto == null) {
            Toast.makeText(this, "Por favor, completa todos los campos correctamente.", Toast.LENGTH_SHORT).show()
            return
        }

        val categoriaProducto = when {
            accesorio.isChecked -> "Accesorio"
            suplemento.isChecked -> "Suplemento"
            else -> "Sin categoría"
        }

        val nuevoProducto = Producto(
            nombre = nombreProducto,
            categoria = categoriaProducto,
            precio = precioProducto,
            cantidadDisponible = cantidadProducto
        )

        Producto.listaProductos.add(nuevoProducto)

        Toast.makeText(this, "Producto agregado: $nombreProducto", Toast.LENGTH_SHORT).show()
        limpiarCampos()
    }
    private fun limpiarCampos() {
        nombre.text.clear()
        cantidad.text.clear()
        precio.text.clear()
        accesorio.isChecked = false
        suplemento.isChecked = false
    }
}