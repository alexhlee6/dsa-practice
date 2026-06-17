class Solution {
    val map: MutableMap<String, MutableList<String>> = mutableMapOf()

    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        for (word in strs) {
            val key = getMapKey(word)
            val list = map[key] ?: mutableListOf<String>()
            list.add(word)
            map[key] = list
        }

        val result: MutableList<List<String>> = mutableListOf()
        for ((k,v) in map) {
            result.add(v)
        }
        return result
    }

    fun getMapKey(word: String): String {
        val letters = word.split("").sorted()
        return letters.joinToString("")
    }
}
