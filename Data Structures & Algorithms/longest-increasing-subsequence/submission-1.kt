class Solution {
    
    fun lengthOfLIS(nums: IntArray): Int {
        var maxLen = 0

        for (i in 0 until nums.size) {
            val len = getLongestSubseqLen(nums, i)
            maxLen = max(maxLen, len)
        }
        return maxLen
    }

    val mem: MutableMap<Int, Int> = mutableMapOf()

    fun getLongestSubseqLen(nums: IntArray, idx: Int): Int {
        if (idx >= nums.size) {
            return 0
        } else if (idx == nums.size-1) {
            return 1
        } else if (mem.contains(idx)) {
            return mem.getValue(idx)
        }

        var maxLen = 1

        for (i in idx+1 until nums.size) {
            if (nums[i] > nums[idx]) {
                val len = 1 + getLongestSubseqLen(nums, i)
                maxLen = max(maxLen, len)
            }
        }
        mem[idx] = maxLen
        return maxLen
    }
}
