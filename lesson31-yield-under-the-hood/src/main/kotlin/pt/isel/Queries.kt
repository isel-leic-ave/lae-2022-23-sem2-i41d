package pt.isel

fun foo() = sequence {
    yield(8713)
    yield(5432)
    yield(7755)
}

fun main() {
    /*
    zas().forEach { println(it) }
     */
    val iter = zas().iterator()
    println(iter.next())
    println(iter.next())
}

fun zas() = chequence {  label ->
    when(label) {
        0 -> yield(7397423, 3)
        3 -> yield(234123, 5)
        5 -> yield(726361, 8)
    }
}

suspend fun bar() : Int {
    return 8736486
}