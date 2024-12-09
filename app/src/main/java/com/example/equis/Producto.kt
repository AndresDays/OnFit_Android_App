package com.example.equis

import android.os.Parcel
import android.os.Parcelable

open class Producto(
    var idProducto: Int = getNextId(),
    var nombre: String,
    var categoria: String,
    var precio: Double,
    var cantidadDisponible: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt() ?: 0,
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble() ?: 0.0,
        parcel.readInt() ?: 0
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idProducto)
        parcel.writeString(nombre)
        parcel.writeString(categoria)
        parcel.writeDouble(precio)
        parcel.writeInt(cantidadDisponible)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Producto> {
        private var currentId = 1
        val listaProductos = mutableListOf<Producto>()

        fun getNextId(): Int {
            return currentId++
        }

        override fun createFromParcel(parcel: Parcel): Producto {
            return Producto(parcel)
        }

        override fun newArray(size: Int): Array<Producto?> {
            return arrayOfNulls(size)
        }
    }
}