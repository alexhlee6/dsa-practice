class Solution {
    fun simplifyPath(path: String): String {
        val pathArr: MutableList<String> = mutableListOf()

        var i = 0
        while (i < path.length) {
            while (i < path.length && path[i] == '/') {
                i++
            }

            if (i >= path.length) {
                break
            }

            val segment = StringBuilder()
            var periodCount = 0

            var j = i
            while (j < path.length && path[j] != '/') {
                segment.append(path[j])
                if (path[j] == '.') {
                    periodCount++
                }
                j++ 
            }

            if (segment.length <= 2 && periodCount == segment.length) {
                if (periodCount == 2 && pathArr.size > 0) {
                    pathArr.removeLast()
                } 
                i = j
                continue
            }

            pathArr.add(segment.toString())
            i = j
        }



        return if (pathArr.size == 0) "/" else "/" + pathArr.joinToString("/")
    }
}
