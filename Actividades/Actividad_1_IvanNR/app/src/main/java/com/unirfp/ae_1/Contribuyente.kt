package com.unirfp.ae_1

import java.io.Serializable

data class Contribuyente(
    val salarioBruto: Double,
    val numeroPagas: Int,
    val edad: Int,
    val grupoProfesional: String,
    val gradoInvalidez: Double,
    val estadoCivil: String,
    val numeroHijos: Int
) : Serializable
