package pt.isel

import org.junit.jupiter.api.Test
import org.objectweb.asm.ClassReader
import org.objectweb.asm.util.TraceClassVisitor
import java.io.PrintWriter

class LoggerDynamicBytecodeTest {

    @Test
    fun `test logging properties of Student`() {
        val bytes = LoggerDynamic.buildLoggerDynamicForProperties(Student::class.java).finishBytes()
        printBytecodes(bytes)

    }

    fun printBytecodes(bytes: ByteArray?) {
        val reader = ClassReader(bytes)
        val tcv = TraceClassVisitor(PrintWriter(System.out))
        reader.accept(tcv, 0)
    }
}