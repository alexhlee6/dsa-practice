class Solution {
    fun maxSubArray(nums: IntArray): Int {
        var maxSum = nums[0]
        var currSum = nums[0]

        for (i in 1 until nums.size) {
            if (currSum < 0) {
                currSum = nums[i]
            } else {
                currSum += nums[i]
            }
            maxSum = max(maxSum, currSum)
        }
        return maxSum
    }
}
