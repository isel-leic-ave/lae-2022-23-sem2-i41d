package pt.isel

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

public class LoggerDynamicBytecodeTest {
    @Test
    fun `test logging properties of Student`() {
        LoggerDynamic.buildLoggerPropertyAndPrintBytecodes(Student::class.java)
    }
}