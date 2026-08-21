class Solution {
    fun combinationSum(nums: IntArray, target: Int): List<List<Int>> {
        nums.sortDescending()
        val res = getCombinations(nums, target)
        return res
    }

    fun getCombinations(nums: IntArray, target: Int, idx: Int = 0): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        if (idx >= nums.size || target <= 1) {
            return res
        }

        for (i in idx until nums.size) {
            if (target < nums[i]) {
                continue
            } else if (target == nums[i]) {
                res.add(listOf(target))
                continue
            }
            val first = listOf(nums[i])
            val nextCombs = getCombinations(nums, target - nums[i], i)
            for (nextArr in nextCombs) {
                res.add(first + nextArr)
            }
        }
        return res
    }
}
