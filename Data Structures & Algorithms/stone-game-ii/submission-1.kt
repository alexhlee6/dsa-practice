class Solution {
    val mem: MutableMap<Triple<Int,Int,Boolean>, IntArray> = mutableMapOf()

    fun takeStones(piles: List<Int>, m: Int, idx: Int, isAlice: Boolean): IntArray {
        var max = 0
        val memKey = Triple(m,idx,isAlice)

        if (mem.contains(memKey)) {
            return mem.getValue(memKey)
        }

        if (idx + (2*m) >= piles.size) {
            var sum = 0
            for (i in idx..piles.size-1) {
                sum += piles[i]
            }

            if (isAlice) {
                mem[memKey] = intArrayOf(sum, 0)
                return intArrayOf(sum, 0)
            } else {
                mem[memKey] = intArrayOf(0, sum)
                return intArrayOf(0, sum)
            }
        }
        
        var maxSum = 0
        var currStones = 0
        var currPiles = 0
        var maxResult = intArrayOf(0,0)

        for (i in idx until idx+(2*m)) { 
            val curr = piles[i]
            currStones += curr
            currPiles++

            val nextIdx = i+1
            val nextSum = takeStones(piles, max(m, currPiles), nextIdx, !isAlice)

            var currSum: Int
            if (isAlice) {
                currSum = currStones + nextSum[0]
            } else {
                currSum = currStones + nextSum[1]
            }

            if (currSum > maxSum) {
                maxSum = currSum
                if (isAlice) {
                    maxResult = intArrayOf(currSum, nextSum[1])
                } else {
                    maxResult = intArrayOf(nextSum[0], currSum)
                }
            }
        }

        mem[memKey] = maxResult
        return maxResult
    }

    fun stoneGameII(piles: IntArray): Int {
        val result = takeStones(piles.toList(), 1, 0, true)
        return result[0]
    }
}
