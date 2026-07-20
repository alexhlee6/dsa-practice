class Solution {
    fun permuteUnique(nums: IntArray): List<List<Int>> {
        nums.sort()
        return getPermutations(nums.toList())
    }

    fun getPermutations(nums: List<Int>): List<List<Int>> {
        if (nums.size == 0) {
            return listOf<List<Int>>()
        } else if (nums.size == 1) {
            return listOf(nums)
        }

        val result: MutableList<List<Int>> = mutableListOf()

        for (i in 0 until nums.size) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue
            }
            var nextArr = listOf<Int>()
            if (i != 0) {
                nextArr += nums.slice(0..i-1)
            }
            if (i != nums.size-1) {
                nextArr += nums.slice(i+1..nums.size-1)
            }
            val nextRes = getPermutations(nextArr)
            for (subArr in nextRes) {
                result.add(listOf(nums[i]) + subArr)
            }
        }

        return result
    }
}
