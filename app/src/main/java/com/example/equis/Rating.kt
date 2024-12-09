package com.example.equis

import android.os.Parcel
import android.os.Parcelable

open class Rating(
    var idRating: Int,
    var idUsuario: Int,
    var fecha: String,
    var valor: Int,
    var comentario: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idRating)
        parcel.writeInt(idUsuario)
        parcel.writeString(fecha)
        parcel.writeInt(valor)
        parcel.writeString(comentario)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Rating> {
        override fun createFromParcel(parcel: Parcel): Rating {
            return Rating(parcel)
        }

        override fun newArray(size: Int): Array<Rating?> {
            return arrayOfNulls(size)
        }

        val listaRatings: MutableList<Rating> = mutableListOf()
    }
}