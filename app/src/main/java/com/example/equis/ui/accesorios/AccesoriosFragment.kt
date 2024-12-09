package com.example.equis.ui.accesorios

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.equis.AgregarProductoActivity
import com.example.equis.AgregarUsuarioActivity
import com.example.equis.R
import com.example.equis.databinding.FragmentAccesoriosBinding

class AccesoriosFragment : Fragment() {

    private lateinit var agregar: ImageButton

    // Declaración de la variable _binding
    private var _binding: FragmentAccesoriosBinding? = null

    // Esta propiedad es solo válida entre onCreateView y onDestroyView
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAccesoriosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        agregar = binding.root.findViewById(R.id.btnAgregar)

        val sharedPreferences = requireActivity().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

        val userType = sharedPreferences.getString("userType", "cliente")

        if (userType == "cliente") {
            agregar.visibility = View.GONE
        } else {
            agregar.setOnClickListener {
                val intent = Intent(requireContext(), AgregarProductoActivity::class.java)
                startActivity(intent)
            }
        }

        setupProductClickListeners()

        return root
    }

    private fun setupProductClickListeners() {
        binding.banda.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Bandas elásticas $200 MXN. Herramienta versátil para resistencia.",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.cuerda.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Cuerda de Saltar $300 MXN. Esencial para entrenamientos cardiovasculares.",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.guantes.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Guantes $700 MXN. Diseñados para proteger tus manos durante levantamientos.",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.mochila.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Mochila $900 MXN. Espaciosa e ideal para transportar tu equipo deportivo.",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.rueda.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Rueda para abdominales 250 MXN. Herramienta efectiva para fortalecer el núcleo.",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.shaker.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Shaker $350 MXN. Botella mezcladora para transportar tus batidos.",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.straps.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Straps $400 MXN. Correas de levantamiento que mejoran el agarre.",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.tapete.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Tapete $600 MXN. Superficie acolchada para yoga o pilates.",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.tobillera.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Tobilleras con peso $200 MXN. Accesorio ideal para añadir resistencia a tus entrenamientos.",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}