class Solution {
    fun letterCombinations(digits: String): List<String> {
        val combos: MutableList<String> = mutableListOf()
        if (digits.length == 0) {
            return combos
        }
        getLetterCombos(digits, combos)
        return combos
    }

    val charMap: Map<Char, String> = mapOf(
        '2' to "abc", '3' to "def", '4' to "ghi", '5' to "jkl", '6' to "mno", '7' to "pqrs", '8' to "tuv", '9' to "wxyz"
    )

    fun getLetterCombos(digits: String, combos: MutableList<String>, i: Int = 0, builder: StringBuilder = StringBuilder()) {
        if (i >= digits.length) {
            combos.add(builder.toString())
            return
        }

        val currChar = digits[i]

        for (letter in charMap[currChar]!!) {
            builder.append(letter)
            getLetterCombos(digits, combos, i+1, builder)
            builder.deleteAt(builder.length-1)
        }
    }
}
