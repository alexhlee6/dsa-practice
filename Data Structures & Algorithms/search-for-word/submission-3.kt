class Solution {
    // need to visit each cell, so at least O(n*m) time
    // recurs: check if cell matches char, then for 4 directions call function for the next char 
    // if does not match, return early 
    // for each recursive step, we check 4 directions (constant time)
    // if full word matches, worst case time complexity: O(n*m*w) where w = length of word
    // space: track visited cells -> O(n*m)
    
    fun exist(board: Array<CharArray>, word: String): Boolean {
        for (i in 0 until board.size) {
            for (j in 0 until board[0].size) {
                if (word[0] == board[i][j]) {
                    val isMatch = canMatchWord(board, word, Pair(i,j))
                    if (isMatch) {
                        return true
                    }
                }
            }
        }
        return false
    }

    val dirs: List<IntArray> = listOf(
        intArrayOf(0,1), intArrayOf(0,-1), intArrayOf(1,0), intArrayOf(-1,0), 
    )

    fun canMatchWord(
        board: Array<CharArray>, 
        word: String, 
        pos: Pair<Int,Int>, 
        idx: Int = 0,
        visited: MutableSet<Pair<Int,Int>> = mutableSetOf()
    ): Boolean {
        val i = pos.first; val j = pos.second 

        if (visited.contains(pos)) {
            return false
        } else if (idx >= word.length) {
            return true
        } else if (idx == word.length-1) {
            return word[idx] == board[i][j]
        } else if (word[idx] != board[i][j]) {
            return false
        }
        visited.add(pos)

        for (dir in dirs) {
            val x = i+dir[0]; val y = j+dir[1]
            if (x >= 0 && y >= 0 && x < board.size && y < board[0].size) {
                val nextRes = canMatchWord(board, word, Pair(x,y), idx+1, visited)
                if (nextRes) {
                    return true
                }
            }
        }
        visited.remove(pos)
        return false
    }
}
