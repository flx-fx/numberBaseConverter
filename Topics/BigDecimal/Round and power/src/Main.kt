import java.math.RoundingMode

fun main() {
    val power = readln().toInt()
    val mode = readln().toInt()
    println(readln().toBigDecimal().setScale(mode, RoundingMode.FLOOR).pow(power))
}