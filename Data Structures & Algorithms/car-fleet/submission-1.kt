class Solution {
    // sort by position: List<Pair<Int,Int>>
    // for each car, find when it will reach the destination (in order)
    // if a car is supposed to reach the dest before the previous car, its part of the prev car's fleet

    fun carFleet(target: Int, position: IntArray, speed: IntArray): Int {
        val cars = mutableListOf<Pair<Int,Int>>()

        for (i in 0 until position.size) {
            cars.add(Pair(position[i], speed[i]))
        }

        val sorted = cars.sortedBy { it.first }.reversed()

        val arrivalTimes = DoubleArray(sorted.size) {0.0}

        for (i in 0 until sorted.size) {
            val pos = sorted[i].first; val sp = sorted[i].second
            val hoursLeft = (target - pos) / (sp * 1.0)
            arrivalTimes[i] = hoursLeft
        }

        for (i in 1 until arrivalTimes.size) {
            val curr = arrivalTimes[i]
            val prev = arrivalTimes[i-1]
            if (curr < prev) {
                arrivalTimes[i] = prev
            }
        }

        var fleetCount = 1
        var currTime = arrivalTimes[0]

        for (i in 1 until arrivalTimes.size) {
            if (currTime == arrivalTimes[i]) {
                continue
            }
            fleetCount++
            currTime = arrivalTimes[i]
        }
        return fleetCount
    }
}
