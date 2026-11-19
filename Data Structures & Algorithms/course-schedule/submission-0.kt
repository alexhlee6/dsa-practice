class Solution {
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val prereqs = mutableMapOf<Int, MutableSet<Int>>()
        val related = mutableMapOf<Int, MutableSet<Int>>()
        for (arr in prerequisites) {
            val (a,b) = arr
            val list = prereqs[a] ?: mutableSetOf<Int>()
            val list2 = related[b] ?: mutableSetOf<Int>()
            list.add(b)
            prereqs[a] = list
            list2.add(a)
            related[b] = list2
        }
        val noPrereqs = mutableSetOf<Int>()
        for (i in (0..numCourses-1)) {
            if (!prereqs.contains(i)) {
                noPrereqs.add(i)
            }
        }

        val coursesTaken = mutableSetOf<Int>()
        var queue = noPrereqs.toMutableList()
        var nextQueue = mutableListOf<Int>()

        while (queue.size > 0) {
            val last = queue.removeLast()
            if (coursesTaken.contains(last) || (prereqs[last]?.size ?: 0) != 0) {
                if (queue.size == 0) {
                    queue = nextQueue
                    nextQueue = mutableListOf()
                }
                continue
            }
            coursesTaken.add(last)
            val relatedCourses = related[last] ?: mutableSetOf<Int>()
            for (nextCourse in relatedCourses) {
                prereqs[nextCourse]?.remove(last)
                nextQueue.add(nextCourse)
            }

            if (queue.size == 0) {
                queue = nextQueue
                nextQueue = mutableListOf()
            }
        }
        return coursesTaken.size == numCourses
    }
}
