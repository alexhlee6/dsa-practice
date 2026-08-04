class Solution {
    fun trap(heights: IntArray): Int {
        var left = 0

        var totalWater = 0
        val maxWaterRTL = heights.clone()
        val maxWaterLTR = heights.clone()

        for (i in 1 until heights.size) {
            maxWaterLTR[i] = max(maxWaterLTR[i], maxWaterLTR[i-1])
            maxWaterRTL[heights.size-1-i] = max(maxWaterRTL[heights.size-1-i], maxWaterRTL[heights.size-i])
        }

        // println("maxWaterLTR: " + maxWaterLTR.contentToString())
        // println("maxWaterRTL: " + maxWaterRTL.contentToString())

        for (i in 1 until heights.size-1) {
            val minPeak = min(maxWaterLTR[i-1], maxWaterRTL[i+1])
            if (heights[i] < minPeak) {
                totalWater += minPeak - heights[i]
            }
        }

        return totalWater
    }
}
