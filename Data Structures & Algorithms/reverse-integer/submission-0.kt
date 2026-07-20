class Solution {

    fun reverse(x: Int): Int {
        val isNeg = x < 0
        val x = if (isNeg) (-1 * x) else x
        val digits = getDigitsReversed(x)

        var res = 0.0
        var mult = 1.0

        while (digits.size > 0) {
            val last = digits.removeLast()
            res += (last * mult).toDouble()
            mult *= 10
        }

        val rangeStart = Math.pow(-2.0, 31.0)
        val rangeEnd = Math.pow(2.0, 31.0)-1
        val resWithNeg = if (isNeg) -1 * res else res

        if (resWithNeg !in rangeStart..rangeEnd) {
            return 0
        }
        return resWithNeg.toInt()
    }

    fun getDigitsReversed(num: Int): MutableList<Int> {
        val res: MutableList<Int> = mutableListOf()

        var curr = num
        while (curr > 0) {
            res.add(curr % 10)
            curr /= 10
        }
        return res
    }
}
