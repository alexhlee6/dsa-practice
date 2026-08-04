class Solution {
    // max subarr product 
    // - if product becomes zero, move left pointer until no zeros 
    // - if product becomes negative, keep moving right (might reach another negative). move left to where first neg occurred
    fun maxProduct(nums: IntArray): Int {
        var left = 0
        var maxProd = Int.MIN_VALUE
        var hasZero = false

        while (left < nums.size) {
            if (left < nums.size && nums[left] == 0) {
                if (!hasZero) {
                    hasZero = true
                }
                left++
                continue
            }

            var currProd = nums[left].toLong()
            maxProd = max(maxProd, currProd.toInt())
            var right = left + 1

            println("left:$left, right:$right, currProd:${currProd.toInt()}, maxProd:$maxProd")

            while (right < nums.size && nums[right] != 0) {
                currProd *= nums[right]
                maxProd = max(maxProd, currProd.toInt())
                right++
            }

            if (currProd >= 0) {
                left = right
                continue
            }

            while (currProd < 0 && left < right) {
                currProd /= nums[left]
                if (left < right - 1) {
                    maxProd = max(maxProd, currProd.toInt())
                }
                left++ 
            }

        }
        return if (hasZero && maxProd < 0) 0 else maxProd
    }
}
