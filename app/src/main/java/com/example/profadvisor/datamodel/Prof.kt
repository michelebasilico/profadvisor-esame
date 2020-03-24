package com.example.profadvisor.datamodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Prof(var Email: String, var Materia: String, var Nome: String, var Rating: Float) : Parcelable{
    constructor() : this ("","","",0f)
}