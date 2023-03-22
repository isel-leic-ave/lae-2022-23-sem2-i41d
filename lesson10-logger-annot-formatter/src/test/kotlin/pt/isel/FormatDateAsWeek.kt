package pt.isel

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FormatDateAsWeek : Formatter {
    override fun format(v: Any?): String {
        require(v is LocalDate) { "Illegal use with non LocalDate value!" }
        return v.format(DateTimeFormatter.ISO_WEEK_DATE)
    }
}
