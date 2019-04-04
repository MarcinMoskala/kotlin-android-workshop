fun a(f1: (Int) -> Unit = {}, f2: (Int) -> Unit = {}) {
    f1(1)
    f2(2)
}

fun main() {
    a(f1 = { print(it) })
    a(f2 = { print(it) })

    a(f1 = {}, f2 = {})
}