fun main() {
    val string = readln()
    println(string.replaceAfterLast('u', string.substringAfterLast('u').uppercase()))
}