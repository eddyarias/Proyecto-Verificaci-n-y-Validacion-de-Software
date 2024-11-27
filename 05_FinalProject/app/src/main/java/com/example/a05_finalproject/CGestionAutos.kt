package com.example.a05_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CGestionAutos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cgestion_autos)

        // Registrar Auto
        val botonRegistrarAuto = findViewById<Button>(R.id.btn_registrar_auto)
        botonRegistrarAuto.setOnClickListener {
            irActividad((FRegistrarAuto::class.java))
        }
        // Ver Auto
        val botonVerAuto = findViewById<Button>(R.id.btn_ver_auto)
        botonVerAuto.setOnClickListener {
            irActividad((FVerAuto::class.java))
        }
        // Editar Auto
        val botonEditarAuto = findViewById<Button>(R.id.btn_editar_auto)
        botonEditarAuto.setOnClickListener {
            irActividad((FEditarAuto::class.java))
        }
        // Eliminar Auto
        val botonEliminarAuto = findViewById<Button>(R.id.btn_eliminar_auto)
        botonEliminarAuto.setOnClickListener {
            irActividad((FEliminarAuto::class.java))
        }
    }
    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}