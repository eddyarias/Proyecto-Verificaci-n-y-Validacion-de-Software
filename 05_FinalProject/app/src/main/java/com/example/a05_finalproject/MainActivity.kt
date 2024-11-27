package com.example.a05_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(
            findViewById(R.id.id_layout_main),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Inicializar Base de Datos
        EBaseDeDatosAutos.tablaAuto = ESqliteHelperAutos(
            this
        )
        EBaseDeDatosPartes.tablaParte = ESqliteHelperPartes(
            this
        )

        // Botones para navegar entre las inferces de Gestion
        val botonAutos = findViewById<Button>(R.id.btn_autos)
        botonAutos.setOnClickListener {
            irActividad((CGestionAutos::class.java))
        }
        val botonPartes = findViewById<Button>(R.id.btn_partes)
        botonPartes.setOnClickListener {
            irActividad((CGestionPartes::class.java))
        }
    }
    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}