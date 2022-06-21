package com.alwin.leetcode

class Node(var `val`: Int) {
    var next: Node? = null
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

fun main() {
}

class Solution {
    val map = mutableMapOf<Int, Int>()

    fun findFrequentTreeSum(root: TreeNode?): IntArray {
        root ?: return intArrayOf()
        help(root)
        var maxCount = 0
        for (i in map.values) {
            if (i > maxCount) {
                maxCount = i
            }
        }
        val result = mutableListOf<Int>()
        for (i in map.keys) {
            if (map[i]!! == maxCount) {
                result.add(i)
            }
        }
        return result.toIntArray()
    }

    fun help(root: TreeNode?): Int {
        root ?: return 0
        val left = help(root.left)
        val right = help(root.right)
        val cur = left + right + root.`val`
        if (!map.containsKey(cur)) {
            map[cur] = 0
        }
        map[cur] = map[cur]!! + 1
        return cur
    }
}