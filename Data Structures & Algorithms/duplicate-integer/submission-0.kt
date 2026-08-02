class Solution {
    fun hasDuplicate(nums: IntArray): Boolean {
        val visited = mutableSetOf<Int>()
        for (num in nums) {
            if (visited.contains(num)) {
                return true
            }
            visited.add(num)
        }
        return false 
    }
}
