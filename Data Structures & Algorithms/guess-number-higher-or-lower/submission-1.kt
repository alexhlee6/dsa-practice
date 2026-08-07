/**
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return       -1 if num is higher than the picked number
 *                1 if num is lower than the picked number
 *               otherwise return 0
 * fun guess(num: Int): Int
 */
/** 
- b-search: low = 1, high = n
- while low < high
- take average of 2 numbers (mid), guess via API
- if 0 -> return mid 
- if -1 -> right = mid - 1
- if 1 -> left = mid + 1
*/
class Solution : GuessGame() {
    fun guessNumber(n: Int): Int {
        var low = 1; var high = n
        while (low < high) {
            val mid = (((low.toDouble() + high.toDouble()) / 2.0)).toInt()
            when (guess(mid)) {
                0 -> return mid
                -1 -> high = mid - 1
                else -> low = mid + 1
            }
        }
        return low
    }
}
