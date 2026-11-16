import java.net.HttpURLConnection
import java.net.URI

fun main() {
    val urlString = "https://jsonkeeper.com/b/MX0A"
    
    try {
        println("Conectando a la API de e-commerce...")
        val url = URI(urlString).toURL()
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"

        val responseCode = connection.responseCode
        if (responseCode == 200) {
            val response = connection.inputStream.bufferedReader().use { it.readText() }
            
            println("\n=== RESULTADO OBTENIDO DE LA API (Consola) ===")
            println(response)
        } else {
            println("Error en la conexión. Código de estado: $responseCode")
        }
    } catch (e: Exception) {
        println("Ocurrió un error al realizar la petición: ${e.message}")
    }
}