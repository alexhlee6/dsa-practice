/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    fun isSubtree(root: TreeNode?, subRoot: TreeNode?, matchEvery: Boolean = false): Boolean {
        if (root == null) {
            return subRoot == null
        } else if (root.left == null && root.right == null) {
            return root.`val` == subRoot?.`val` && (subRoot?.left == null && subRoot?.right == null)
        }
        
        // curr is root of subtree
        if (subRoot != null && root.`val` == subRoot.`val`) {
            val leftRes = isSubtree(root.left, subRoot.left, true)
            val rightRes = isSubtree(root.right, subRoot.right, true)
            if (leftRes && rightRes) {
                return true 
            }
        }

        if (matchEvery) {
            return false
        }

        // curr is NOT root of subtree 
        val leftRes = isSubtree(root.left, subRoot, false)
        val rightRes = isSubtree(root.right, subRoot, false)
        return leftRes || rightRes 
    }
}
