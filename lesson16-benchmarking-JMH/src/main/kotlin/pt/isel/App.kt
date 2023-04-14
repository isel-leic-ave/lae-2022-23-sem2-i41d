package pt.isel;

import kotlin.system.measureTimeMillis

fun main() {
    val emptyPrinter = object : Printer {
        override fun print(msg: Any?) {
            // Do nothing
        }
    }
    val logger = Logger(emptyPrinter, MemberKind.FIELD)
    val baseline = LoggerBaselineStudent(emptyPrinter);

    val s = Student(3414, "Ze Manel")

    println("###### Logger Reflect")
    for (i in 1..10)
        measureTimeMillis {
            for(i in 1..10_000_000)
                logger.log(s)
        }.let {
            println("Logger Reflect log() took $it millis")
        }
    println("###### Baseline")
    for (i in 1..10)
        measureTimeMillis {
            for(i in 1..10_000_000)
                baseline.log(s)
        }.let {
            println("Logger Baseline log() took $it millis")
        }


}