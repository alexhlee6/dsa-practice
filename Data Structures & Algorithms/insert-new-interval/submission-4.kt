class Solution {
    // b-search for index of interval with closest greater start time (position to insert)
    // if overlapping, merge with overlapping intervals
    // time: O(n), space: O(n)

    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        if (intervals.size == 0) {
            return arrayOf(newInterval)
        }
        val insertIdx = getInsertIndex(intervals, newInterval)
        println(insertIdx)

        val result = mutableListOf<IntArray>()
        for (i in 0 until insertIdx) {
            result.add(intervals[i])
        }

        // no overlap
        if (intervals[insertIdx][1] < newInterval[0]) {
            result.add(intervals[insertIdx])
            result.add(newInterval)
            return result.toTypedArray()
        } else if (intervals[insertIdx][0] > newInterval[1] ) {
            result.add(newInterval)
            for (j in insertIdx until intervals.size) {
                result.add(intervals[j])
            }
            return result.toTypedArray()
        }

        val insertInterval = newInterval.clone()
        insertInterval[0] = min(insertInterval[0], intervals[insertIdx][0])
        insertInterval[1] = max(insertInterval[1], intervals[insertIdx][1])
        var i = insertIdx + 1
        
        while (i < intervals.size && intervals[i][0] <= insertInterval[1]) {
            insertInterval[0] = min(insertInterval[0], intervals[i][0])
            insertInterval[1] = max(insertInterval[1], intervals[i][1])
            i++
        }

        result.add(insertInterval)
        // add the remaining intervals
        while (i < intervals.size) {
            result.add(intervals[i])
            i++
        }

        return result.toTypedArray()
    }

    fun getInsertIndex(intervals: Array<IntArray>, newInterval: IntArray): Int {
        var left = 0; var right = intervals.size-1

        while (left < right) {
            val mid = (left + right) / 2
            val midEnd = intervals[mid][1]
            if (newInterval[0] > midEnd) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return left
    }
}
