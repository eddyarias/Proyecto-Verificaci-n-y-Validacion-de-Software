package com.example.a05_finalproject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class HEliminarParte : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heliminar_parte)

        val botonEliminar = findViewById<Button>(R.id.btn_eliminar_parte)
        botonEliminar.setOnClickListener {
            eliminarParte()
        }
    }

    private fun eliminarParte() {
        val etIdAuto = findViewById<EditText>(R.id.et_id_auto)
        val etIdParte = findViewById<EditText>(R.id.et_id_parte)
        val idAuto = etIdAuto.text.toString().toIntOrNull()
        val idParte = etIdParte.text.toString().toIntOrNull()

        if (idAuto != null && idParte != null) {
            val parte = EBaseDeDatosPartes.tablaParte?.consultarPartePorID(idParte)
            if (parte != null && parte.id_autoUnico == idAuto) {
                val resultado = EBaseDeDatosPartes.tablaParte?.eliminarParteFormulario(idParte)
                if (resultado == true) {
                    mostrarSnackbar("La parte se ha eliminado correctamente")
                    limpiarFormulario()
                } else {
                    mostrarSnackbar("Error al eliminar la parte")
                }
            } else {
                mostrarSnackbar("No se encontró una parte con ese identificador o no coincide con el auto")
            }
        } else {
            mostrarSnackbar("Por favor, ingrese identificadores válidos")
        }
    }

    private fun mostrarSnackbar(mensaje: String) {
        Snackbar.make(findViewById(R.id.btn_eliminar_parte), mensaje, Snackbar.LENGTH_LONG).show()
    }

    private fun limpiarFormulario() {
        findViewById<EditText>(R.id.et_id_auto).setText("")
        findViewById<EditText>(R.id.et_id_parte).setText("")
    }
}