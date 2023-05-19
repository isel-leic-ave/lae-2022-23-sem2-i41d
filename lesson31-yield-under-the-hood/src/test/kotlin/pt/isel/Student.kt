package pt.isel

data class Student(
    val classroom: String,
    val name: String,
    val nr: Int
)

fun String.toStudent(): Student {
    val words = this.split(";")
    require( words.size == 3) { "String wrong format. Should contain 3 words with ;" }
    val nr = words[1].toInt()
    return Student(words[0], words[2], nr)
}