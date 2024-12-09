package com.example.equis.adaptador

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.equis.Entrenador
import com.example.equis.ListaEntrenadores
import com.example.equis.R

class EntrenadorAdaptador(private val listaEntrenadores: MutableList<Entrenador>) : RecyclerView.Adapter<EntrenadorVista>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            EntrenadorVista {

        val layoutInflater = LayoutInflater.from(parent.context)

        return EntrenadorVista(layoutInflater.inflate(R.layout.cardview_entrenador, parent,
            false))
    }


    override fun getItemCount(): Int = listaEntrenadores.size
    override fun onBindViewHolder(holder: EntrenadorVista, position: Int) {

        val item = listaEntrenadores[position]

        holder.devolver(item)
    }

    fun actualizarDatos(nuevaLista: MutableList<Entrenador>) {
        listaEntrenadores.clear()
        listaEntrenadores.addAll(nuevaLista)
        notifyDataSetChanged()
    }
}