
/**AGENDA*/
/**
 * Asignamos lo que es la agenda en sí. Que consiste en un diccionario (map) cuyo valor es una lista de valores.
 *
 */
val agenda = mutableMapOf(
    "Laura" to listOf("Iglesias", "liglesias@gmail.com", "666777333", "666888555","607889988"),
    "Antonio" to listOf("Amargo", "aamargo@gmail.com"),
    "Marta" to listOf("Copete", "marcopete@gmail.com", "+34600888800"),
    "Rafael" to listOf("Ciruelo", "rciruelo@gmail.com", "+34607212121", "655001122"),
    "Daniela" to listOf("Alba", "danalba@gmail.com", "+34600606060", "+34670898934"),
    "Rogelio" to listOf("Rojo", "rogrojo@gmail.com", "610000099", "645000013"))

/**
 * La función addContact() pide una serie de variables con las que añadir un contacto a la agenda.
 */
fun addContact(){
       try {

        println("Ingrese el nombre: ")
        val nombre = readln()

        print("Ingrese el apellido: ")
        val apellido = readln()

        print("Ingrese el correo electrónico: ")
        val email = readln()

        print("Ingrese el teléfono: ")
        val telefono = readln()

        print("Ingrese el otro número de teléfono (opcional): ")
        val otroTelefono = readln()

        val nuevoContacto = listOf(apellido, email, telefono, otroTelefono).filter { it.isNotBlank() }

        agenda[nombre] = nuevoContacto


       }
       catch (e: Exception){
           println("*** ERROR *** Something unexpected happened. Error: $e.")
       }



}

/**
 *Esta función hace display de lo que sería el menú de la agenda.
 */
fun showMenu(){


    println("AGENDA")
    println("------")
    println("1. Nuevo contacto")
    println("2. Modificar contacto")
    println("3. Eliminar contacto")
    println("4. Vaciar agenda")
    println("5. Cargar agenda inicial")
    println("6. Mostrar contactos por criterio")
    println("7. Mostrar la agenda completa")
    println("8. Salir")
    println("")


}



/**
 * La función modContact() permite modificar los contactos ya agregados de la agenda.
 */
fun modContact(){

    try {

        println("Por favor, seleccione el contacto a modificar: ")
        val selectContacto = readln()

        val contactInfo = agenda[selectContacto]

        if (contactInfo != null){
            println("¡Contacto seleccionado con éxito! ¿Qué desea modificar?")

            println(agenda)

            println("Por favor, inserte de nuevo los datos del contacto: ")

            print("Ingrese el apellido: ")
            val nuevoApellido = readln()

            print("Ingrese el correo electrónico: ")
            val nuevoEmail = readln()

            print("Ingrese el teléfono: ")
            val nuevoTelefono = readln()

            print("Ingrese el otro número de teléfono (opcional): ")
            val nuevoOtroTelefono = readln()

            agenda[selectContacto] = listOfNotNull(nuevoApellido, nuevoEmail, nuevoTelefono, nuevoOtroTelefono)

            println("¡Contacto modificado con éxito!")
        }
    }
    catch (e: Exception){
        println("*** ERROR *** Error: $e.")
    }
}

/**
 * La función removeContact() permite eliminar un contacto ya agregado a la agenda.
 */

fun removeContact(){
    try {
        println("Por favor, seleccione el contacto a eliminar: ")
        val selectContacto = readln()

        if (agenda.containsKey(selectContacto)) {
            agenda.remove(selectContacto)
            println("¡Contacto eliminado con éxito!")
        } else {
            println("Contacto no encontrado.")
        }
    } catch (e: Exception) {
        println("*** ERROR *** Something unexpected happened. Error: $e.")
    }
}

/**
 * La función deleteAgenda() permite eliminar el contenido de la agenda.
 */
fun deleteAgenda(){
    try {
        agenda.clear()
        println("¡Agenda reseteada con éxito!")
    } catch (e: Exception) {
        println("*** ERROR *** Error: $e.")
    }
}

/**
 * La función showInitialAgenda() muestra el contenido de la agenda original.
 */
fun showInitialAgenda(){
    /**Esto he tenido que buscarlo porque no había encontrado forma de imprimirlo sin que Kotlin lo reconociera como nulo.*/

    agenda.forEach { (name, contactInfo) ->
        val infoNoNula = contactInfo.filterNotNull().map { it.toString() }
        println("$name: ${infoNoNula.joinToString(", ")}")
    }
}

/**
 * La función searchByCriteria() permite filtrar los contactos mediante un criterio y luego a través de un valor.
 */
fun searchByCriteria(){
    try {
        println("Introduzca el criterio de búsqueda (nombre, apellido o email): ")
        val criterio = readln().lowercase()

        if (criterio !in listOf("nombre", "apellido", "email")) {
            println("Ese no es un criterio de búsqueda válido.")
            return
        }

        println("Introduzca un valor de búsqueda: ")
        val valor = readln().lowercase()

        val contactosEncontrados = agenda.filter { (nombre, datos) ->
            when (criterio) {
                "nombre" -> valor in nombre.lowercase()

                "apellido" -> valor in datos[0].lowercase()

                "email" -> valor in datos[1].lowercase()

                else -> false
            }
        }

        if (contactosEncontrados.isNotEmpty()) {
            println("Contactos encontrados:")

            contactosEncontrados.forEach { (nombre, datos) ->
                println("Nombre: $nombre ${datos[0]} (${datos[1]})")

                if (datos.size > 2) {
                    val telefonos = datos.subList(2, datos.size).joinToString(" / ")
                    println("Teléfonos: $telefonos")
                }
                println("......")
            }
        } else {
            println("No se han encontrado contactos bajo ese criterio.")
        }
    } catch (e: Exception) {
        println("*** ERROR *** Error: $e.")
    }
}

/**
 * La función showFullAgenda() muestra por consola el estado actual de la agenda.
 */
fun showFullAgenda(){

    println("Agenda completa:")
    agenda.forEach { (name, contactInfo) ->
        val infoNoNula = contactInfo.filterNotNull().map { it.toString() }
        println("$name: ${infoNoNula.joinToString(", ")}")

    }
}


/**
 * La función chooseFromMenu() permite elegir las distintas opciones del menú de la agenda.
 */
fun chooseFromMenu(){
    try {

        while (true){
            val option = readln().toInt()



            when(option) {
                1 -> {
                    println("You chose: 1. Nuevo contacto.")

                    println("Por favor, inserte la información del contacto que quiera añadir: ")

                    addContact()

                    println("¡Contacto añadido con éxito!")

                    showMenu()

                }

                2 -> {
                    println("You chose: 2. Modificar contacto.")

                    modContact()

                    showMenu()

                }

                3 -> {
                    println("You chose: 3. Eliminar contacto.")

                    removeContact()

                    showMenu()

                }

                4 -> {
                    println("You chose: 4. Vaciar agenda.")

                    deleteAgenda()

                    showMenu()

                }

                5 -> {
                    println("You chose: 5. Cargar agenda inicial.")

                    showInitialAgenda()

                    showMenu()


                }

                6 -> {
                    println("You chose: 6. Mostrar contactos por criterio.")

                    searchByCriteria()

                    showMenu()

                }

                7 -> {
                    println("You chose: 7. Mostrar la agenda completa.")

                    showFullAgenda()


                    showMenu()

                }

                8 -> {
                    println("You chose: 8. Salir.")

                    println("Por favor, si necesita algo más no dude en elegir otra opción.")

                    break
                }

                else -> {
                    println("Opción no válida. Inténtelo de nuevo.")
                }

            }



        }
    }
    catch (e: NumberFormatException){
        println("*** ERROR *** Invalid numeric input. Error: $e.")
    }

}
fun main() {

    println("Bienvenidos al asistente de contactos, el Agendinator-3000. ¡Esperamos serles de mucha ayuda!")

    println("")

    showMenu()

    chooseFromMenu()
}

