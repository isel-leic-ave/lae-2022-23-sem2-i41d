package pt.isel

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class QueriesTest {

    @Test
    fun `Concat with operator +`() {
        val first = listOf("Portugal", "Football", "Teams")
        val other = listOf("SLB", "The", "Champion")
        val actual = first + other
        val expected = listOf("Portugal", "Football", "Teams", "SLB", "The", "Champion")
        assertEquals(expected, actual)
    }

    @Test
    fun `Concat with custom eager operator`() {
        val first = listOf("Portugal", "Football", "Teams")
        val other = listOf("SLB", "The", "Champion")
        val actual = first.concat(other)
        val expected = listOf("Portugal", "Football", "Teams", "SLB", "The", "Champion")
        assertEquals(expected, actual)
    }
    @Test
    fun `Concat with custom Lazy concat`() {
        val first = sequenceOf("Portugal", "Football", "Teams")
        val other = sequenceOf("SLB", "The", "Champion")
        val actual = first.concatLazy(other)
        val expected = listOf("Portugal", "Football", "Teams", "SLB", "The", "Champion")
        assertEquals(expected, actual.toList())
    }
    @Test
    fun `Concat with custom Lazy concat using yield`() {
        val first = sequenceOf("Portugal", "Football", "Teams")
        val other = sequenceOf("SLB", "The", "Champion")
        val actual = first.concat(other)
        val expected = listOf("Portugal", "Football", "Teams", "SLB", "The", "Champion")
        assertEquals(expected, actual.toList())
    }
    @Test
    fun `Collapse with custom Lazy collapse using yield`() {
        val actual: Sequence<Int?> = sequenceOf(4, 5, 5, 6, 7, 7, 5, 3, 3, 7, 6, 5)
            .collapse()
        val expected = listOf(4, 5, 6, 7, 5, 3, 7, 6, 5)
        assertEquals(expected, actual.toList())
    }







    @Test
    fun `Interleave with custom Lazy interleave using yield`() {
        val first = sequenceOf(2,5,3,6)
        val other = sequenceOf(9,5,8,3,7,1)
        val actual = first.interleave(other)
        val expected = listOf(2, 9, 5, 5, 3, 8, 6, 3, 7, 1)
        assertEquals(expected, actual.toList())
    }
    @Test
    fun `Window with custom Lazy window using yield`() {
        val actual: Iterator<Sequence<Int>> = sequenceOf(2,5,7,9,6,4,7,1)
            .window(3)
            .iterator()
        val expected = sequenceOf(
                listOf(2,5,7),
                listOf(9,6,4),
                listOf(7,1)
            )
            .iterator();
        while(expected.hasNext())
            assertEquals(expected.next(), actual.next().toList())
    }
}

