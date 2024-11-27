package com.example.a05_finalproject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class HRegistrarParte : AppCompatActivity() {

    private lateinit var etIdAuto: EditText
    private lateinit var btnVerificarAuto: Button
    private lateinit var btnConfirmar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hregistrar_parte)

        etIdAuto = findViewById(R.id.et_id_auto)
        btnVerificarAuto = findViewById(R.id.btn_verificar_auto)
        btnConfirmar = findViewById(R.id.btn_confirmar_parte)

        btnVerificarAuto.setOnClickListener {
            mostrarDialogoSeleccionarAuto()
        }

        btnConfirmar.setOnClickListener {
            registrarParte()
        }
    }

    private fun mostrarDialogoSeleccionarAuto() {
        val autos = EBaseDeDatosAutos.tablaAuto?.consultarTodosLosAutos()
        if (autos != null && autos.isNotEmpty()) {
            val items = autos.map { "${it.id_auto} - ${it.marca} ${it.anio}" }.toTypedArray()
            AlertDialog.Builder(this)
                .setTitle("Selecciona un Auto")
                .setItems(items) { dialog, which ->
                    etIdAuto.setText(autos[which].id_auto.toString())
                }
                .setNegativeButton("Cancelar", null)
                .show()
        } else {
            mostrarSnackbar("No hay autos registrados")
        }
    }

    private fun registrarParte() {
        val idAuto = etIdAuto.text.toString().toIntOrNull()
        val etNombre = findViewById<EditText>(R.id.et_nombre_parte)
        val etNumeroSerie = findViewById<EditText>(R.id.et_numero_serie)
        val etFechaFabricacion = findViewById<EditText>(R.id.et_fecha_fabricacion)
        val etPrecioParte = findViewById<EditText>(R.id.et_precio_parte)

        val nombre = etNombre.text.toString()
        val numeroSerie = etNumeroSerie.text.toString()
        val fechaFabricacion = etFechaFabricacion.text.toString()
        val precioParte = etPrecioParte.text.toString().toDoubleOrNull()

        if (idAuto != null && nombre.isNotEmpty() && numeroSerie.isNotEmpty() && fechaFabricacion.isNotEmpty() && precioParte != null) {
            val resultado = EBaseDeDatosPartes.tablaParte?.crearParte(
                idAuto,
                nombre,
                numeroSerie,
                fechaFabricacion,
                precioParte
            )
            if (resultado == true) {
                mostrarSnackbar("La parte se ha registrado correctamente")
                limpiarFormulario()
            } else {
                mostrarSnackbar("Error al registrar la parte")
            }
        } else {
            mostrarSnackbar("Por favor, complete todos los campos correctamente")
        }
    }

    private fun mostrarSnackbar(mensaje: String) {
        Snackbar.make(findViewById(R.id.btn_confirmar_parte), mensaje, Snackbar.LENGTH_LONG).show()
    }

    private fun limpiarFormulario() {
        etIdAuto.setText("")
        findViewById<EditText>(R.id.et_id_parte).setText("")
        findViewById<EditText>(R.id.et_nombre_parte).setText("")
        findViewById<EditText>(R.id.et_numero_serie).setText("")
        findViewById<EditText>(R.id.et_fecha_fabricacion).setText("")
        findViewById<EditText>(R.id.et_precio_parte).setText("")
    }
}