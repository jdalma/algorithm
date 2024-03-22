package baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader


fun main(args: Array<String>) = with (BufferedReader(InputStreamReader(System.`in`))) {
    val (nodeCount, edgeCount) = readLine().toInt() to readLine().toInt()
    val nodeRelation = Array(nodeCount + 1) { it }
    val sortedEdges = IntRange(1, edgeCount).map { Edge.valueOf(readLine()) }.sortedBy { it.cost }

    println(sortedEdges.sumOf { union(nodeRelation, it) })
}

fun find(relation: Array<Int>, node: Int): Int =
    if(relation[node] == node) node
    else find(relation, relation[node]).apply { relation[node] = this }

fun union(relation: Array<Int>, edge: Edge): Int {
    val (startNodeRoot, endNodeRoot) = find(relation, edge.start) to find(relation, edge.end)

    if (startNodeRoot == endNodeRoot) return 0
    relation[endNodeRoot] = startNodeRoot

    return edge.cost
}

data class Edge(
    val start: Int,
    val end: Int,
    val cost: Int
) {

    companion object {
        fun valueOf(line: String): Edge = line.split(" ").let {
            Edge(it[0].toInt(), it[1].toInt(), it[2].toInt())
        }
    }
}
