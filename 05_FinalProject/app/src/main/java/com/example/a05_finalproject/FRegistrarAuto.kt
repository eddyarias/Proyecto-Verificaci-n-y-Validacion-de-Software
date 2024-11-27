package com.example.a05_finalproject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class FRegistrarAuto : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fregistrar_auto)

        val botonConfirmar = findViewById<Button>(R.id.btn_confirmar)
        botonConfirmar.setOnClickListener {
            registrarAuto()
        }
    }

    private fun registrarAuto() {
        val etIdentificador = findViewById<EditText>(R.id.et_identificador)
        val etMarca = findViewById<EditText>(R.id.et_marca)
        val etAnio = findViewById<EditText>(R.id.et_anio)
        val etPrecio = findViewById<EditText>(R.id.et_precio)
        val swElectrico = findViewById<Switch>(R.id.sw_electrico)

        val idAuto = etIdentificador.text.toString().toIntOrNull()
        val marca = etMarca.text.toString()
        val anio = etAnio.text.toString().toIntOrNull()
        val precio = etPrecio.text.toString().toDoubleOrNull()
        val electrico = if (swElectrico.isChecked) "Sí" else "No"

        if (idAuto != null && marca.isNotEmpty() && anio != null && precio != null) {
            val resultado = EBaseDeDatosAutos.tablaAuto?.crearAuto(
                marca,
                anio,
                precio,
                electrico
            )
            if (resultado == true) {
                mostrarSnackbar("Auto registrado exitosamente")
                limpiarFormulario()
            } else {
                mostrarSnackbar("Error al registrar el auto")
            }
        } else {
            mostrarSnackbar("Por favor, complete todos los campos correctamente")
        }
    }

    private fun mostrarSnackbar(mensaje: String) {
        Snackbar.make(findViewById(R.id.btn_confirmar), mensaje, Snackbar.LENGTH_LONG).show()
    }

    private fun limpiarFormulario() {
        findViewById<EditText>(R.id.et_identificador).setText("")
        findViewById<EditText>(R.id.et_marca).setText("")
        findViewById<EditText>(R.id.et_anio).setText("")
        findViewById<EditText>(R.id.et_precio).setText("")
        findViewById<Switch>(R.id.sw_electrico).isChecked = false
    }
}
