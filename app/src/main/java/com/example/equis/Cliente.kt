package com.example.equis

import android.os.Parcel
import android.os.Parcelable

class Cliente(
    id: Int,
    nombre: String,
    contrasena: String,
    email: String,
    telefono: Int,
    tipoUsuario: String,
    var fechaInicioMembresia: String,
    var fechaFinMembresiia : String,
    var asistenciasRegistradas : Int,
    var planMembresia : String
) : Usuario(id, nombre, contrasena, email, telefono, tipoUsuario), Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt() ?: 0,
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt() ?: 0,
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt()  ?: 0,
        parcel.readString() ?: ""
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
        parcel.writeString(fechaInicioMembresia)
        parcel.writeString(fechaFinMembresiia)
        parcel.writeInt(asistenciasRegistradas)
        parcel.writeString(planMembresia)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cliente> {
        override fun createFromParcel(parcel: Parcel): Cliente {
            return Cliente(parcel)
        }

        override fun newArray(size: Int): Array<Cliente?> {
            return arrayOfNulls(size)
        }
    }
}