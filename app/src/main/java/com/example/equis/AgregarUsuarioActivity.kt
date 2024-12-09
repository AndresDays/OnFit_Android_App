package com.example.equis

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AgregarUsuarioActivity : AppCompatActivity() {

    private lateinit var editTextNombre: EditText
    private lateinit var editTextTelefono: EditText
    private lateinit var editTextCorreo: EditText
    private lateinit var editTxtContrasena: EditText
    private lateinit var switchCliente: Switch
    private lateinit var switchAdmin: Switch
    private lateinit var buttonRegistrar: Button
    private lateinit var regresar : Button

    private var usuarios = ArrayList<Cliente>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_usuario)

        editTextNombre = findViewById(R.id.editTxtNombre)
        editTextTelefono = findViewById(R.id.editTxtTelefono)
        editTextCorreo = findViewById(R.id.editTxtEmail)
        editTxtContrasena = findViewById(R.id.editTxtContrasena)
        switchCliente = findViewById(R.id.switchCliente)
        switchAdmin = findViewById(R.id.switchAdmin)
        buttonRegistrar = findViewById(R.id.buttonRegistrar)
        regresar = findViewById(R.id.btnRegresar1)

        intent.getParcelableArrayListExtra<Cliente>("list")?.let {
            usuarios.addAll(it)
        }

        switchCliente.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) switchAdmin.isChecked = false
        }

        switchAdmin.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) switchCliente.isChecked = false
        }

        regresar.setOnClickListener{
            val resultIntent = Intent()
            resultIntent.putParcelableArrayListExtra("list", usuarios)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        buttonRegistrar.setOnClickListener {
            val id = 0;
            val fechaInicioMembresia = ""
            val fechaFinMembresia = ""
            val asistenciasRegistradas = 0
            val planMembresia = ""
            val nombre = editTextNombre.text.toString().trim()
            val telefono = editTextTelefono.text.toString().trim()
            val correo = editTextCorreo.text.toString().trim()
            val contrasena = editTxtContrasena.text.toString().trim()
            val tipoUsuario = if (switchCliente.isChecked) "cliente" else "administrador"

            if (nombre.isEmpty() || telefono.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val listado = mapOf(
                    "username" to nombre,
                    "password" to contrasena,
                    "userType" to tipoUsuario,
                    "telefono" to telefono,
                    "correo" to correo
                )

                val usuario = Cliente(
                    id,
                    nombre,
                    contrasena,
                    correo,
                    telefono.toInt(),
                    tipoUsuario,
                    fechaInicioMembresia,
                    fechaFinMembresia,
                    asistenciasRegistradas,
                    planMembresia
                )
                usuarios.add(usuario)

                LoginActivity.userList.add(listado)

                Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()

                //finish()
            }
        }

    }

}
