package com.iagodavit.tikray.screens




/* Función para poder calcular la longitud del progreso de la barra
teniendo en cuenta la longitud de la contraseña, tambien gestiona los colores
 */
fun progressBar(passwordText: String): List<Any> {
    var lista: MutableList<Any> = mutableListOf(0, 0, " ")

    var multiplicate: Double = passwordText.length.toDouble() * 0.05f
    lista[0] = multiplicate
    when (multiplicate) {
        in 0.09f..0.35f -> lista[1] = 1
        in 0.36f..0.55f -> lista[1] = 2
        in 0.56f..100f -> lista[1] = 3


    }
    return lista
}


fun fieldNotEmpty(fieldA:String, fieldB:String): Boolean {
    return if (fieldA.isEmpty() || fieldB.isEmpty()) {
        false
    }
    else {
        true
    }


}