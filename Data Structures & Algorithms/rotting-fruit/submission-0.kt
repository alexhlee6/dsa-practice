class Solution {
    // iterate over every cell, track rotten fruit cells and fresh fruit cells 
    // every minute, iterate over rotten cells list, add new rotten cells
    // if no change, return -1. if no fresh fruits, return time

    fun orangesRotting(grid: Array<IntArray>): Int {
        val rotten = mutableListOf<Pair<Int,Int>>()
        val fresh = mutableListOf<Pair<Int,Int>>()

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
        while (fresh.size > 0) {
            val newRottenCount = passMinute(fresh, rotten)
            if (newRottenCount == 0 && fresh.size > 0) {
                return -1
            }
            time++
        }
        return time 
    }

    val dirs: List<IntArray> = listOf(
        intArrayOf(0,1), intArrayOf(0,-1), intArrayOf(1,0), intArrayOf(-1,0)
    )

    fun passMinute(fresh: MutableList<Pair<Int,Int>>, rotten: MutableList<Pair<Int,Int>>): Int {
        val newRotten = mutableListOf<Pair<Int,Int>>()

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
        for (pair in newRotten) {
            rotten.add(pair)
        }
        return newRotten.size
    }
}
