package com.example.equis

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity() {

    private var usuarios = ArrayList<Cliente>()
    private val REGISTRAR_CODE = 1

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    companion object {
        val userList = mutableListOf<Map<String, String>>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameEditText = findViewById(R.id.edtUsuario)
        passwordEditText = findViewById(R.id.edtPassword)
        loginButton = findViewById(R.id.btnLogin)

        intent.getParcelableArrayListExtra<Cliente>("list")?.let {
            usuarios.addAll(it)
        }


        if (userList.isEmpty()) {
            userList.add(
                mapOf("username" to "admin", "password" to "123", "userType" to "admin", "telefono" to "1122334455", "correo" to "admin@onfit.mx", "plan" to "Admin")
            )
            userList.add(
                mapOf("username" to "cliente", "password" to "123", "userType" to "cliente", "telefono" to "5544332211", "correo" to "cliente@gmail.com", "plan" to "N/A")
            )
        }

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val user = userList.find { it["username"] == username && it["password"] == password }
                if (user != null) {
                    saveUserDetails(user)
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    navigateToMain()
                } else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun saveUserDetails(user: Map<String, String>) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("username", user["username"])
        editor.putString("userType", user["userType"])
        editor.putString("telefono",user["telefono"])
        editor.putString("correo",user["correo"])
        editor.putString("plan",user["plan"])
        editor.putString("plan",user["plan"])
        editor.apply()
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        //val intent = Intent(this,MainActivity::class.java)
        //intent.putParcelableArrayListExtra("list", usuarios)
        //startActivityForResult(intent, REGISTRAR_CODE)
        finish()
    }
    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REGISTRAR_CODE && resultCode == Activity.RESULT_OK) {
            data?.getParcelableArrayListExtra<Cliente>("list")?.let {
                usuarios.clear()
                usuarios.addAll(it)
            }
        }
    }
}