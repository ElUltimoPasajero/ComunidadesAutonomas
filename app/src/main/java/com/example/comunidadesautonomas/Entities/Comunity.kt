package com.example.comunidadesautonomas.Entities

/**
 * Clase Comunity para representar la Comunidad
 */

data class Comunity(
    val flag: Int,
    var name: String,
    var capital: String,
    var inhabitants: Int,
    var latitude: Double,
    var longitude: Double,
    val icon: Int
) {


}
