package pe.edu.ulima.pm.uset.Models

import pe.edu.ulima.pm.uset.firebase.FuncionesRandom

data class Usuario (
    var id: String = "",
    var nombres : String = "",
    var apellidos : String = "",
    var correo : String = "",
    var sexo : String = "",
    var ocupacion : String = "",
    var friend_code : String = FuncionesRandom.getRandomCode(),
    var settingsBasicos : Map<String,Boolean> = mapOf(
        "notifications" to true,
        "estadoActividad" to true),
    var settingsPrivacidadContactos : Map<String,Boolean> = mapOf(
        "nombre" to false,
        "apellidos" to false,
        "correo" to true,
        "sexo" to false,
        "ocupacion" to false
    ),
    var settingsPrivacidadAmigos : Map<String,Boolean> = mapOf(
        "nombre" to false,
        "apellidos" to false,
        "correo" to true,
        "sexo" to false,
        "ocupacion" to false
    )
)

