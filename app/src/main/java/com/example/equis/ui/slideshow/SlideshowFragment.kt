package com.example.equis.ui.slideshow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.equis.AgregarProductoActivity
import com.example.equis.R
import com.example.equis.databinding.FragmentSuplementosBinding

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSuplementosBinding? = null
    private lateinit var btnAgregar : ImageButton

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSuplementosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        btnAgregar = binding.root.findViewById(R.id.btnAgregarSuplemento)

        setupProductClickListeners()

        val sharedPreferences = requireActivity().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

        val userType = sharedPreferences.getString("userType", "cliente")

        if (userType == "cliente") {
            btnAgregar.visibility = View.GONE
        } else {
            btnAgregar.setOnClickListener {
                val intent = Intent(requireContext(), AgregarProductoActivity::class.java)
                startActivity(intent)
            }
        }

        val textView = binding.textSlideshow
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root
    }

    private fun setupProductClickListeners() {
        // Configurar clics para cada producto con su mensaje de precio
        binding.proteina1.setOnClickListener {
            Toast.makeText(requireContext(), "Proteína Whey: $600 MXN", Toast.LENGTH_SHORT).show()
        }

        binding.proteina2.setOnClickListener {
            Toast.makeText(requireContext(), "Proteína Caseína: $700 MXN", Toast.LENGTH_SHORT).show()
        }

        binding.proteina3.setOnClickListener {
            Toast.makeText(requireContext(), "Creatina Monohidratada: $400 MXN", Toast.LENGTH_SHORT).show()
        }

        binding.proteina4.setOnClickListener {
            Toast.makeText(requireContext(), "Aminoácidos BCAA: $500 MXN", Toast.LENGTH_SHORT).show()
        }

        binding.proteina5.setOnClickListener {
            Toast.makeText(requireContext(), "Pre-Workout: $800 MXN", Toast.LENGTH_SHORT).show()
        }

        binding.proteina6.setOnClickListener {
            Toast.makeText(requireContext(), "Multivitaminas: $300 MXN", Toast.LENGTH_SHORT).show()
        }

        binding.proteina7.setOnClickListener {
            Toast.makeText(requireContext(), "Omega-3: $250 MXN", Toast.LENGTH_SHORT).show()
        }

        binding.proteina8.setOnClickListener {
            Toast.makeText(requireContext(), "Glutamina: $450 MXN", Toast.LENGTH_SHORT).show()
        }
        binding.proteina9.setOnClickListener {
            Toast.makeText(requireContext(), "Glutamina: $500 MXN", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
