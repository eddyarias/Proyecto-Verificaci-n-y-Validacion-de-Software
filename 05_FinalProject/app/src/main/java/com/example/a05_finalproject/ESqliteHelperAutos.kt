package com.example.a05_finalproject

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperAutos(
    contexto: Context? // this
) : SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        // Crear una tabla
        val scriptSQLCrearTablaAuto =
            """
                CREATE TABLE AUTO(
                    id_auto INTEGER PRIMARY KEY AUTOINCREMENT,
                    marca VARCHAR(50),
                    anio INTEGER,
                    precio DOUBLE,
                    electrico VARCHAR(50)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaAuto)
    }

    override fun onUpgrade(
        p0: SQLiteDatabase?, p1: Int, p2: Int) {}

    fun crearAuto(
        marca: String,
        anio: Int,
        precio: Double,
        electrico: String?
    ): Boolean {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("marca", marca)
        valoresAGuardar.put("anio", anio)
        valoresAGuardar.put("precio", precio)
        valoresAGuardar.put("electrico", electrico)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "AUTO", // nombre tabla
                null,
                valoresAGuardar // valores
            )
        basedatosEscritura.close()
        return if(resultadoGuardar.toInt() == -1) false else true
    }

    fun eliminarAutoFormulario(id_auto: Int): Boolean {
        val conexionEscritura = writableDatabase
        val parametrosConsultaDelete = arrayOf(id_auto.toString())
        val resultadoEliminacion = conexionEscritura
            .delete(
                "AUTO",
                "id_auto=?",
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return if(resultadoEliminacion.toInt()==-1) false else true
    }

    fun actualizarAutoFormulario(
        id_auto: Int,
        marca: String,
        anio: Int,
        precio: Double,
        electrico: String?
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("marca", marca)
        valoresAActualizar.put("anio", anio)
        valoresAActualizar.put("precio", precio)
        valoresAActualizar.put("electrico", electrico)
        val parametrosConsultaActualizar = arrayOf(id_auto.toString())
        val resultadoActualizacion = conexionEscritura
            .update(
                "AUTO",
                valoresAActualizar,
                "id_auto=?",
                parametrosConsultaActualizar
            )
        conexionEscritura.close()
        return if (resultadoActualizacion.toInt()==-1) false else true
    }

    fun consultarAutoPorID(id_auto: Int): BAutos? {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM AUTO WHERE id_auto = ?
        """.trimIndent()
        val arregloParametrosConsultaLectura = arrayOf(
            id_auto.toString()
        )
        val resultadoConsultaLectura = baseDatosLectura
            .rawQuery(
                scriptConsultaLectura,
                arregloParametrosConsultaLectura
            )

        val existeAlMenosUno = resultadoConsultaLectura
            .moveToFirst()
        val arregloRespuesta = arrayListOf<BAutos>()
        if (existeAlMenosUno) {
            do {
                val auto = BAutos(
                    resultadoConsultaLectura.getInt(0),
                    resultadoConsultaLectura.getString(1),
                    resultadoConsultaLectura.getInt(2),
                    resultadoConsultaLectura.getDouble(3),
                    resultadoConsultaLectura.getString(4)
                )
                arregloRespuesta.add(auto)
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return if(arregloRespuesta.size > 0) arregloRespuesta[0] else null
    }

    fun consultarTodosLosAutos(): List<BAutos> {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = "SELECT * FROM AUTO"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(scriptConsultaLectura, null)

        val listaAutos = mutableListOf<BAutos>()
        if (resultadoConsultaLectura.moveToFirst()) {
            do {
                val auto = BAutos(
                    resultadoConsultaLectura.getInt(0),     // id_auto
                    resultadoConsultaLectura.getString(1),  // marca
                    resultadoConsultaLectura.getInt(2),     // anio
                    resultadoConsultaLectura.getDouble(3),  // precio
                    resultadoConsultaLectura.getString(4)   // electrico
                )
                listaAutos.add(auto)
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return listaAutos
    }
}
