
fun main(){
    val s = Student(83764, "Ze Manel")
    println(s.nr) // aceder prop nr
    s.print()     // aceder fun  print
} 

fun makeAndPrintPoint(x: Int, y: Int) {
    Point(x, y).print()
}