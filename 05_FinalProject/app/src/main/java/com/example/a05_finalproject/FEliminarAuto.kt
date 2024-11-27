package com.example.a05_finalproject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class FEliminarAuto : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feliminar_auto)

        val botonEliminar = findViewById<Button>(R.id.btn_eliminar)
        botonEliminar.setOnClickListener {
            eliminarAuto()
        }
    }

    private fun eliminarAuto() {
        val etIdentificador = findViewById<EditText>(R.id.et_identificador)
        val idAuto = etIdentificador.text.toString().toIntOrNull()

        if (idAuto != null) {
            val auto = EBaseDeDatosAutos.tablaAuto?.consultarAutoPorID(idAuto)
            if (auto != null) {
                val resultado = EBaseDeDatosAutos.tablaAuto?.eliminarAutoFormulario(idAuto)
                if (resultado == true) {
                    mostrarSnackbar("El auto se ha eliminado correctamente")
                    limpiarFormulario()
                } else {
                    mostrarSnackbar("Error al eliminar el auto")
                }
            } else {
                mostrarSnackbar("No se encontró un auto con ese identificador")
            }
        } else {
            mostrarSnackbar("Por favor, ingrese un identificador válido")
        }
    }

    private fun mostrarSnackbar(mensaje: String) {
        Snackbar.make(findViewById(R.id.btn_eliminar), mensaje, Snackbar.LENGTH_LONG).show()
    }

    private fun limpiarFormulario() {
        findViewById<EditText>(R.id.et_identificador).setText("")
    }
}
