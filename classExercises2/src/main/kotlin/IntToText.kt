package org.example

val digitToText = mapOf(
    0 to "zero", 1 to "one", 2 to "two", 3 to "three", 4 to "four", 5 to "five",
    6 to "six", 7 to "seven", 8 to "eight", 9 to "nine", 10 to "ten",
    11 to "eleven", 12 to "twelve", 13 to "thirteen", 14 to "fourteen", 15 to "fifteen"
)

fun intToText(i: Int): String {
    when {
        i < 16 -> return digitToText[i] ?: ""
        i < 20 -> return digitToText[i - 10] + "teen"
        i < 30 -> return "twenty" + if (i > 20) " " + digitToText[i - 20] else ""
        i < 40 -> return "thirty" + if (i > 30) " " + digitToText[i - 30] else ""
        i < 50 -> return "forty" + if (i > 40) " " + digitToText[i - 40] else ""
        i < 60 -> return "fifty" + if (i > 50) " " + digitToText[i - 50] else ""
        i < 70 -> return "sixty" + if (i > 60) " " + digitToText[i - 60] else ""
        i < 80 -> return "seventy" + if (i > 70) " " + digitToText[i - 70] else ""
        i < 90 -> return "eighty" + if (i > 80) " " + digitToText[i - 80] else ""
        i < 100 -> return "ninety" + if (i > 90) " " + digitToText[i - 90] else ""
        i < 1000 -> {
            val s = intToText(i / 100) + " hundred"
            return s + if (i % 100 > 0) " and " + intToText(i % 100) else ""
        }
        i < 1000000 -> {
            val s = intToText(i / 1000) + " thousand"
            return s + if (i % 1000 > 0) " " + intToText(i % 1000) else ""
        }
        else -> return "too large number"
    }
}

fun mainIntToText() {
    println(intToText(48))
}
