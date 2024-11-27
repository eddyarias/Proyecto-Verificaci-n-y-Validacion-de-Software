package com.example.a05_finalproject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class HEditarParte : AppCompatActivity() {

    private lateinit var etIdAuto: EditText
    private lateinit var etIdParte: EditText
    private lateinit var etNombre: EditText
    private lateinit var etNumeroSerie: EditText
    private lateinit var etFechaFabricacion: EditText
    private lateinit var etPrecioParte: EditText
    private lateinit var botonVerificar: Button
    private lateinit var botonConfirmarCambios: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heditar_parte)

        etIdAuto = findViewById(R.id.et_id_auto)
        etIdParte = findViewById(R.id.et_id_parte)
        etNombre = findViewById(R.id.et_nombre_parte)
        etNumeroSerie = findViewById(R.id.et_numero_serie)
        etFechaFabricacion = findViewById(R.id.et_fecha_fabricacion)
        etPrecioParte = findViewById(R.id.et_precio_parte)
        botonVerificar = findViewById(R.id.btn_verificar_parte)
        botonConfirmarCambios = findViewById(R.id.btn_confirmar_cambios)

        // Inicialmente deshabilitar campos de edición
        deshabilitarCampos()

        botonVerificar.setOnClickListener {
            buscarPartePorId()
        }

        botonConfirmarCambios.setOnClickListener {
            actualizarParte()
        }
    }

    private fun buscarPartePorId() {
        val idAuto = etIdAuto.text.toString().toIntOrNull()
        val idParte = etIdParte.text.toString().toIntOrNull()

        if (idAuto != null && idParte != null) {
            val parte = EBaseDeDatosPartes.tablaParte?.consultarPartePorID(idParte)
            if (parte != null && parte.id_autoUnico == idAuto) {
                mostrarSnackbar("Parte encontrada")
                // Habilitar campos para edición
                habilitarCampos()

                // Llenar los campos con los datos de la parte
                etNombre.setText(parte.nombre)
                etNumeroSerie.setText(parte.numero_serie)
                etFechaFabricacion.setText(parte.fecha_fabricacion)
                etPrecioParte.setText(parte.precio_parte.toString())
            } else {
                mostrarSnackbar("Parte no encontrada o no coincide con el auto")
                deshabilitarCampos()
                limpiarCampos()
            }
        } else {
            mostrarSnackbar("Por favor, ingrese identificadores válidos")
        }
    }

    private fun actualizarParte() {
        val idParte = etIdParte.text.toString().toIntOrNull()
        val idAuto = etIdAuto.text.toString().toIntOrNull()
        val nombre = etNombre.text.toString()
        val numeroSerie = etNumeroSerie.text.toString()
        val fechaFabricacion = etFechaFabricacion.text.toString()
        val precioParte = etPrecioParte.text.toString().toDoubleOrNull()

        if (idParte != null && idAuto != null && nombre.isNotEmpty() && numeroSerie.isNotEmpty() && fechaFabricacion.isNotEmpty() && precioParte != null) {
            val resultado = EBaseDeDatosPartes.tablaParte?.actualizarParteFormulario(
                idParte,
                idAuto,
                nombre,
                numeroSerie,
                fechaFabricacion,
                precioParte
            )
            if (resultado == true) {
                mostrarSnackbar("La parte se ha actualizado correctamente")
            } else {
                mostrarSnackbar("Error al actualizar la parte")
            }
        } else {
            mostrarSnackbar("Por favor, complete todos los campos correctamente")
        }
    }

    private fun mostrarSnackbar(mensaje: String) {
        Snackbar.make(findViewById(R.id.btn_confirmar_cambios), mensaje, Snackbar.LENGTH_LONG).show()
    }

    private fun habilitarCampos() {
        etNombre.isEnabled = true
        etNumeroSerie.isEnabled = true
        etFechaFabricacion.isEnabled = true
        etPrecioParte.isEnabled = true
        botonConfirmarCambios.isEnabled = true
    }

    private fun deshabilitarCampos() {
        etNombre.isEnabled = false
        etNumeroSerie.isEnabled = false
        etFechaFabricacion.isEnabled = false
        etPrecioParte.isEnabled = false
        botonConfirmarCambios.isEnabled = false
    }

    private fun limpiarCampos() {
        etNombre.setText("")
        etNumeroSerie.setText("")
        etFechaFabricacion.setText("")
        etPrecioParte.setText("")
    }
}