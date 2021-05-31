package unionfind

class RegionsCutBySlashes2 {
    fun regionsBySlashes(grid: Array<String>): Int {
        if (grid.isEmpty() || grid.size > 30) throw IllegalStateException()
        fun index(x: Int, y: Int) = (y * grid.size * 2) + x

        val qf = QuickFind(Array(grid.size * grid.size * 4) { i -> i })
        grid.forEachIndexed { row, s ->
            s.forEachIndexed { col, c ->
                val x = col * 2
                val y = row * 2

                when (c) {
                    '/' -> {
                        qf.union(index(x, y), index(x + 1, y))
                        qf.union(index(x, y + 1), index(x + 1, y + 1))
                    }
                    '\\' -> {
                        qf.union(index(x, y), index(x, y + 1))
                        qf.union(index(x + 1, y), index(x + 1, y + 1))
                    }
                    else -> {
                        qf.union(index(x, y), index(x + 1, y))
                        qf.union(index(x, y), index(x, y + 1))
                        qf.union(index(x + 1, y + 1), index(x, y + 1))
                        qf.union(index(x + 1, y + 1), index(x + 1, y))
                    }
                }
                if (col < grid.size - 1) qf.union(index(x + 1, y + 1), index(x + 2, y))
                if (row < grid.size - 1) qf.union(index(x, y + 1), index(x + 1, y + 2))
            }
        }

        val result = mutableSetOf<Int>()
        for (i in 0 until (grid.size * grid.size * 4)) { result.add(qf.find(i)) }
        return result.size
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
    RegionsCutBySlashes2().apply {
        require(regionsBySlashes(arrayOf(" /", "/ ")) == 2) // 2
        require(regionsBySlashes(arrayOf("/\\", "\\/")) == 5) // 5
        require(regionsBySlashes(arrayOf("//", "/ ")) == 3) // 3
    }
}
