package pt.isel

data class Student(
    val classroom: String,
    val nr: Int,
    val name: String
)

fun String.toStudent(): Student {
    val words = this.split(";")
    require(words.size == 3) { "Each line should contain 3 words separated by ;" }
    val nr = words[1].toIntOrNull()
    require(nr != null) { "Second word should be a valid Int representing the Student number" }
    return Student(words[0], nr, words[2])
}