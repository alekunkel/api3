data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val currency: String,
    val inStock: Boolean
)

class ProductCatalogPresenter {
    fun buildSimpleProductListDisplay(products: List<Product>): String? {
        if (products.isEmpty()) {
            return null
        }

        return products.joinToString(separator = "\n") { product ->
            val stockStatus = if (product.inStock) "Disponible" else "Sin stock"
            "- ${product.name} | \$${product.price} ${product.currency} ($stockStatus)"
        }
    }
}

class ProductCatalogUnitTests {
    private val presenter = ProductCatalogPresenter()

    private val sampleProducts = listOf(
        Product(
            id = 1,
            name = "iPhone 13",
            description = "The latest iPhone from Apple",
            price = 999.99,
            currency = "USD",
            inStock = true
        ),
        Product(
            id = 2,
            name = "Samsung Galaxy S21",
            description = "The latest Samsung phone",
            price = 899.99,
            currency = "USD",
            inStock = true
        ),
        Product(
            id = 3,
            name = "Google Pixel 6",
            description = "The latest Google phone",
            price = 799.99,
            currency = "USD",
            inStock = false
        )
    )

    fun testGivenProductsList_shouldShowInformationOnScreen(): Boolean {
        val screenOutput = presenter.buildSimpleProductListDisplay(sampleProducts)

        check(screenOutput != null) { "Se esperaba mostrar información en pantalla, pero fue null." }
        check(screenOutput.isNotBlank()) { "Se esperaba texto no vacío en pantalla." }
        check(screenOutput.contains("iPhone 13") && screenOutput.contains("999.99")) {
            "Faltan datos del iPhone 13 en el listado."
        }
        check(screenOutput.contains("Samsung Galaxy S21") && screenOutput.contains("899.99")) {
            "Faltan datos del Samsung Galaxy S21 en el listado."
        }
        check(screenOutput.contains("Google Pixel 6") && screenOutput.contains("799.99")) {
            "Faltan datos del Google Pixel 6 en el listado."
        }

        println("[PASS] Test 1: Dado el listado de productos, la información se muestra en pantalla.")
        println(screenOutput)
        return true
    }

    // Test 2: Cuando el listado esté vacío, validar que no se pueda mostrar información en pantalla
    fun testWhenListIsEmpty_shouldNotShowInformationOnScreen(): Boolean {
        val emptyProducts = emptyList<Product>()
        val screenOutput = presenter.buildSimpleProductListDisplay(emptyProducts)

        check(screenOutput == null) {
            "No debería mostrarse información en pantalla cuando el listado está vacío, pero se obtuvo: $screenOutput"
        }

        println("[PASS] Test 2: Cuando el listado está vacío, no se muestra información en pantalla.")
        return true
    }
}

fun main() {
    val tests = ProductCatalogUnitTests()
    println("=== Ejecutando Test Unitarios - Actividad 4 ===\n")

    val test1Passed = tests.testGivenProductsList_shouldShowInformationOnScreen()
    println()
    val test2Passed = tests.testWhenListIsEmpty_shouldNotShowInformationOnScreen()

    println("\n=== Resultado final: ${if (test1Passed && test2Passed) "2/2 TESTS PASARON" else "HAY FALLOS"} ===")
}