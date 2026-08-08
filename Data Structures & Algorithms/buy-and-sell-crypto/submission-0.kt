class Solution {
    fun maxProfit(prices: IntArray): Int {
        var maxProfit = 0
        var maxPrice = 0
        for (i in prices.size-1 downTo 0) {
            val profit = maxPrice - prices[i]
            maxProfit = max(maxProfit, profit)
            maxPrice = max(maxPrice, prices[i])
        }
        return maxProfit 
    }
}
