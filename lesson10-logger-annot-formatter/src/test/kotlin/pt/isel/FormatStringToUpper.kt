package pt.isel

class FormatStringToUpper : Formatter {
    override fun format(v: Any?): String {
        require(v is String) { "Illegal use of FormatStringToUpper with non String value!" }
        return v.uppercase()
    }

}
