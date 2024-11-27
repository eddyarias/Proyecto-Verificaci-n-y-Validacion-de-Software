package com.example.a05_finalproject

import android.os.Parcel
import android.os.Parcelable

class BPartes (
    var id_parte: Int,
    var id_autoUnico: Int,  // Cambia id_autoUnico a id_auto
    var nombre: String,
    var numero_serie: String?,
    var fecha_fabricacion: String?,
    var precio_parte: Double  // Cambia precio_parte a precio
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble()
    )

    override fun toString(): String {
        return "$id_autoUnico $nombre $numero_serie $fecha_fabricacion $precio_parte"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id_parte)
        parcel.writeInt(id_autoUnico)
        parcel.writeString(nombre)
        parcel.writeString(numero_serie)
        parcel.writeString(fecha_fabricacion)
        parcel.writeDouble(precio_parte)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BPartes> {
        override fun createFromParcel(parcel: Parcel): BPartes {
            return BPartes(parcel)
        }

        override fun newArray(size: Int): Array<BPartes?> {
            return arrayOfNulls(size)
        }
    }
}