/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
 /**
 - preorder: root -> left -> right
 - inorder: left -> root -> right

 - root is preorder[0]
 - map every inorder element to index -> can tell us if left or right of current node 
 - 
 */

class Solution {
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        val inorderIdx = mutableMapOf<Int, Int>()
        for (i in 0 until inorder.size) {
            inorderIdx[inorder[i]] = i
        }

        if (preorder.size == 1) {
            return TreeNode(preorder[0])
        }

        return buildRecurs(preorder, inorderIdx)
    }

    var preorderIdx: Int = 0

    fun buildRecurs(
        preorder: IntArray, 
        inorderNumToIdx: Map<Int,Int>, 
        low: Int = Int.MIN_VALUE,
        high: Int = Int.MAX_VALUE
    ): TreeNode? {
        if (preorderIdx >= preorder.size) {
            return null
        } else if (low > high) {
            return null
        }

        val num = preorder[preorderIdx]
        val inorderIdx = inorderNumToIdx[num]!!
        if (inorderIdx < low || inorderIdx > high) {
            return null
        }

        val root = TreeNode(preorder[preorderIdx])
        preorderIdx++

        root.left = buildRecurs(preorder, inorderNumToIdx, low, inorderIdx-1)

        if (high == inorderIdx) {
            return root
        }

        root.right = buildRecurs(preorder, inorderNumToIdx, inorderIdx+1, high)

        return root


    }
}
