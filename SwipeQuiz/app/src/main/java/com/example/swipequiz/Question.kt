package com.example.swipequiz

class Question(
    var text: String,
    var answer: Boolean
) {
    /**
     * companion is like a static type.
     * Allows you to have access to the object without having to create an instance.
     */
    companion object {
        val QUESTION_TEXTS = arrayOf(
            "A 'val' and 'var' are the same.",
            "Mobile Application Development grants 12 ECTS.",
            "A Unit in Kotlin corresponds to a void in Java.",
            "In Kotlin 'when' replaces the 'switch' operator in Java."
        )

        val QUESTION_ANSWERS = arrayOf(
            false,
            false,
            true,
            true
        )
    }
}
