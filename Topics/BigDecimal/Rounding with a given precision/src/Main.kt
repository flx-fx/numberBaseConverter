import java.math.RoundingMode

fun main() = println(readln().toBigDecimal().setScale(readln().toInt(), RoundingMode.HALF_DOWN))