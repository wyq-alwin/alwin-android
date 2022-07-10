package com.alwin.leetcode

class Node(var `val`: Int) {
    var left: Node? = null
    var right: Node? = null
    var next: Node? = null
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

fun main() {
}

class Solution {
    fun lenLongestFibSubseq(arr: IntArray): Int {
        if (arr.size < 3) {
            return 0
        }
        var result = 0
        val indices = mutableMapOf<Int, Int>()
        for (i in arr.indices) {
            indices[arr[i]] = i
        }
        val dp = Array(arr.size) {
            IntArray(arr.size)
        }
        for (i in arr.indices) {
            var j = i - 1
            while (j >= 0 && arr[j] * 2 > arr[i]) {
                indices[arr[i] - arr[j]]?.let {
                    dp[j][i] = Math.max(dp[it][j] + 1, 3)
                }
                result = Math.max(result, dp[j][i])
                j--
            }
        }
        return result
    }
}