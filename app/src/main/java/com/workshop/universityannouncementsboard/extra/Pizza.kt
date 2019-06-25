class Pizza(
    val olives: Int = 0,
    val pineapple: Int = 0,
    val tomato: Int = 0,
    val mushrooms: Int = 0,
    val ham: Int = 0,
    val cheese: Int = 0
)

fun main() {
    val hawaiian = Pizza(pineapple = 1, ham = 1, cheese = 1)
}