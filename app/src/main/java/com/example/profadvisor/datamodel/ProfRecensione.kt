package com.example.profadvisor.datamodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ProfRecensione(var nomeUtente: String? = "",
                          var nomeDocente: String? = "",
                          var email: String? = "",
                          var materia: String? = "",
                          var recensione: String? = ""
                          , var rating: Float? = 0f,
                          var gentile: String? = "",
                          var disponibile: String? = "",
                          var severo: String? = "",
                          var divertente: String? = "",
                          var competente: String? = "",
                          var imparziale: String? = "",
                          var esigente: String? = "",
                          var socievole: String? = "",
                          var carismatico: String? = "",
                          var impegnativo: String? = "") : Parcelable