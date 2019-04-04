package com.workshop.universityannouncementsboard.extra

// TODO: Clean it up
sealed class Tree {
    override fun toString(): String = when (this) {
        is Leaf -> value
        is Node -> "($left, $right)"
    }
}

class Leaf(val value: String) : Tree()
class Node(val left: Tree, val right: Tree) : Tree()