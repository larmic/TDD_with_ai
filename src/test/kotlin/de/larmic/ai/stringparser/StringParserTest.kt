package de.larmic.ai.stringparser

import de.larmic.ai.StringParser
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals

class StringParserTest {

    @ParameterizedTest
    @CsvSource(
        "'', 0",
        "Hallo, 1",
        "Hallo Welt, 2",
        "Hallo     Welt   , 2",
        "Hallo\\nWelt, 2",
        "Hallo Welt 1 2 3, 5",
    )
    fun `count words`(test: String, count: Int)  {
        assertEquals(count, StringParser().countWords(test))
    }
}