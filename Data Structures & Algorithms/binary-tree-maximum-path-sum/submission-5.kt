/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
  
    fun maxPathSum(root: TreeNode?): Int {
        root ?: return 0
        val res = getMaxPathSum(root)
        return max(res, maxPathThruNode)
    }

    var maxPathThruNode: Int = Int.MIN_VALUE

    fun getMaxPathSum(root: TreeNode):Int {
        if (root.left == null && root.right == null) {
            maxPathThruNode = max(maxPathThruNode, root.`val`)
            return root.`val`
        }

        val currVal = root.`val`

        val leftSum = root.left?.let { getMaxPathSum(it) } ?: 0
        val rightSum = root.right?.let { getMaxPathSum(it) } ?: 0
       
        var maxSum = currVal
        if (leftSum != null && leftSum > 0) {
            maxSum += leftSum
        }
        if (rightSum != null && rightSum > 0) {
            maxSum += rightSum
        }
        maxPathThruNode = max(maxPathThruNode, maxSum)
        
        return root.`val` + max(max(leftSum, 0), max(rightSum, 0))
    }
}
