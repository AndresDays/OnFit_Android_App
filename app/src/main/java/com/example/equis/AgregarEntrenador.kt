package com.example.equis

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AgregarEntrenador : AppCompatActivity() {

    private lateinit var nombreEntrenador : EditText
    private lateinit var horarioEntrenador : EditText
    private lateinit var gimnasioEntrenador: EditText
    private lateinit var agregarEntrenador: ImageButton
    private lateinit var regresarEntrenador : Button
    private lateinit var imagenEntrenador: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agregar_entrenador)

        nombreEntrenador = findViewById(R.id.txtNombreEntrenador)
        horarioEntrenador = findViewById(R.id.txtAgregarHorario)
        gimnasioEntrenador = findViewById(R.id.txtGymAgregar)
        agregarEntrenador = findViewById(R.id.imgBtnAgregar)
        regresarEntrenador = findViewById(R.id.btnRegresarEntrenador)
        imagenEntrenador = findViewById(R.id.txtNumeroImagen)

        agregarEntrenador.setOnClickListener {
            agregarEntrenador()
        }

        regresarEntrenador.setOnClickListener {
            finish()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun agregarEntrenador() {
        // Obtener los datos del formulario
        val nombre = nombreEntrenador.text.toString()
        val horario = horarioEntrenador.text.toString()
        val gimnasio = gimnasioEntrenador.text.toString()
        val imagen = imagenEntrenador.text.toString().toInt() // suponer que imagen se guarda como un número


        if (nombre.isBlank() || horario.isBlank() || gimnasio.isBlank() || imagenEntrenador.text.isBlank()) {
            Toast.makeText(this, "Por favor llena todos los campos.", Toast.LENGTH_SHORT).show()
            return
        }

        val nuevoEntrenador = Entrenador(nombre, horario, gimnasio, imagen)


        ListaEntrenadores.listaEntrenadores += nuevoEntrenador

        Toast.makeText(this, "Entrenador agregado exitosamente.", Toast.LENGTH_SHORT).show()

        limpiarCampos()
    }

    private fun limpiarCampos() {
        nombreEntrenador.text.clear()
        horarioEntrenador.text.clear()
        gimnasioEntrenador.text.clear()
        imagenEntrenador.text.clear()
    }


}