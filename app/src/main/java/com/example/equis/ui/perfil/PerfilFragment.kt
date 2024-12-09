package com.example.equis.ui.perfil

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.equis.AgregarUsuarioActivity
import com.example.equis.Cliente
import com.example.equis.R
import com.example.equis.VerResenasActivity

class PerfilFragment : Fragment() {

    private lateinit var txtUsuario: TextView
    private lateinit var txtNumTelefonico: TextView
    private lateinit var txtEmail: TextView
    private lateinit var btnAgregarUsuario: Button
    private lateinit var txtPlan : TextView
    private lateinit var btnRating : Button

    private var usuarios = ArrayList<Cliente>()
    private val REGISTRAR_CODE = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        txtUsuario = view.findViewById(R.id.txtUsuario)
        txtNumTelefonico = view.findViewById(R.id.txtNumTelefonico)
        txtEmail = view.findViewById(R.id.txtEmail)
        btnAgregarUsuario = view.findViewById(R.id.btnAgregarUsuario)
        txtPlan = view.findViewById(R.id.txtPlan)
        btnRating = view.findViewById(R.id.btnRating)

        val sharedPreferences = requireActivity().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "Usuario desconocido")
        val telefono = sharedPreferences.getString("telefono", "Sin número")
        val correo = sharedPreferences.getString("correo", "Sin correo")
        val plan = sharedPreferences.getString("plan", "Sin plan")
        val userType = sharedPreferences.getString("userType", "cliente")

        txtUsuario.text = username
        txtNumTelefonico.text = telefono
        txtEmail.text = correo
        txtPlan.text = plan

        if (userType == "cliente") {
            btnAgregarUsuario.visibility = View.GONE
            btnRating.visibility = View.GONE
        } else {
            btnAgregarUsuario.setOnClickListener {
                val intent = Intent(requireContext(), AgregarUsuarioActivity::class.java)
                intent.putParcelableArrayListExtra("list", usuarios)
                startActivityForResult(intent, REGISTRAR_CODE)
            }
            btnRating.setOnClickListener{
                val intent = Intent(requireContext(), VerResenasActivity::class.java)
                intent.putParcelableArrayListExtra("list", usuarios)
                startActivityForResult(intent, REGISTRAR_CODE)
            }
        }

        return view
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