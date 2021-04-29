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
        val N = grid.size
        val qf = UnionFind(4 * N * N)
        for (i in 0 until N) {
            for (j in 0 until N) {
                val root = 4 * (i * N + j)
                val char: Char = grid[i][j]

                if (char != '\\') {
                    qf.union(root + 0, root + 1)
                    qf.union(root + 2, root + 3)
                }

                if (char != '/') {
                    qf.union(root + 0, root + 2)
                    qf.union(root + 1, root + 3)
                }

                if (i + 1 < N) {
                    qf.union(root + 3, (root + 4 * N) + 0)
                }
                if (i - 1 >= 0) {
                    qf.union(root + 0, (root - 4 * N) + 3)
                }

                if (j + 1 < N) {
                    qf.union(root + 2, (root + 4) + 1)
                }
                if (j - 1 >= 0) {
                    qf.union(root + 1, (root - 4) + 2)
                }
            }
        }

        return qf.roots
    }

    class UnionFind(N: Int) {
        var parent: IntArray = IntArray(N)
        var size: IntArray = IntArray(N)
        var roots: Int = N
        fun root(i: Int): Int {
            var i = i
            while (i != parent[i]) {
                parent[i] = parent[parent[i]] // compress
                i = parent[i]
            }
            return i
        }

        fun union(p: Int, q: Int) {
            val rootP = root(p)
            val rootQ = root(q)
            if (rootP == rootQ) return
            roots--
            if (size[rootP] < size[rootQ]) {
                parent[rootP] = rootQ
                size[rootQ] += size[rootP]
            } else {
                parent[rootQ] = rootP
                size[rootP] += size[rootQ]
            }
        }

        init {
            for (i in 0 until N) {
                parent[i] = i
                size[i] = 1
            }
        }
    }
}

fun main() {
    Solution().apply {
        require(regionsBySlashes(arrayOf(" /", "/ ")) == 2) // 2
        require(regionsBySlashes(arrayOf("/\\", "\\/")) == 5) // 5
        require(regionsBySlashes(arrayOf("//", "/ ")) == 3) // 3
    }
}