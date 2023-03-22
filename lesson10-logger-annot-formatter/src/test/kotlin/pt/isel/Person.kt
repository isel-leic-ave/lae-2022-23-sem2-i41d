package pt.isel

import java.time.LocalDate

class Person(
    val id: Int,
    val name: String,
    @Format(format = FormatStringToUpper::class) val address: String,
    @Format(format = FormatDateAsWeek::class) val birth: LocalDate)
