class Solution {
    fun longestDiverseString(a: Int, b: Int, c: Int): String {
        val pairs: List<Pair<Char,Int>> = listOf(Pair('a', a), Pair('b', b), Pair('c', c))
        return getLongestHappyStr(pairs, StringBuilder())
    }

    fun getLongestHappyStr(chars: List<Pair<Char,Int>>, builder: StringBuilder): String {
        if (chars[0].second == 0 && chars[1].second == 0 && chars[2].second == 0) {
            return builder.toString()
        } 

        val sorted = chars.sortedByDescending {it.second}
        var canAddAny = true
        if (sorted.size == 1 && sorted[0].second > 0 && builder.length >= 2) {
            val char = sorted[0].first
            if (builder[builder.length-1] == char && builder[builder.length-2] == char) {
                return builder.toString()
            }
        } else if (sorted.size >= 2 && sorted[1].second == 0 && builder.length >= 2) {
            val char = sorted[0].first
            if (builder[builder.length-1] == char && builder[builder.length-2] == char) {
                return builder.toString()
            }
        }

        for (i in 0 until sorted.size) {
            val pair = sorted[i]
            val char = pair.first
            val count = pair.second
            if (count <= 0) {
                continue
            }

            var endCharsMatchingCurr = 0
            if (builder.length > 0 && builder[builder.length-1] == char) {
                endCharsMatchingCurr++
                if (builder.length > 1 && builder[builder.length-2] == char) {
                    endCharsMatchingCurr++
                }
            }
            if (endCharsMatchingCurr == 2) {
                continue
            }

            builder.append(char)
            val newChars = sorted.mapIndexed { idx, pair -> if (idx == i) Pair(char, count-1) else pair }
            return getLongestHappyStr(newChars, builder)
        }

        return ""
    }
}
