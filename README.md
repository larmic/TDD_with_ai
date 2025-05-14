# TDD with AI

This project demonstrates Test-Driven Development (TDD) using AI, specifically JetBrains Junie. The goal is to define requirements in a natural language format (like Gherkin) and then have the AI generate the production code based on those requirements.

## Process

1. Write requirements in text form (e.g., using Gherkin syntax) that define the expected behavior
2. Generate test code from these requirements if necessary, or ideally execute tests directly from this syntax
3. Include a prompt for the AI
4. Have the AI generate the production code
5. Verify that the requirements are met with the generated code

## Example: StringParser

### Requirements

The `StringParser` class should provide functionality to parse and analyze strings. The initial requirement is to implement a method that counts words in a given text with the following specifications:

- Empty strings should return a word count of 0
- A single word should return a count of 1
- Multiple words separated by spaces should be counted correctly
- Multiple consecutive spaces should be treated as a single separator
- The literal "\n" string should be treated as a word separator
- Numbers should be counted as words

### Gherkin Example

```gherkin
Feature: Word Counter

  Scenario: Counting words in different types of text
    Given a StringParser
    When I count words in an empty string
    Then the count should be 0

    When I count words in "Hello"
    Then the count should be 1

    When I count words in "One two three"
    Then the count should be 3

    When I count words in "Hello     World"
    Then the count should be 2

    When I count words in "Hello\nWorld"
    Then the count should be 2

    When I count words in "Hello 123 World"
    Then the count should be 3
```

### Test (Generated from Requirements)

```kotlin
// prompt: Implement StringParser#countWords method.
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
```

### AI-Generated Implementation

```kotlin
class StringParser {
    fun countWords(text: String): Int {
        if (text.isEmpty()) return 0
        // Replace literal "\n" with a space before splitting
        val processedText = text.replace("\\n", " ")
        return processedText.split("\\s+".toRegex()).filter { it.isNotEmpty() }.size
    }
}
```

### Results

All tests pass successfully with the AI-generated implementation. The AI correctly:

1. Handled the empty string case
2. Replaced the literal "\n" with a space
3. Used regex to split by whitespace and handle multiple spaces
4. Filtered out empty strings
5. Counted the remaining elements

## Benefits of TDD with AI

- Faster development cycle
- Ensures code meets requirements from the start
- Reduces the need for manual implementation
- Maintains high test coverage
- Allows developers to focus on defining behavior rather than implementation details

## Future Work

Additional string parsing functionality can be added following the same TDD with AI approach:
- String validation
- Pattern matching
- Text transformation
- More complex parsing operations
