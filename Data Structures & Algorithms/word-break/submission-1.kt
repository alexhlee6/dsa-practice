class Solution {

    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        return canConstructWithWords(s, wordDict)
    }

    val memo: MutableMap<Int, Boolean> = mutableMapOf()

    fun canConstructWithWords(s: String, wordDict: List<String>, i: Int = 0): Boolean {
        if (i >= s.length) {
            return true
        } else if (memo.contains(i)) {
            return memo.getValue(i)
        }

        for (word in wordDict) {
            var j = 0
            while (j < word.length && i+j < s.length) {
                if (word[j] == s[i+j]) {
                    j++
                } else {
                    break
                }
            }
            if (j == word.length) {
                val nextRes = canConstructWithWords(s, wordDict, i+j)
                if (nextRes) {
                    memo[i] = true
                    return true
                }
            }
        }
        memo[i] = false
        return false 
    }
}
