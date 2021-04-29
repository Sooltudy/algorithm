package unionfind

/*
*
* In a N x N grid composed of 1 x 1 squares, each 1 x 1 square consists of a /, \, or blank space.  These characters divide the square into contiguous regions.
(Note that backslash characters are escaped, so a \ is represented as "\\".)
Return the number of regions.
*
*
[
  " /",
  "/ "
]
* Output: 2
*
[
  " /",
  "  "
]
Output: 1
*
* */

class Solution {
    fun regionsBySlashes(grid: Array<String>): Int {
        if (grid.isEmpty() || grid.size > 30) throw IllegalStateException()

        val qf = QuickFind(Array(grid.size) { 0 })

        val sizeDouble = grid.size
        val temp = Array(sizeDouble) {
            Array(sizeDouble) {
                0
            }
        }

        val space = ' '
        grid.forEachIndexed { index1, s ->
            s.forEachIndexed { index2, c ->
                temp[index1][index2] = when (c) {
                    space -> 0
                    '/' -> 1
                    else -> 2
                }
            }
        }

        return 0
    }

    class QuickFind<T>(private val parent: Array<T>) {
        fun find(p: Int) = parent[p]

        fun union(p: Int, q: Int) {
            val pId = find(p)
            val qId = find(q)

            if (pId == qId) return

            parent.forEachIndexed { index, _ ->
                if (parent[index] == pId) parent[index] = qId
            }
        }
    }
}

fun main() {
    Solution().apply {
        regionsBySlashes(arrayOf(" /", "/ ")) // 2
        regionsBySlashes(arrayOf("""/\""", """\/""")) // 5
        regionsBySlashes(arrayOf("//", "/ ")) // 3
    }
}