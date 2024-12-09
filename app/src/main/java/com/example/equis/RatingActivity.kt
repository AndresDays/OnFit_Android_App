package com.example.equis

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class RatingActivity : AppCompatActivity() {

    private lateinit var ratingBar: RatingBar
    private lateinit var comentario: EditText
    private lateinit var enviar: Button
    private lateinit var regresar: Button
    private lateinit var valor : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rating)

        ratingBar = findViewById(R.id.rbRating)
        comentario = findViewById(R.id.txtComentarios)
        enviar = findViewById(R.id.btnEnviar)
        regresar = findViewById(R.id.btnRegresar)
        valor = findViewById(R.id.txtValor)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        enviar.setOnClickListener {
            agregarRating()
        }

        regresar.setOnClickListener {
            finish()
        }
    }

    private fun agregarRating() {
        val estrellas = ratingBar.rating
        val comentarioTexto = comentario.text.toString()

        if (comentarioTexto.isBlank()) {
            Toast.makeText(this, "Por favor ingresa un comentario.", Toast.LENGTH_SHORT).show()
            return
        }

        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val fechaActual = dateFormat.format(Date())
        val idUsuario = UUID.randomUUID().hashCode() // Generar ID único para usuario
        val idRating = Rating.listaRatings.size + 1 // Generar ID incremental para la reseña

        val nuevoRating = Rating(idRating, idUsuario, fechaActual, estrellas.toInt(), comentarioTexto)

        Rating.listaRatings.add(nuevoRating)

        Toast.makeText(
            this,
            "Reseña agregada: \nEstrellas: $estrellas\nComentario: $comentarioTexto\nFecha: $fechaActual\nID Reseña: $idRating",
            Toast.LENGTH_LONG
        ).show()

        limpiarCampos()
    }

    private fun limpiarCampos() {
        ratingBar.rating = 0f
        comentario.text.clear()
    }
}