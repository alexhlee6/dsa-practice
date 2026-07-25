class Solution {
    fun searchInsert(nums: IntArray, target: Int): Int {
        var left = 0; var right = nums.size-1
        while (left <= right) {
            val mid = (left + right) / 2
            val midNum = nums[mid]
            if (midNum == target) {
                return mid
            } else if (midNum < target) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return left
    }
}
