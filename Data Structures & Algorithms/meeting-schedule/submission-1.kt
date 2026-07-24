/**
 * Definition of Interval:
 * class Interval(var start: Int, var end: Int) {}
 */

class Solution {
    fun canAttendMeetings(intervals: List<Interval>): Boolean {
        val sorted = intervals.sortedByDescending { it.end }.sortedBy { it.start }
    
        for (i in 1 until sorted.size) {
            val prev = sorted[i-1]
            val curr = sorted[i]
            if (prev.end > curr.start) {
                return false
            }
        }
        return true
    }
}
