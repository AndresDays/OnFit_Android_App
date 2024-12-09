package com.example.equis.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.equis.databinding.FragmentClasesBinding

class GalleryFragment : Fragment() {

    private var _binding: FragmentClasesBinding? = null
    private val binding get() = _binding!!

    // Lista para registrar inscripciones
    private val registeredClasses = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Instanciar el ViewModel
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentClasesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Configurar observador para datos del ViewModel
        galleryViewModel.text.observe(viewLifecycleOwner) { text ->
            binding.textGallery.text = text
        }

        // Configurar eventos de los botones
        setupButtonListeners()

        return root
    }

    private fun setupButtonListeners() {
        binding.btnyoga.setOnClickListener {
            registerClass("Yoga")
        }
        binding.buttonSpinning.setOnClickListener { registerClass("Spinning") }
        binding.buttonPilates.setOnClickListener { registerClass("Pilates") }
        binding.buttonZumba.setOnClickListener { registerClass("Zumba") }
    }

    private fun registerClass(className: String) {
        if (!registeredClasses.contains(className)) {
            registeredClasses.add(className)
            Toast.makeText(requireContext(), "Inscripción exitosa en $className", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Ya estás inscrito en $className", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
