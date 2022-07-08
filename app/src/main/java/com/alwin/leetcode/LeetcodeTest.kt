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
    fun replaceWords(dictionary: List<String>, sentence: String): String {
        val sb = StringBuilder()
        dictionary.sortedBy { it.length }
        sentence.split(" ").forEach {
            for (root in dictionary){
                if (it.startsWith(root)){
                    sb.append(root).append(' ')
                    return@forEach
                }
            }
            sb.append(it).append(' ')
        }
        return sb.deleteCharAt(sb.lastIndex).toString()
    }
}