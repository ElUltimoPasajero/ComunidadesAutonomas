package com.example.comunidadesautonomas.Entities

/**
 * Clase Comunity para representar la Comunidad
 */

data class Comunity(
    val id: Int,
    val flag: Int,
    var name: String,
    var capital: String,
    var inhabitants: Int,
    var latitude: Double,
    var longitude: Double,
    var icon: Int,
    var uri: String

) {


}
