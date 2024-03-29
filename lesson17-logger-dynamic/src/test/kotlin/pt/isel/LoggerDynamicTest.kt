/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package pt.isel

import kotlin.test.Test
import kotlin.test.assertEquals

class LoggerDynamicTest {
    @Test fun `test logging fields of Student`() {
        val out = PrintBuffer()
        val logger: AbstractLogger = LoggerDynamic.logger(Student::class.java, MemberKind.PROPERTY, out)
        val s = Student(1234124, "Ze Manel")
        logger.log(s)
        assertEquals(
            "Student: name = Ze Manel, nr = 1234124, " + System.lineSeparator(),
            out.buffer())
    }
}
