class Person(val nr: Int, val name: String) {
    companion object {
        private var personsCount: Int = 0
        fun count() : Int {
            return personsCount;
        }
    }
    init {
        Person.personsCount++
    }
}