package com.example.a05_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CGestionPartes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cgestion_partes)

        // Registrar Parte
        val botonRegistrarParte = findViewById<Button>(R.id.btn_registrar_parte)
        botonRegistrarParte.setOnClickListener {
            irActividad((HRegistrarParte::class.java))
        }
        // Ver Parte
        val botonVerParte = findViewById<Button>(R.id.btn_ver_parte)
        botonVerParte.setOnClickListener {
            irActividad((HVerParte::class.java))
        }
        // Editar Parte
        val botonEditarParte = findViewById<Button>(R.id.btn_editar_parte)
        botonEditarParte.setOnClickListener {
            irActividad((HEditarParte::class.java))
        }
        // Eliminar Parte
        val botonEliminarParte = findViewById<Button>(R.id.btn_eliminar_parte)
        botonEliminarParte.setOnClickListener {
            irActividad((HEliminarParte::class.java))
        }
    }
    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}