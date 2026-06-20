class Solution {
    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int) {
        var i = m-1; var j = n-1

        var fillIdx = nums1.size-1

        while (j >= 0) {
            if (nums1[fillIdx] != 0) {
                break
            } else if (i < 0) {
                nums1[fillIdx] = nums2[j]
                j--; fillIdx--
                continue 
            }

            if (nums2[j] >= nums1[i]) {
                nums1[fillIdx] = nums2[j]
                j--; fillIdx--
            } else {
                nums1[fillIdx] = nums1[i]
                nums1[i] = 0
                i--; fillIdx--
            }
        }
    }
}
