package sort

import java.util.*
import kotlin.Comparator

class RussianDollEnvelopes {
    fun maxEnvelopes(envelopes: Array<IntArray>): Int {
        val sorted = envelopes.sortedWith(
            compareBy(
                Comparator { a: IntArray, b: IntArray ->
                    if (a.first() == b.first()) {
                        b.last() - a.last()
                    } else {
                        a.first() - b.first()
                    }
                }
            ) { it }
        )
        val sortedSet = TreeSet<Int>()

        sorted.forEach { item ->
            val ceiling = sortedSet.ceiling(item.last())
            if (ceiling != null && ceiling.toInt() != item.last()) sortedSet.remove(ceiling)
            sortedSet.add(item.last())
        }

        return sortedSet.size
    }
}