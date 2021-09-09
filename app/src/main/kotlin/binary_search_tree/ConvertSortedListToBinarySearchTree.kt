package binary_search_tree

class ConvertSortedListToBinarySearchTree {
    private var node: ListNode? = null
    // -10 -3 0 5 9
    fun sortedListToBST(head: ListNode?): TreeNode? {
        if (head == null) return null
        val headList = mutableListOf<Int>()
        var header = head
        node = head
        while (header != null) {
            headList.add(header.`val`)
            header = header.next
        }

        val result = recursive(0, headList.size - 1)
        return result
    }

    private fun recursive(start: Int, end: Int): TreeNode? {
        if (node == null) return null
        if (start > end) return null

        val mid = start + (end - start) / 2
        val left = recursive(start, mid - 1)

        val treeNode = TreeNode(node!!.`val`)
        treeNode.left = left
        node = node?.next

        val right = recursive(mid + 1, end)
        treeNode.right = right

        return treeNode
    }

    data class ListNode(var `val`: Int, var next: ListNode? = null)

    data class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}

fun main() {
    val prob = ConvertSortedListToBinarySearchTree.ListNode(-10).apply {
        next = ConvertSortedListToBinarySearchTree.ListNode(-3).apply {
            next = ConvertSortedListToBinarySearchTree.ListNode(0).apply {
                next = ConvertSortedListToBinarySearchTree.ListNode(5).apply {
                    next = ConvertSortedListToBinarySearchTree.ListNode(9)
                }
            }
        }
    }

    println(ConvertSortedListToBinarySearchTree().sortedListToBST(prob))
}


