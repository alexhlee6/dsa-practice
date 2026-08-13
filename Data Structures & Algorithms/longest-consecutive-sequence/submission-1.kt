class Solution {
    
    fun longestConsecutive(nums: IntArray): Int {
        val unique = nums.toMutableSet()
        var maxLen = 0

        while (unique.size > 0) {
            val currFirst = unique.first()
            unique.remove(currFirst)

            var curr = currFirst
            var currLen = 1
            while (unique.contains(curr-1)) {
                unique.remove(curr-1)
                curr--
                currLen++
            }
            curr = currFirst
            while (unique.contains(curr+1)) {
                unique.remove(curr+1)
                curr++
                currLen++
            }
            maxLen = max(maxLen, currLen)
        }
        return maxLen
    }
}
