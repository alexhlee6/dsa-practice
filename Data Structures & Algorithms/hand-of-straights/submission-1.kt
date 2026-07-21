class Solution {
    fun isNStraightHand(hand: IntArray, groupSize: Int): Boolean {
        if (hand.size % groupSize != 0) {
            return false
        }

        val counter: MutableMap<Int,Int> = mutableMapOf()
        for (num in hand) {
            counter[num] = (counter[num] ?: 0) + 1
        }

        val sortedKeys = counter.keys.toList().sorted()
        var sortedKeysIdx = 0

        while (counter.size > 0) {
            while (counter.size > 0 && sortedKeysIdx < sortedKeys.size && counter[sortedKeys[sortedKeysIdx]] == null) {
                sortedKeysIdx++
            }
            val currMin = sortedKeys[sortedKeysIdx]
            for (i in currMin..(currMin+groupSize-1)) {
                if (!counter.contains(i)) {
                    return false
                }
                val newCount = counter.getValue(i)-1
                if (newCount == 0) {
                    counter.remove(i)
                } else {
                    counter[i] = newCount
                }
            }
        }
        return true
    }
}
