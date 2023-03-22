/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package pt.isel

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class LoggerTest {
    @Test fun `test logging fields of Student`() {
        val out = PrintBuffer()
        val logger = Logger(out, MemberKind.FIELD)
        val s = Student(1234124, "Ze Manel")
        logger.log(s)
        assertEquals(
            "Student: nr = 1234124, name = Ze Manel, " + System.lineSeparator(),
            out.buffer())
    }
    @Test fun `test logging properties of Point`() {
        val out = PrintBuffer()
        val logger = Logger(out, MemberKind.PROPERTY)
        val p = Point(4,7)
        logger.log(p)
        assertEquals(
            "Point: MODULE = 8.06225774829855, " + System.lineSeparator(),
            out.buffer())
    }
}
