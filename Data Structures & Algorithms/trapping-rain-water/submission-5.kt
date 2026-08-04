class Solution {
    fun trap(heights: IntArray): Int {
        var totalWater = 0
        val maxHeightRTL = heights.clone()
        val maxHeightLTR = heights.clone()

        for (i in 1 until heights.size) {
            maxHeightLTR[i] = max(maxHeightLTR[i], maxHeightLTR[i-1])
            maxHeightRTL[heights.size-1-i] = max(maxHeightRTL[heights.size-1-i], maxHeightRTL[heights.size-i])
        }

        for (i in 1 until heights.size-1) {
            val minPeak = min(maxHeightLTR[i-1], maxHeightRTL[i+1])
            if (heights[i] < minPeak) {
                totalWater += minPeak - heights[i]
            }
        }

        return totalWater
    }
}
