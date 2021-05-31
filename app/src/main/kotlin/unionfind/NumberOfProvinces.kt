package unionfind

class NumberOfProvinces {
    fun findCircleNum(connectMap: Array<IntArray>): Int {
        val uf = QuickFind(connectMap.size)

        connectMap.forEachIndexed { index, node ->
            node.forEachIndexed { connectedIndex, isConnected ->
                if (isConnected == 1) uf.union(index, connectedIndex)
            }
        }

        val result = mutableSetOf<Int>()
        (connectMap.indices).forEach { index ->
            result.add(uf.find(index))
        }
        return result.size
    }

    class QuickFind(size: Int) {
        private val parent: Array<Int> = Array<Int>(size) { it }

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
