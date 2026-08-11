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

    fun buildRecurs(
        preorder: IntArray, 
        inorderNumToIdx: Map<Int,Int>, 
        i: Int = 0,
        inorderIdxRange: IntArray = intArrayOf(Int.MIN_VALUE, Int.MAX_VALUE)
    ): TreeNode? {
        if (i >= preorder.size) {
            return null
        } else if (i == preorder.size-1) {
            val inorderIdx = inorderNumToIdx[preorder[i]]!!
            if (inorderIdx >= inorderIdxRange[0] && inorderIdx <= inorderIdxRange[1]) {
                return TreeNode(preorder[i])
            } else {
                return null
            }
        }

        val num = preorder[i]
        val inorderIdx = inorderNumToIdx[num]!!
        if (inorderIdx < inorderIdxRange[0] || inorderIdx > inorderIdxRange[1]) {
            return null
        }

        val root = TreeNode(preorder[i])
        root.left = buildRecurs(preorder, inorderNumToIdx, i+1, intArrayOf(inorderIdxRange[0], inorderIdx-1))

        for (j in i+1 until preorder.size) {
            if (inorderNumToIdx[preorder[j]]!! > inorderIdxRange[1]) {
                break
            } else if (inorderNumToIdx[preorder[j]]!! > inorderIdx) {
                root.right = buildRecurs(preorder, inorderNumToIdx, j, intArrayOf(inorderIdx+1, inorderIdxRange[1]))
                return root
            }
        }

        return root


    }
}
