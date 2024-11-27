package com.example.a05_finalproject

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar

class FVerAuto : AppCompatActivity() {

    private lateinit var tablaAutos: TableLayout
    private lateinit var inputIdentificador: EditText
    private lateinit var botonVerTodos: Button
    private lateinit var botonBuscar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fver_auto)

        tablaAutos = findViewById(R.id.table_autos)
        inputIdentificador = findViewById(R.id.et_identificador)
        botonVerTodos = findViewById(R.id.btn_ver_todos)
        botonBuscar = findViewById(R.id.btn_buscar)

        botonVerTodos.setOnClickListener {
            mostrarTodosLosAutos()
        }

        botonBuscar.setOnClickListener {
            buscarAutoPorId()
        }
    }

    private fun mostrarTodosLosAutos() {
        val listaAutos = EBaseDeDatosAutos.tablaAuto?.consultarTodosLosAutos() ?: emptyList()
        actualizarTablaAutos(listaAutos)
    }

    private fun buscarAutoPorId() {
        val idAuto = inputIdentificador.text.toString().toIntOrNull()
        if (idAuto != null) {
            val auto = EBaseDeDatosAutos.tablaAuto?.consultarAutoPorID(idAuto)
            if (auto != null) {
                actualizarTablaAutos(listOf(auto))
            } else {
                mostrarSnackbar("Auto no encontrado")
                limpiarTabla()
            }
        } else {
            mostrarSnackbar("Por favor, ingrese un identificador válido")
        }
    }

    private fun actualizarTablaAutos(listaAutos: List<BAutos>) {
        limpiarTabla()

        // Agregar encabezados
        val filaEncabezado = TableRow(this)
        val encabezados = listOf("ID Auto", "Marca", "Año", "Precio", "Eléctrico")
        for (encabezado in encabezados) {
            val columnaEncabezado = TextView(this)
            columnaEncabezado.text = encabezado
            columnaEncabezado.setPadding(16, 16, 16, 16)
            columnaEncabezado.setBackgroundColor(Color.LTGRAY)
            columnaEncabezado.setTextColor(Color.BLACK)
            columnaEncabezado.setTypeface(null, Typeface.BOLD)
            filaEncabezado.addView(columnaEncabezado)
        }
        tablaAutos.addView(filaEncabezado)

        // Agregar datos
        for (auto in listaAutos) {
            val fila = TableRow(this)

            val columnaId = TextView(this)
            columnaId.text = auto.id_auto.toString()
            columnaId.setPadding(16, 16, 16, 16)
            columnaId.setTextColor(Color.BLACK)
            fila.addView(columnaId)

            val columnaMarca = TextView(this)
            columnaMarca.text = auto.marca
            columnaMarca.setPadding(16, 16, 16, 16)
            columnaMarca.setTextColor(Color.BLACK)
            fila.addView(columnaMarca)

            val columnaAnio = TextView(this)
            columnaAnio.text = auto.anio.toString()
            columnaAnio.setPadding(16, 16, 16, 16)
            columnaAnio.setTextColor(Color.BLACK)
            fila.addView(columnaAnio)

            val columnaPrecio = TextView(this)
            columnaPrecio.text = auto.precio.toString()
            columnaPrecio.setPadding(16, 16, 16, 16)
            columnaPrecio.setTextColor(Color.BLACK)
            fila.addView(columnaPrecio)

            val columnaElectrico = TextView(this)
            columnaElectrico.text = auto.electrico
            columnaElectrico.setPadding(16, 16, 16, 16)
            columnaElectrico.setTextColor(Color.BLACK)
            fila.addView(columnaElectrico)

            fila.setBackgroundColor(Color.parseColor("#F5F5F5"))
            tablaAutos.addView(fila)
        }
    }

    private fun limpiarTabla() {
        tablaAutos.removeAllViews()
    }

    private fun mostrarSnackbar(mensaje: String) {
        Snackbar.make(findViewById(R.id.btn_buscar), mensaje, Snackbar.LENGTH_LONG).show()
    }
}
