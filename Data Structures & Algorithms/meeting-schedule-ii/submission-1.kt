/**
 * Definition of Interval:
 * class Interval(var start: Int, var end: Int) {}
 */

class Solution {

    fun minMeetingRooms(intervals: List<Interval>): Int {
        val startTimes = intervals.map {it.start}.sorted()
        val endTimes = intervals.map {it.end}.sorted()

        var i = 0; var j = 0
        var count = 0 // current meetings 
        var maxRooms = 0 

        while (i < intervals.size && j < intervals.size) {
            if (startTimes[i] < endTimes[j]) {
                count++; i++ // open new room 
            } else {
                i++; j++
            }
            maxRooms = max(maxRooms, count)
        }
        return maxRooms
    }

}
