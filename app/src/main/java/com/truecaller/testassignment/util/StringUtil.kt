package com.truecaller.testassignment.util

object StringUtil {

    // find 10th character
    fun findTenthCharacter(text: String): String {
        return text[9].toString()
    }


    // find every 10th character
    fun findEveryTenthCharacter(text: String): List<Char> {
        val arrayListResult = arrayListOf<Char>()
        for (i in 10..text.length) {
            if (i % 10 == 0) {
                // -1 is because index starts from 0 not 1
                arrayListResult.add(text[i - 1])
            }
        }
        return arrayListResult
    }

    // words counter
    fun findWordsFromString(text: String): List<String> {
        return text.split(" ").toList()
    }

    fun findOccurrenceOfEachWord(text: List<String>): Map<String, Int> {
        val occurrenceMap: MutableMap<String, Int> = HashMap()
        for (str in text) {
            if (occurrenceMap.containsKey(str)) {
                occurrenceMap[str] = (occurrenceMap[str] ?: 0) + 1
            } else {
                occurrenceMap[str] = 1
            }
        }
        return occurrenceMap
    }
}