class Person(val nr: Int, val name: String)

/**
 * ?? Onde foi parar esta fun print ????
 */
fun Person.print() {
    println("$nr: $name")
}