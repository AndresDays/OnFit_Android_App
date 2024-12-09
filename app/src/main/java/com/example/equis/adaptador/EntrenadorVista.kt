package com.example.equis.adaptador

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.equis.Entrenador
import com.example.equis.R

class EntrenadorVista(view: View) : RecyclerView.ViewHolder(view) {
    private val nombre = view.findViewById<TextView>(R.id.txtEntrenador)
    private val horario = view.findViewById<TextView>(R.id.txtHorarioEntrenador)
    private val gym = view.findViewById<TextView>(R.id.txtGym)
    private val imagen = view.findViewById<ImageView>(R.id.imgEntrenador)


    fun devolver(entrenadoresModelo: Entrenador){
        nombre.text = entrenadoresModelo.nombreEntrenador
        horario.text = entrenadoresModelo.horario
        gym.text = entrenadoresModelo.gym
        when(entrenadoresModelo.imagen){
            1 -> imagen.setImageResource(R.drawable.entrenador1)
            2 -> imagen.setImageResource(R.drawable.entrenador2)
            3 -> imagen.setImageResource(R.drawable.entrenador3)
            4 -> imagen.setImageResource(R.drawable.entrenador4)
            5 -> imagen.setImageResource(R.drawable.entrenador5)
            6 -> imagen.setImageResource(R.drawable.entrenador6)
            7 -> imagen.setImageResource(R.drawable.entrenador7)
            8 -> imagen.setImageResource(R.drawable.entrenador8)
            9 -> imagen.setImageResource(R.drawable.entrenador9)
            10 -> imagen.setImageResource(R.drawable.entrenador10)
            11 -> imagen.setImageResource(R.drawable.entrenadora11)
            else -> imagen.setImageResource(R.drawable.entrenador1)
        }//when
    }//devolver
}