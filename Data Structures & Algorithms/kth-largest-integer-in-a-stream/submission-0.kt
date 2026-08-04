class KthLargest(k: Int, nums: IntArray) {
    val arr: IntArray = IntArray(k) {Int.MIN_VALUE}
    init {
        for (num in nums) {
            addToStream(num)
        }
    }

    fun addToStream(num: Int) {
        if (num <= arr[0]) {
            return
        }
           
        arr[0] = num
        var i = 0

        while (i < arr.size - 1 && arr[i] > arr[i+1]) {
            val temp = arr[i+1]
            arr[i+1] = arr[i]
            arr[i] = temp
            i++
        }
    }

    fun add(`val`: Int): Int {
        addToStream(`val`)
        return arr[0]
    }
}
