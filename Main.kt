fun main() {
    val menu = mutableListOf(
        Pair("Піца", 180),
        Pair("Бургер", 120),
        Pair("Суші", 250),
        Pair("Паста", 150),
        Pair("Салат", 90)
    )

    var total = 0

    println("Меню доставки:")
    for ((index, item) in menu.withIndex()) {
        val (name, price) = item
        println("${index + 1}. $name — $price грн")
    }
    println("0. Завершити замовлення")

    while (true) {
        try {
            print("Ваш вибір (1-5, або 0 для завершення): ")
            val choice = readln().toIntOrNull() ?: 0

            if (choice == 0) break

            if (choice !in 1 .. menu.size) throw InvalidMenuChoiceException(choice)

            val (name, price) = menu[choice - 1]
            println("Ви обрали: $name за $price грн")
            total += price
        } catch (e: NumberFormatException) {
            println("Помилка: введено не число.")
        } catch (e: InvalidMenuChoiceException) {
            println("Помилка: ${e.message}")
        }
    }

    println("\nСума до сплати: $total грн")

    val discount = when {
        total >= 500 -> 0.15
        total >= 200 -> 0.10
        else -> 0.0
    }

    val finalTotal = total - (total * discount)
    println("Знижка: ${discount * 100}%")
    println("Сума після знижки: %.2f грн".format(finalTotal))
}

// Власний виняток
class InvalidMenuChoiceException(choice: Int) : Exception("Невірний вибір пункту меню: $choice")
