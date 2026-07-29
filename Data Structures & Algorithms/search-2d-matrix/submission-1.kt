class Solution {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        // find row using last element, then find column (b search)
        var left = 0; var right = matrix.size - 1
        val lastColIdx = matrix[0].size - 1

        while (left < right) {
            val mid = (left+right) / 2
            if (matrix[mid][lastColIdx] == target) {
                return true
            } else if (matrix[mid][lastColIdx] < target) {
                left = mid + 1
            } else {
                right = mid
            }
        }

        val targetRowIdx = right

        left = 0; right = lastColIdx

        while (left <= right) {
            val mid = (left + right) / 2
            if (matrix[targetRowIdx][mid] == target) {
                return true
            } else if (matrix[targetRowIdx][mid] < target) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return false
    }
}
