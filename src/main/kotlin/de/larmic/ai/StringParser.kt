package de.larmic.ai

class StringParser {
    fun countWords(text: String): Int {
        if (text.isEmpty()) return 0
        // Replace literal "\n" with a space before splitting
        val processedText = text.replace("\\n", " ")
        return processedText.split("\\s+".toRegex()).filter { it.isNotEmpty() }.size
    }
}
