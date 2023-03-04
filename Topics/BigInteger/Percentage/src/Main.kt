import java.math.BigInteger

fun main() {
    val a = readln().toBigInteger()
    val b = readln().toBigInteger()
    fun percentage(x: BigInteger) = x * "100".toBigInteger() / (a + b)
    println("${percentage(a)}% ${percentage(b)}%")
}