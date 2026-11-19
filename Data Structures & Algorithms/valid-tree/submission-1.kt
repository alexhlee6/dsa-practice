class Solution {
    fun validTree(n: Int, edges: Array<IntArray>): Boolean {
        val grid = Array(n) { IntArray(n) { 0 } }
        for (edge in edges) {
            val (i, j) = edge
            grid[i][j] = 1
            grid[j][i] = 1
        }
        val usedNodes = mutableSetOf<Int>()
        val res = isValid(grid, 0, usedNodes)
        return res && usedNodes.size == n
    }

    fun isValid(grid: Array<IntArray>, i: Int, used: MutableSet<Int>): Boolean {
        if (used.contains(i)) {
            return false
        } else {
            used.add(i)
        }

        val row = grid[i]
        for (j in 0 until row.size) {
            if (row[j] == 1) {
                row[j] = 0
                grid[j][i] = 0
                if (!isValid(grid, j, used)) {
                    return false
                }
            }
        }
        return true
    }
}
