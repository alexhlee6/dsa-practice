class Solution {
    // iterate over every cell, track rotten fruit cells and fresh fruit cells 
    // every minute, iterate over rotten cells list, add new rotten cells
    // if no change, return -1. if no fresh fruits, return time

    fun orangesRotting(grid: Array<IntArray>): Int {
        val rotten = mutableSetOf<Pair<Int,Int>>()
        val fresh = mutableSetOf<Pair<Int,Int>>()

        for (i in 0 until grid.size) {
            for (j in 0 until grid[0].size) {
                when (grid[i][j]) {
                    1 -> fresh.add(Pair(i,j))
                    2 -> rotten.add(Pair(i,j)) 
                    else -> continue
                }
            }
        }

        var time = 0
        var currRotten: Set<Pair<Int,Int>> = rotten

        while (fresh.size > 0) {
            currRotten = passMinute(fresh, currRotten)
            if (currRotten.size == 0 && fresh.size > 0) {
                return -1
            }
            time++
        }
        return time 
    }

    val dirs: List<IntArray> = listOf(
        intArrayOf(0,1), intArrayOf(0,-1), intArrayOf(1,0), intArrayOf(-1,0)
    )

    fun passMinute(fresh: MutableSet<Pair<Int,Int>>, rotten: Set<Pair<Int,Int>>): Set<Pair<Int,Int>> {
        val newRotten = mutableSetOf<Pair<Int,Int>>()

        for (r in rotten) {
            val i = r.first; val j = r.second 
            for (dir in dirs) {
                val x = i + dir[0]; val y = j + dir[1]
                val adjCell = Pair(x,y)
                if (fresh.contains(adjCell)) {
                    fresh.remove(adjCell)
                    newRotten.add(adjCell)
                }
            }
        }

        return newRotten
    }
}
