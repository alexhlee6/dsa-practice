/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    val memo: MutableMap<Pair<TreeNode,Boolean>, Int> = mutableMapOf()

    fun rob(root: TreeNode?, canRob: Boolean = true): Int {
        if (root == null) {
            return 0
        } else if (root.left == null && root.right == null) {
            return if (canRob) root.`val` else 0
        } 

        val memoKey = Pair(root, canRob)
        if (memo.contains(memoKey)) {
            return memo.getValue(memoKey)
        }

        var nextSumIfSkipCurr = 0

        root.left?.let {
            val res = rob(it, true)
            nextSumIfSkipCurr += res
        }
        root.right?.let {
            val res = rob(it, true)
            nextSumIfSkipCurr += res
        }
        
        var maxSum = nextSumIfSkipCurr

        if (canRob) {
            var nextSumWithRobCurr = root.`val`

            root.left?.let {
                val res = rob(it, false)
                nextSumWithRobCurr += res
            }
            root.right?.let {
                val res = rob(it, false)
                nextSumWithRobCurr += res
            }
            maxSum = max(maxSum, nextSumWithRobCurr)
        }

        memo[memoKey] = maxSum
        return maxSum
    }
}
