class Solution {
    val counter: MutableMap<Char, Int> = mutableMapOf()
    val lastExecuted: MutableMap<Char, Int> = mutableMapOf()


    fun leastInterval(tasks: CharArray, n: Int): Int {
        val chars: MutableSet<Char> = mutableSetOf()
        for (char in tasks) {
            counter[char] = (counter[char] ?: 0) + 1 
            chars.add(char)
        }

        var currCycle = 0

        val freqSorted = chars.sortedBy {counter.getValue(it)}.reversed()
        val builder = StringBuilder(freqSorted.joinToString(""))

        while (builder.length > 0) {
            for (i in 0 until builder.length) {
                val char = builder[i]

                if (counter[char] == 0) {
                    builder.deleteCharAt(i)
                    break
                }

                if (!lastExecuted.contains(char) || currCycle - lastExecuted.getValue(char) > n) {
                    counter[char] = counter.getValue(char) - 1
                    lastExecuted[char] = currCycle
                    currCycle++

                    var j = i
                    while (j < builder.length-1 && counter.getValue(char) < counter.getValue(builder[j+1])) {
                        val temp = builder[j]
                        builder[j] = builder[j+1]
                        builder[j+1] = temp
                        j++
                    }
                    break
                } 

                if (i == builder.length-1) {
                    currCycle++
                }
            }
        }

        return currCycle
    }
}
