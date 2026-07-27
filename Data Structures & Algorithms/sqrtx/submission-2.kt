class Solution {
    fun mySqrt(x: Int): Int {
        var left = 0; var right = x
        while (left <= right) {
            val mid = ((left.toDouble() + right.toDouble()) / 2.0).roundToInt().toDouble()
            
            if (mid * mid == x.toDouble() || (mid * mid < x.toDouble() && ((mid+1) * (mid+1)) > x.toDouble())) {
                return mid.toInt()
            } else if (mid * mid > x) {
                right = mid.toInt() - 1
            } else {
                left = mid.toInt() + 1
            }
        }

        return right
    }
}
