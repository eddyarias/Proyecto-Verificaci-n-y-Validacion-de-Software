package com.example.a05_finalproject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class FEditarAuto : AppCompatActivity() {

    private lateinit var etIdentificador: EditText
    private lateinit var etMarca: EditText
    private lateinit var etAnio: EditText
    private lateinit var etPrecio: EditText
    private lateinit var swElectrico: Switch
    private lateinit var botonVerificar: Button
    private lateinit var botonConfirmarCambios: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feditar_auto)

        etIdentificador = findViewById(R.id.et_identificador)
        etMarca = findViewById(R.id.et_marca)
        etAnio = findViewById(R.id.et_anio)
        etPrecio = findViewById(R.id.et_precio)
        swElectrico = findViewById(R.id.sw_electrico)
        botonVerificar = findViewById(R.id.btn_verificar_auto)
        botonConfirmarCambios = findViewById(R.id.btn_confirmar_cambios)

        // Inicialmente deshabilitar campos de edición
        deshabilitarCampos()

        botonVerificar.setOnClickListener {
            buscarAutoPorId()
        }

        botonConfirmarCambios.setOnClickListener {
            actualizarAuto()
        }
    }

    private fun buscarAutoPorId() {
        val idAuto = etIdentificador.text.toString().toIntOrNull()

        if (idAuto != null) {
            val auto = EBaseDeDatosAutos.tablaAuto?.consultarAutoPorID(idAuto)
            if (auto != null) {
                mostrarSnackbar("Auto encontrado")
                // Habilitar campos para edición
                habilitarCampos()

                // Llenar los campos con los datos del auto
                etMarca.setText(auto.marca)
                etAnio.setText(auto.anio.toString())
                etPrecio.setText(auto.precio.toString())
                swElectrico.isChecked = auto.electrico == "Sí"
            } else {
                mostrarSnackbar("Auto no encontrado")
                deshabilitarCampos()
                limpiarCampos()
            }
        } else {
            mostrarSnackbar("Por favor, ingrese un identificador válido")
        }
    }

    private fun actualizarAuto() {
        val idAuto = etIdentificador.text.toString().toIntOrNull()
        val marca = etMarca.text.toString()
        val anio = etAnio.text.toString().toIntOrNull()
        val precio = etPrecio.text.toString().toDoubleOrNull()
        val electrico = if (swElectrico.isChecked) "Sí" else "No"

        if (idAuto != null && marca.isNotEmpty() && anio != null && precio != null) {
            val resultado = EBaseDeDatosAutos.tablaAuto?.actualizarAutoFormulario(
                idAuto,
                marca,
                anio,
                precio,
                electrico
            )
            if (resultado == true) {
                mostrarSnackbar("El auto se ha actualizado correctamente")
            } else {
                mostrarSnackbar("Error al actualizar el auto")
            }
        } else {
            mostrarSnackbar("Por favor, complete todos los campos correctamente")
        }
    }

    private fun mostrarSnackbar(mensaje: String) {
        Snackbar.make(findViewById(R.id.btn_confirmar_cambios), mensaje, Snackbar.LENGTH_LONG).show()
    }

    private fun habilitarCampos() {
        etMarca.isEnabled = true
        etAnio.isEnabled = true
        etPrecio.isEnabled = true
        swElectrico.isEnabled = true
        botonConfirmarCambios.isEnabled = true
    }

    private fun deshabilitarCampos() {
        etMarca.isEnabled = false
        etAnio.isEnabled = false
        etPrecio.isEnabled = false
        swElectrico.isEnabled = false
        botonConfirmarCambios.isEnabled = false
    }

    private fun limpiarCampos() {
        etMarca.setText("")
        etAnio.setText("")
        etPrecio.setText("")
        swElectrico.isChecked = false
    }
}