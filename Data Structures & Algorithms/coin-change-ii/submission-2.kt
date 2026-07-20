class Solution {
    fun change(amount: Int, coins: IntArray): Int {
        coins.sortDescending()
        return getDistinctCombinations(amount, coins)
    }
    val memo: MutableMap<Pair<Int,Int>, Int> = mutableMapOf()
    fun getDistinctCombinations(
        amount: Int, 
        coins: IntArray, 
        idx: Int = 0
    ): Int {
        var count = 0
        val memoKey = Pair(amount, idx)

        if (amount == 0) {
            return 1
        } else if (amount == coins[coins.size-1]) {
            return 1
        } else if (amount < coins[coins.size-1]) {
            return 0
        } else if (memo.contains(memoKey)) {
            return memo.getValue(memoKey)
        }

        for (i in idx until coins.size) {
            val coin = coins[i]
            if (amount < coin) {
                continue
            } else if (amount == coin) {
                count++
                continue
            }
            count += getDistinctCombinations(amount-coin, coins, i)
        }
        memo[memoKey] = count
        return count
    }
}
