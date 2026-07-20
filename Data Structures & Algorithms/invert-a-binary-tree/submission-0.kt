/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    // for every node, switch left & right ?

    fun invertTree(root: TreeNode?): TreeNode? {
        if (root == null) {
            return null
        } else if (root.left == null && root.right == null) {
            return root
        }

        root.left?.let {invertTree(it)}
        root.right?.let {invertTree(it)}

        val temp = root.left
        root.left = root.right
        root.right = temp 
        return root
    }
}
