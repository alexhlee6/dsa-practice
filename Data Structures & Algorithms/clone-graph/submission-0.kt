/*
Definition for a Node.
class Node(var `val`: Int) {
    var neighbors: ArrayList<Node?> = ArrayList()
}
*/

class Solution {
    // recursive: for each unvisited node, create clone in hashmap and call recursively for neighbors 
    // check hashmap to see if cloned node already exists at beginning (all neighbors should've already been visited)

    val cloneMap: MutableMap<Node, Node> = mutableMapOf()

    fun cloneGraph(node: Node?): Node? {
        node ?: return null
        if (cloneMap.contains(node)) {
            return cloneMap[node]
        }
        val newNode = Node(node.`val`)
        cloneMap[node] = newNode

        for (neighbor in node.neighbors) {
            newNode.neighbors.add(cloneGraph(neighbor))
        }
        return newNode
    }
}
