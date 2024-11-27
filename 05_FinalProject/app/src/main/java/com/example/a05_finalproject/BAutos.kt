package com.example.a05_finalproject

import android.os.Parcel
import android.os.Parcelable

class BAutos (
    var id_auto:Int,
    var marca:String,
    var anio:Int,
    var precio:Double,
    var electrico:String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return "$marca $anio $precio ${electrico}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id_auto)
        parcel.writeString(marca)
        parcel.writeInt(anio)
        parcel.writeDouble(precio)
        parcel.writeString(electrico)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BAutos> {
        override fun createFromParcel(parcel: Parcel): BAutos {
            return BAutos(parcel)
        }

        override fun newArray(size: Int): Array<BAutos?> {
            return arrayOfNulls(size)
        }
    }

}