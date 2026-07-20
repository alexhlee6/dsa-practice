class Solution {
    fun minEatingSpeed(piles: IntArray, h: Int): Int {
        piles.sort()

        var lower = 1.0; var higher = piles[piles.size-1].toDouble()
        while (lower < higher) {
            val mid = ((lower+higher)/2).toInt()
            val canFinish = canFinishBananas(piles, h, mid)
            if (canFinish) {
                higher = mid.toDouble()
            } else {
                lower = (mid+1).toDouble()
            }
        }
        return higher.toInt()
    }

    fun canFinishBananas(piles: IntArray, h: Int, k: Int, idx: Int = 0): Boolean {
        if (idx >= piles.size) {
            return true
        } else if (h <= 0) {
            return false
        } 

        val currCount = piles[idx]
        if (currCount <= k) {
            return canFinishBananas(piles, h-1, k, idx+1)
        } else {
            val requiredHours = ceil(piles[idx] / k.toDouble())
            if (requiredHours <= h) {
                return canFinishBananas(piles, h-requiredHours.toInt(), k, idx+1)
            }
            return false
        }
    }
}
