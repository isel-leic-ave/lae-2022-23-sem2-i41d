class Student(
    val nr: Int, // => Field + 1 fun
    var name: String // => Field + 2 fun
) {
    val nameLength get() = name.length // => 1 fun
    fun print() {
        println("$nr, $name")
    }
}