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
import com.google.android.material.snackbar.Snackbar

class HVerParte : AppCompatActivity() {

    private lateinit var tablaPartes: TableLayout
    private lateinit var inputIdAuto: EditText
    private lateinit var inputIdParte: EditText
    private lateinit var botonBuscar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hver_parte)

        tablaPartes = findViewById(R.id.table_partes)
        inputIdAuto = findViewById(R.id.et_id_auto)
        inputIdParte = findViewById(R.id.et_id_parte)
        botonBuscar = findViewById(R.id.btn_buscar)

        botonBuscar.setOnClickListener {
            buscarParte()
        }
    }

    private fun buscarParte() {
        val idAuto = inputIdAuto.text.toString().toIntOrNull()
        val idParte = inputIdParte.text.toString().toIntOrNull()

        val listaPartes = when {
            idAuto != null && idParte != null -> {
                EBaseDeDatosPartes.tablaParte?.consultarPartePorID(idParte)?.let { listOf(it) } ?: emptyList()
            }
            idAuto != null -> {
                EBaseDeDatosPartes.tablaParte?.consultarPartesPorAutoID(idAuto) ?: emptyList()
            }
            else -> emptyList()
        }

        if (listaPartes.isNotEmpty()) {
            actualizarTablaPartes(listaPartes)
        } else {
            mostrarSnackbar("Parte no encontrada")
            limpiarTabla()
        }
    }

    private fun actualizarTablaPartes(listaPartes: List<BPartes>) {
        limpiarTabla()

        // Agregar encabezados
        val filaEncabezado = TableRow(this)
        val encabezados = listOf("ID Parte", "ID Auto", "Nombre", "Num Serie", "Fecha Fab.", "Precio")
        for (encabezado in encabezados) {
            val columnaEncabezado = TextView(this)
            columnaEncabezado.text = encabezado
            columnaEncabezado.setPadding(16, 16, 16, 16)
            columnaEncabezado.setBackgroundColor(Color.LTGRAY)
            columnaEncabezado.setTextColor(Color.BLACK)
            columnaEncabezado.setTypeface(null, Typeface.BOLD)
            filaEncabezado.addView(columnaEncabezado)
        }
        tablaPartes.addView(filaEncabezado)

        // Agregar datos
        for (parte in listaPartes) {
            val fila = TableRow(this)

            val columnaIdParte = TextView(this)
            columnaIdParte.text = parte.id_parte.toString()
            columnaIdParte.setPadding(16, 16, 16, 16)
            columnaIdParte.setTextColor(Color.BLACK)
            fila.addView(columnaIdParte)

            val columnaIdAuto = TextView(this)
            columnaIdAuto.text = parte.id_autoUnico.toString()
            columnaIdAuto.setPadding(16, 16, 16, 16)
            columnaIdAuto.setTextColor(Color.BLACK)
            fila.addView(columnaIdAuto)

            val columnaNombre = TextView(this)
            columnaNombre.text = parte.nombre
            columnaNombre.setPadding(16, 16, 16, 16)
            columnaNombre.setTextColor(Color.BLACK)
            fila.addView(columnaNombre)

            val columnaNumSerie = TextView(this)
            columnaNumSerie.text = parte.numero_serie
            columnaNumSerie.setPadding(16, 16, 16, 16)
            columnaNumSerie.setTextColor(Color.BLACK)
            fila.addView(columnaNumSerie)

            val columnaFechaFab = TextView(this)
            columnaFechaFab.text = parte.fecha_fabricacion
            columnaFechaFab.setPadding(16, 16, 16, 16)
            columnaFechaFab.setTextColor(Color.BLACK)
            fila.addView(columnaFechaFab)

            val columnaPrecio = TextView(this)
            columnaPrecio.text = parte.precio_parte.toString()
            columnaPrecio.setPadding(16, 16, 16, 16)
            columnaPrecio.setTextColor(Color.BLACK)
            fila.addView(columnaPrecio)

            fila.setBackgroundColor(Color.parseColor("#F5F5F5"))
            tablaPartes.addView(fila)
        }
    }

    private fun limpiarTabla() {
        tablaPartes.removeAllViews()
    }

    private fun mostrarSnackbar(mensaje: String) {
        Snackbar.make(findViewById(R.id.btn_buscar), mensaje, Snackbar.LENGTH_LONG).show()
    }
}