class Solution {
    // outer loop: iterate over nums (i)
    // inner loop: iterate over nums starting from i+1 (j) - track visited numbers and check if already visited 0-nums[i]-nums[j]
    // time: O(n^2), space: O(n)
    fun threeSum(nums: IntArray): List<List<Int>> {
        nums.sort()
        val res = mutableListOf<List<Int>>()

        if (nums.last() < 0) {
            return res
        } else if (nums.first() > 0) {
            return res
        }

        for (i in 0 until nums.size-2) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue
            }

            val visited = mutableSetOf<Int>()
            val usedAsThird = mutableSetOf<Int>()

            for (j in i+1 until nums.size) {
                val curr = nums[j]
                val target = 0-nums[i]-curr

                if (visited.contains(target) && !usedAsThird.contains(curr)) {
                    res.add(listOf(nums[i], target, curr))
                    usedAsThird.add(curr)
                }
                visited.add(curr)
            }
        }
        return res
    }
}
