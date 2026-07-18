class Solution {
    fun maxAreaOfIsland(grid: Array<IntArray>): Int {
        var maxArea = 0

        for (i in 0 until grid.size) {
            for (j in 0 until grid[0].size) {
                val isIsland = grid[i][j] == 1
                if (isIsland) {
                    maxArea = max(maxArea, getAreaAndClearIsland(grid, intArrayOf(i,j)))
                }
            }
        }
        return maxArea
    }

    val dirs: List<Pair<Int,Int>> = listOf(
        Pair(0,1), Pair(0,-1), Pair(1,0), Pair(-1,0)
    )

    fun getAreaAndClearIsland(grid: Array<IntArray>, pos: IntArray): Int {
        var area = 0
        val queue: MutableList<IntArray> = mutableListOf(pos)

        while (queue.size > 0) {
            val lastPos = queue.removeLast()
            val i = lastPos[0]; val j = lastPos[1]
            if (grid[i][j] == 1) {
                grid[i][j] = 0
                area++
            } else {
                continue
            }

            for (dir in dirs) {
                val dx = dir.first; val dy = dir.second
                val x = dx + i; val y = dy + j
                if (x >= 0 && x < grid.size && y >= 0 && y < grid[0].size) {
                    val isLand = grid[x][y] == 1
                    if (isLand) {
                        queue.add(intArrayOf(x,y))
                    }
                }
            }
        }
        return area
    }
}
