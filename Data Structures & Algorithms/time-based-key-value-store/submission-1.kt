class TimeMap() {
    // can store multiple values for the same key at different time stamps
    // can retrieve the key's value at a certain timestamp

    val map: MutableMap<String, MutableList<Pair<String, Int>>> = mutableMapOf()

    fun set(key: String, value: String, timestamp: Int) {
        val list = map[key] ?: mutableListOf<Pair<String, Int>>()

        list.add(Pair(value, timestamp))

        var idx = list.size-1
        while (idx > 0) {
            val currItem = list[idx]
            val prevItem = list[idx - 1]
            if (prevItem.second > currItem.second) {
                list[idx-1] = currItem
                list[idx] = prevItem
                idx--
            } else {
                break
            }
        }
        map[key] = list
    }

    fun get(key: String, timestamp: Int): String {
        if (!map.contains(key)) {
            return ""
        }
        val list = map.getValue(key)
        var idx = list.size-1
        while (idx >= 0) {
            val pair = list[idx]
            if (pair.second <= timestamp) {
                return pair.first
            }
            idx--
        }
        return ""
    }
}
