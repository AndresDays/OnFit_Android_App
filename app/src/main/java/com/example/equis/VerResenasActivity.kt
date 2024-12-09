package com.example.equis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class VerResenasActivity : AppCompatActivity() {
    private lateinit var listaResenas: ListView
    private lateinit var ratings: List<Rating>
    private lateinit var regresar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ver_resenas)

        listaResenas = findViewById(R.id.listResenas)
        regresar = findViewById(R.id.btnRegresarResenas)

        ratings = Rating.listaRatings

        val adapter = object : ArrayAdapter<Rating>(this, android.R.layout.simple_list_item_2, android.R.id.text1, ratings) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val rating = getItem(position)
                val view = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false)
                val text1 = view.findViewById<TextView>(android.R.id.text1)
                val text2 = view.findViewById<TextView>(android.R.id.text2)

                text1.text = "Estrellas: ${rating?.valor}"
                text2.text = "Comentario: ${rating?.comentario}"

                return view
            }
        }

        listaResenas.adapter = adapter

        regresar.setOnClickListener {
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}