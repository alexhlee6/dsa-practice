class Solution {
    // courses: (0..numCourses-1)
    // prerequisites[i] = [a, b] -> means a is prereq of b
    // queries[i] = [a,b] -> answer[i] = whether a is prereq of b

    // brute force-ish: 
    // - iterate over prereqs, map each course to set of courses that must be taken as prereq (prereqMap)
    // - for each query, first check prereqMap[queries[i][1]].contains(queries[i][0])
    // - if false, recursively call for all prereq courses in prereqMap[queries[i][1]]
    // - save result for repeated lookups (memoize)

    fun checkIfPrerequisite(numCourses: Int, prerequisites: Array<IntArray>, queries: Array<IntArray>): List<Boolean> {
        val prereqMap: MutableMap<Int, MutableSet<Int>> = mutableMapOf()
        for (courseNum in 0 until numCourses) {
            prereqMap[courseNum] = mutableSetOf<Int>()
        }

        for (i in 0 until prerequisites.size) {
            val arr = prerequisites[i]
            val pre = arr[0]; val post = arr[1]
            prereqMap[post]!!.add(pre)
        }

        val answer = MutableList(queries.size) {false}
        for (i in 0 until queries.size) {
            val query = queries[i]
            val pre = query[0]; val post = query[1]
            answer[i] = isPrereq(prereqMap, pre, post)
        }

        return answer
    }

    fun isPrereq(
        prereqMap: MutableMap<Int, MutableSet<Int>>, 
        pre: Int, 
        post: Int, 
        visited: MutableSet<Int> = mutableSetOf()
    ): Boolean {
        if (prereqMap[post]!!.contains(pre)) {
            return true
        } else if (visited.contains(post)) {
            return false
        }
        visited.add(post)
        val result = prereqMap.getValue(post).any { isPrereq(prereqMap, pre, it, visited) }
        if (result) {
            prereqMap[post]!!.add(pre)
        }
        return result
    }
}
