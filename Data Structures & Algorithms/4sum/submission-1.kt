class Solution {
    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
        nums.sort()

        val result = mutableListOf<List<Int>>()
        val numsMap = mutableMapOf<Int, MutableSet<Int>>()
        for (i in 0 until nums.size) {
            if (!numsMap.contains(nums[i])) {
                numsMap[nums[i]] = mutableSetOf<Int>()
            }
            numsMap.getValue(nums[i]).add(i)
        }
        


        for (i in 0 until nums.size-3) {
            val numA = nums[i]
            if (nums[i] + nums[i+1] + nums[i+2] + nums[i+3] > target) {
                break
            } else if (i > 0 && nums[i] == nums[i-1]) {
                continue
            } else if (target <= 0 && nums[i] > 0) {
                break
            }

            for (j in i+1 until nums.size-2) {
                val numB = nums[j]
                if (j > i+1 && nums[j] == nums[j-1]) {
                    continue
                }

                val twoSum = target - numA - numB
                if (twoSum < nums[j+1] + nums[j+2]) {
                    break
                }

                for (k in j+1 until nums.size-1) {
                    if (k > j+1 && nums[k] == nums[k-1]) {
                        continue
                    }

                    val lastTarget = twoSum - nums[k]
                    if (lastTarget >= nums[k] && numsMap.contains(lastTarget) && numsMap.getValue(lastTarget).last() != k) {
                        result.add(listOf(numA,numB,nums[k],lastTarget))
                    }
                }

            }
        }

        return result
    }
}
