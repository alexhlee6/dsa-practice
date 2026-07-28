class Solution {
    fun transpose(matrix: Array<IntArray>): Array<IntArray> {
        val res = Array(matrix[0].size) { IntArray(matrix.size) { -1 } }

        for (i in 0 until matrix.size) {
            for (j in 0 until matrix[0].size) {
                val curr = matrix[i][j]
                res[j][i] = curr
            }
        }

        return res
    }
}
