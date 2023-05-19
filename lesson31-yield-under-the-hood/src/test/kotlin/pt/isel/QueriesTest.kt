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

}

