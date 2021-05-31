package linked_list

/** by leetcode **/
class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

/** by leetcode **/
class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class LinkedListInBinaryTree {
    fun isSubPath(head: ListNode?, root: TreeNode?): Boolean = when {
        head == null || root == null -> false
        else -> root.findRec(head)
    }

    private fun TreeNode.findRec(node: ListNode): Boolean =
        (left?.findRec(node) ?: false) || this.find(node) || (right?.findRec(node) ?: false)

    private fun TreeNode.find(node: ListNode): Boolean = when (`val`) {
        node.`val` -> when (val next = node.next) {
            null -> true
            else -> (left?.find(next) ?: false) || (right?.find(next) ?: false)
        }
        else -> false
    }
}
