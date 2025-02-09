package com.github.italord.util

fun String.removeRepeatedWords(): String {
    val words = this.split("\\s+".toRegex())

    val uniqueWords = LinkedHashSet<String>()

    for (word in words) {
        uniqueWords.add(word)
    }

    return uniqueWords.joinToString(" ")
}