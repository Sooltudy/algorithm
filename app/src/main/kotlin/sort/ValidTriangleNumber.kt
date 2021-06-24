package sort

class ValidTriangleNumber {
    fun triangleNumber(nums: IntArray): Int {
        val sorted = nums.sorted()
        return sorted.foldRightIndexed(0) { index, value, acc ->
            if (index < 2) acc
            else {
                var head = index - 1
                var tail = 0
                var count = 0

                while (head > tail) {
                    if (sorted[head] + sorted[tail] > value) {
                        count += head - tail
                        head--
                    } else {
                        tail++
                    }
                }

                acc + count
            }
        }
    }
}