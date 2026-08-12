/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    fun maxDepth(root: TreeNode?): Int {
        if (root == null) {
            return 0
        } else if (root.left == null && root.right == null) {
            return 1
        }

        var maxD = Int.MIN_VALUE
        root.left?.let { maxD = maxDepth(it) }
        root.right?.let { maxD = max(maxD, maxDepth(it)) }
        return 1 + maxD
    }
}
