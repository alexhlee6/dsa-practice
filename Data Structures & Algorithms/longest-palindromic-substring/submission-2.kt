class Solution {
    
    fun longestPalindrome(s: String): String {
        if (s.length <= 1) {
            return s
        }

        var maxLenStr = s.substring(0,1)

        var charIndices: MutableMap<Char, MutableList<Int>> = mutableMapOf()

        for (i in 0 until s.length) {
            val char = s[i]
            val indices = charIndices[char] ?: mutableListOf<Int>()
            indices.add(i)
            charIndices[char] = indices
        }

        for (i in 0 until s.length) {
            if (maxLenStr.length >= (s.length - i)) {
                break
            }
            val char = s[i]
            val indices = charIndices.getValue(char) 
            var j = indices.size-1
            while (j > 0 && indices[j] > i) {
                if (isPalindrome(s, intArrayOf(i, indices[j]))) {
                    if (maxLenStr.length < (indices[j] - i + 1)) {
                        maxLenStr = s.substring(i, indices[j] + 1)
                    }
                    break
                } else if (indices[j] - i + 1 <= maxLenStr.length) {
                    break
                }
                j--
            }
        }
        return maxLenStr
    }

    fun isPalindrome(s: String, indices: IntArray): Boolean {
        var left = indices[0]; var right = indices[1]
        while (left < right) {
            if (s[left] != s[right]) {
                return false
            }
            left++; right--
        }
        return true
    }
}
