package sort

import kotlin.math.abs

class MaximumElementAfterDecreasingAndRearranging {
    fun maximumElementAfterDecrementingAndRearranging(
        arr: IntArray
    ): Int = arr
        .sorted()
        .fold(0) { acc: Int, num: Int ->
            when {
                acc == 0 -> 1
                abs(num - acc) > 1 -> acc + 1
                else -> num
            }
        }
}