package pt.isel

import org.junit.jupiter.api.Test
import java.io.BufferedWriter
import java.io.Closeable
import java.io.File
import java.io.FileOutputStream
import java.lang.ref.Cleaner
import java.nio.channels.OverlappingFileLockException
import java.util.Random
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

fun loadStudents() : List<String>{
    val uriLae2022 = ClassLoader
        .getSystemClassLoader()
        .getResource("lae2022.txt")
        .toURI()
    return File(uriLae2022)
        .readLines()
}

fun randNumbers() : Sequence<Int> {
    return object : Sequence<Int> {
        override fun iterator(): Iterator<Int> {
            return object : Iterator<Int> {
                val rand = Random()
                override fun hasNext() = true
                override fun next() = rand.nextInt()
            }
        }
    }
}

fun randGenerator() : Sequence<Int> {
    val rand = Random()
    return sequence {
        while(true) yield(rand.nextInt())
    }
}

fun foo() = sequence<Int> {
    yield(7)
    yield(6)
    yield(5)
}

class QueriesTest {
    @Test fun fooTest() {
        val seq = foo()
        val iter = seq.iterator()
        val item1 = iter.next()
        assertEquals(7, item1)
        val item2 = iter.next()
        assertEquals(6, item2)
    }

    @Test fun `Select first 3 random numbers`() {
        randNumbers()
            .take(3)
            .forEach { println(it) }
    }

    @Test fun `With Collections select first Surname with A and number greater than 47000`() {
        var count = 0
        val name = loadStudents()
            .map { count++; it.toStudent() }
            .filter { count++; it.nr > 47000 }          // of a student with number greater than 47000 - - intermediate operation
            .map { count++; it.name.split(" ").last() } // ... surname ... - intermediate operation
            .filter { count++; it.startsWith("A") }     //... starting with letter A...  - - intermediate operation
            .first()                           // ... select the first... - terminal ope
        assertEquals("Almeida", name)
        assertEquals(356, count)
    }
    @Test fun `With Queries select first Surname with A and number greater than 47000`() {
        var count = 0
        val name = loadStudents()
            .convert { count++; it.toStudent() }
            .where { count++; it.nr > 47000 }          // of a student with number greater than 47000 - - intermediate operation
            .convert { count++; it.name.split(" ").last() } // ... surname ... - intermediate operation
            .where { count++; it.startsWith("A") }     //... starting with letter A...  - - intermediate operation
            .iterator()
            .next()                                    // ... select the first... - terminal ope
        assertEquals("Almeida", name)
        assertEquals(356, count)
    }

    @Test fun `With Sequences select first Surname with A and number greater than 47000`() {
        var count = 0
        val name = loadStudents()
            .asSequence()
            .map { count++; it.toStudent() }
            .filter { count++; it.nr > 47000 }          // of a student with number greater than 47000 - - intermediate operation
            .map { count++; it.name.split(" ").last() } // ... surname ... - intermediate operation
            .filter { count++; it.startsWith("A") }     //... starting with letter A...  - - intermediate operation
            .first()                           // ... select the first... - terminal ope
        assertEquals("Almeida", name)
        assertEquals(22, count)
    }

    @Test fun `With Queries Lazy select first Surname with A and number greater than 47000`() {
        var count = 0
        val name = loadStudents()
            .asSequence()
            .convert { count++; it.toStudent() }
            .where { count++; it.nr > 47000 }          // of a student with number greater than 47000 - - intermediate operation
            .convert { count++; it.name.split(" ").last() } // ... surname ... - intermediate operation
            .where { count++; it.startsWith("A") }     //... starting with letter A...  - - intermediate operation
            .iterator()                               // ... select the first... - terminal ope
            .next()
        assertEquals("Almeida", name)
        assertEquals(22, count)
    }

}
