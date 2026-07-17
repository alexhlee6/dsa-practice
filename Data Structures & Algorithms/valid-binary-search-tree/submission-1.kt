/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    fun isValidBST(
        root: TreeNode?, 
        range: Pair<Int,Int> = Pair(Int.MIN_VALUE,Int.MAX_VALUE)
    ): Boolean {
        if (root == null) {
            return true
        } else if (root.left == null && root.right == null) {
            return root.`val` >= range.first && root.`val` <= range.second
        }

        val leftRes = root.left?.let { isValidBST(it, Pair(range.first, root.`val` - 1)) } ?: true
        val rightRes = root.right?.let { isValidBST(it, Pair(root.`val` + 1, range.second)) } ?: true

        val isCurrentNodeInRange = root.`val` >= range.first && root.`val` <= range.second

        return leftRes && rightRes && isCurrentNodeInRange
    }
}
