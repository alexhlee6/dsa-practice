/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    fun diameterOfBinaryTree(root: TreeNode?): Int {
        root ?: return 0
        val longestDownPath = getLongestPath(root)
        return max(maxPathThruRoot, longestDownPath)
    }

    var maxPathThruRoot: Int = Int.MIN_VALUE

    fun getLongestPath(root: TreeNode): Int {
        if (root.left == null && root.right == null) {
            return 0
        }

        val leftRes = root.left?.let {1 + getLongestPath(it)} ?: 0
        val rightRes = root.right?.let {1 + getLongestPath(it)} ?: 0

        maxPathThruRoot = max(maxPathThruRoot, leftRes+rightRes)

        return max(leftRes, rightRes)
    }
}
