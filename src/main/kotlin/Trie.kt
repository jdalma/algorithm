fun main(args: Array<String>) {

    Trie().let {
        it.insert("app")
        it.insert("appl")
        it.insert("apple")
        println(it.longestPrefixOf("a"))
        println(it.keysThatMatch("app"))
    }

}

class Trie {

    companion object {
        private val R = 25
    }

    class Node(
        var value: String? = null,
        val next: Array<Node?> = arrayOfNulls(R + 1)
    )

    private var root = Node()

    fun insert(word: String) {
        this.root = put(root, word, 0)
    }

    //  문자열을 인수로 받아 그 문자열의 접두어인 문자열을 심볼 테이블에서 찾아서 그중에서 가장 긴 문자열을 리턴한다.
    fun longestPrefixOf(word: String) : String {
        val length = this.get(this.root, word, 0, 0)
        return word.substring(0, length)
    }

    // 패턴에 해당하는 모든 키를 수집한다.
    // 패턴 문자가 와일드카드인 경우 모든 링크에 재귀 호출을 하고, 그렇지 않은 경우 해당 문자에 해당하는 링크만 재귀 호출한다.
    fun keysThatMatch(key: String) : Iterable<String> {
        val list = mutableListOf<String>()
        collect(root, "", key, list)
        return list
    }

    private fun collect(node: Node?, prefix: String, pattern: String, list: MutableList<String>) {
        if (node == null) return
        if (prefix.length == pattern.length && node.value != null) {
            list.add(prefix)
        }
        if (prefix.length == pattern.length) return

        val next = pattern[prefix.length]
        for (code in 0 until R) {
            if (next == '.' || next == code.toChar()) {
                collect(node.next[next.code], prefix + next, pattern, list)
            }
        }
    }

    private fun put(node: Node?, word: String, index: Int) : Node {
        var nowNode = node
        if (nowNode == null) {
            nowNode = Node()
        }
        if (index == word.length) {
            nowNode.value = word
            return nowNode
        }

        val char = word[index].code
        nowNode.next[char - 97] = put(nowNode.next[char - 97], word, index + 1)
        return nowNode
    }
    private fun get(node: Node?, word: String, index: Int, length: Int) : Int {
        var wordLength = length
        if (node == null) return wordLength
        if (node.value != null) wordLength = index
        if (index == word.length) return wordLength

        val char = word[index].code
        return this.get(node.next[char - 97], word, index + 1, wordLength)
    }
}
