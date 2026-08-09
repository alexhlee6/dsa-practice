/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Solution {
    fun rightSideView(root: TreeNode?): List<Int> {
        var queue = mutableListOf<TreeNode>()
        root?.let { queue.add(it) } ?: return listOf<Int>()

        var nextQueue = mutableListOf<TreeNode>()
        val res = mutableListOf<Int>()

        while (queue.size > 0) {
            for (node in queue) {
                node.left?.let {nextQueue.add(it)}
                node.right?.let {nextQueue.add(it)}
            }
            val last = queue.removeLast()
            res.add(last.`val`)
            queue = nextQueue
            nextQueue = mutableListOf()
        }
        
        return res
    }
}
