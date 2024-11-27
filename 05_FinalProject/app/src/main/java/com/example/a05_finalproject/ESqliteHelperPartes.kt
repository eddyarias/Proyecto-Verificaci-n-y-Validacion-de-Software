package com.example.a05_finalproject

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperPartes(
    contexto: Context? // this
) : SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        // Crear la tabla PARTE
        val scriptSQLCrearTablaParte =
            """
                CREATE TABLE PARTE(
                    id_parte INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_autoUnico INTEGER,
                    nombre VARCHAR(50),
                    numero_serie VARCHAR(50),
                    fecha_fabricacion VARCHAR(50),
                    precio_parte DOUBLE
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaParte)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}

    fun crearParte(
        id_autoUnico: Int,
        nombre: String,
        numero_serie: String,
        fecha_fabricacion: String,
        precio_parte: Double
    ): Boolean {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("id_autoUnico", id_autoUnico)
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("numero_serie", numero_serie)
        valoresAGuardar.put("fecha_fabricacion", fecha_fabricacion)
        valoresAGuardar.put("precio_parte", precio_parte)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "PARTE", // nombre tabla
                null,
                valoresAGuardar // valores
            )
        basedatosEscritura.close()
        return if (resultadoGuardar.toInt() == -1) false else true
    }

    fun eliminarParteFormulario(id_parte: Int): Boolean {
        val conexionEscritura = writableDatabase
        val parametrosConsultaDelete = arrayOf(id_parte.toString())
        val resultadoEliminacion = conexionEscritura.delete(
            "PARTE",
            "id_parte=?",
            parametrosConsultaDelete
        )
        conexionEscritura.close()
        return resultadoEliminacion != -1
    }

    fun actualizarParteFormulario(
        id_parte: Int,
        id_auto: Int,
        nombre: String,
        numero_serie: String,
        fecha_fabricacion: String,
        precio: Double
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues().apply {
            put("id_auto", id_auto)
            put("nombre", nombre)
            put("numero_serie", numero_serie)
            put("fecha_fabricacion", fecha_fabricacion)
            put("precio", precio)
        }
        val parametrosConsultaActualizar = arrayOf(id_parte.toString())
        val resultadoActualizacion = conexionEscritura.update(
            "PARTE",
            valoresAActualizar,
            "id_parte=?",
            parametrosConsultaActualizar
        )
        conexionEscritura.close()
        return resultadoActualizacion != -1
    }

    fun consultarPartePorID(id_parte: Int): BPartes? {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM PARTE WHERE id_parte = ?
        """.trimIndent()
        val arregloParametrosConsultaLectura = arrayOf(id_parte.toString())
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura,
            arregloParametrosConsultaLectura
        )

        var parte: BPartes? = null
        if (resultadoConsultaLectura.moveToFirst()) {
            parte = BPartes(
                resultadoConsultaLectura.getInt(0),
                resultadoConsultaLectura.getInt(1),
                resultadoConsultaLectura.getString(2),
                resultadoConsultaLectura.getString(3),
                resultadoConsultaLectura.getString(4),
                resultadoConsultaLectura.getDouble(5)
            )
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return parte
    }

    fun consultarPartesPorAutoID(id_autoUnico: Int): List<BPartes> {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = "SELECT * FROM PARTE WHERE id_auto = ?"
        val parametrosConsulta = arrayOf(id_autoUnico.toString())

        val resultadoConsultaLectura =
            baseDatosLectura.rawQuery(scriptConsultaLectura, parametrosConsulta)

        val listaPartes = mutableListOf<BPartes>()
        if (resultadoConsultaLectura.moveToFirst()) {
            do {
                val parte = BPartes(
                    resultadoConsultaLectura.getInt(0),     // id_parte
                    resultadoConsultaLectura.getInt(1),     // id_autoUnico
                    resultadoConsultaLectura.getString(2),  // nombre
                    resultadoConsultaLectura.getString(3),  // numero_serie
                    resultadoConsultaLectura.getString(4),  // fecha_fabricacion
                    resultadoConsultaLectura.getDouble(5)   // precio_parte
                )
                listaPartes.add(parte)
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return listaPartes
    }
}