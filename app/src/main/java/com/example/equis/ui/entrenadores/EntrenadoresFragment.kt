package com.example.equis.ui.entrenadores

import android.content.Context
import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.equis.AgregarEntrenador
import com.example.equis.AgregarUsuarioActivity
import com.example.equis.Cliente
import com.example.equis.ListaEntrenadores
import com.example.equis.R
import com.example.equis.VerResenasActivity
import com.example.equis.adaptador.EntrenadorAdaptador
import com.example.equis.databinding.FragmentEntrenadoresBinding
import com.example.equis.databinding.FragmentSuplementosBinding
import com.example.equis.ui.slideshow.SlideshowViewModel

class EntrenadoresFragment : Fragment() {


    private var usuarios = ArrayList<Cliente>()
    private val REGISTRAR_CODE = 1

    companion object {
        fun newInstance() = EntrenadoresFragment()
    }

    private val viewModel: EntrenadoresViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        val view = inflater.inflate(R.layout.fragment_entrenadores, container, false)

        val sharedPreferences = requireActivity().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val userType = sharedPreferences.getString("userType", "cliente")

        val agregar = view.findViewById<ImageButton>(R.id.btnAgregarEntrenador)

        if (userType == "cliente") {
            agregar.visibility = View.GONE
        } else {
            agregar.setOnClickListener {
                val intent = Intent(requireContext(), AgregarEntrenador::class.java)
                intent.putParcelableArrayListExtra("list", usuarios)
                startActivityForResult(intent, REGISTRAR_CODE)
            }
        }


        inicializarRecycler(view)

        return view
    }



    private fun inicializarRecycler(view: View) {
        // Acceder al componente Recycler
        val recycler = view.findViewById<RecyclerView>(R.id.rvEntrenadores)
        // Administrador del componente
        recycler.layoutManager = LinearLayoutManager(requireContext())
        // Establecer en el adaptador la lista de entrenadores
        recycler.adapter = EntrenadorAdaptador(ListaEntrenadores.listaEntrenadores)

    }
}