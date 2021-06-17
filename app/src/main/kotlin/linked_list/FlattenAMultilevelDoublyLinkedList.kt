package linked_list

/*   Input: head = [1, 2, 3, 4, 5, 6, null, null, null, 7, 8, 9, 10, null, null, 11, 12]*/
/*   Output: [1, 2, 3, 7, 8, 11, 12, 9, 10, 4, 5, 6]*/

class Solution {
    fun flatten(root: Node?): Node? {
        var node = root
        var temp: Node? = null
        while(true) {
            node = when {
                node?.child != null -> {
                    if (temp == null ) {
                        temp = node.next
                    } else {
                        if (node.next != null) {
                            val first = node.next
                            var n = first
                            while (n?.next != null) {
                                n = n.next
                            }
                            temp.prev = n
                            n?.next = temp
                            temp = first
                        }
                    }
                    node.child?.prev = node
                    node.next = node.child
                    node.child = null
                    node.next
                }
                node?.next != null -> {
                    node.next
                }
                else -> {
                    temp?.prev = node
                    node?.next = temp

                    return root
                }
            }
        }
    }
}

data class Node(
        var value: Int,
) {
    var prev: Node? = null
    var next: Node? = null
    var child: Node? = null
}

fun main() {
    val node1 = Node(1)
    val node2 = Node(2)
    val node3 = Node(3)
    val node4 = Node(4)
    val node5 = Node(5)
    val node6 = Node(6)
    val node7 = Node(7)
    val node8 = Node(8)
    val node9 = Node(9)
    val node10 = Node(10)
    val node11 = Node(11)
    val node12 = Node(12)

    node1.next = node2
    node2.prev = node1
    node2.next = node3
    node3.prev = node2
    node3.next = node4
    node4.prev = node3
    node4.next = node5
    node5.prev = node4
    node5.next = node6
    node6.prev = node5

    node3.child = node7
    node7.next = node8
    node8.prev = node7
    node8.next = node9
    node9.prev = node8
    node9.next = node10
    node10.prev = node9

    node8.child = node11
    node11.next = node12
    node12.prev = node11
/*

    val node1 = Node(1)
    val node2 = Node(2)
    val node3 = Node(3)

    node1.child = node2
    node2.child = node3
*/

//    var node: Node? = node1
    var node = Solution().flatten(node1)
    while (node != null) {
        println("${node.value} : ${node.prev} : ${node.next} : ${node.child}")

        node = node.next
    }
}