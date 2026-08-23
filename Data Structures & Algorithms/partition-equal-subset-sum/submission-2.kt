class Solution {
    fun canPartition(nums: IntArray): Boolean {
        var sum = 0
        for (num in nums) {
            sum += num
        }
        if (sum % 2 != 0) {
            return false 
        }
        return canMakeEqualSums(nums)
    }

    val memo: MutableMap<Pair<Int,Int>, Boolean> = mutableMapOf()

    fun canMakeEqualSums(nums: IntArray, i: Int = 0, sum1: Int = 0, sum2: Int = 0): Boolean {
        val key = Pair(i, sum1-sum2)
        if (i >= nums.size) {
            return sum1 == sum2
        } else if (i == nums.size-1) {
            return if (sum1 < sum2) sum1 + nums[i] == sum2 else sum2 + nums[i] == sum1
        } else if (memo.contains(key)) {
            return memo.getValue(key)
        }
        
        val res1 = canMakeEqualSums(nums, i+1, sum1 + nums[i], sum2)
        if (res1) {
            memo[key] = true
            return true
        }
        val res2 = canMakeEqualSums(nums, i+1, sum1, sum2 + nums[i])
        if (res2) {
            memo[key] = true
            return true
        }
        memo[key] = false
        return false
    }
}
