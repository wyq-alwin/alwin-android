package com.alwin.leetcode

class Solution {
    // you need treat n as an unsigned value
    fun hammingWeight(n: Int): Int {
        var result = 0
        var n = n
        while (n != 0) {
            result++
            n = n.and(n - 1)
        }
        return result
    }
}