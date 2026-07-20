class Solution {
    fun getConcatenation(nums: IntArray): IntArray {
        val answer = IntArray(nums.size*2) {0}

        for (i in 0 until nums.size) {
            answer[i] = nums[i]
            answer[i+nums.size] = nums[i]
        }

        return answer
    }
}
