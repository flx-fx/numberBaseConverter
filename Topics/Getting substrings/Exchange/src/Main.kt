fun main() {
    val string = readln()
    println(string[string.length - 1].toString() + string.subSequence(1, string.length - 1) + string[0])
}