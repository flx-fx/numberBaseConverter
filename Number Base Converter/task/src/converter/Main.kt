package converter

import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode

fun main() {
    while (true) {
        println("Enter two numbers in format: {source base} {target base} (To quit type /exit)")
        val inputBase = readln()
        if (inputBase == "/exit") break
        else while (true) {
            val (sourceBase, targetBase) = inputBase.split(" ")
            println("Enter number in base $sourceBase to convert to base $targetBase (To go back type /back)")
            val inputSource = readln()
            if (inputSource == "/back") break
            else println(
                "Conversion result: ${
                    fromDecimal(targetBase.toBigInteger(), toDecimal(sourceBase.toBigInteger(), inputSource), 5)
                }\n"
            )
        }
    }
}

fun toDecimal(sourceBase: BigInteger, source: String): String {
    val integer: String
    val fraction: String?
    val sourceScale: Int

    if (source.contains(".")) {
        integer = source.split('.')[0].uppercase()
        fraction = source.split('.')[1].uppercase()
        sourceScale = fraction.length
    } else {
        integer = source.uppercase()
        fraction = null
        sourceScale = 0
    }

    val intReversed = integer.reversed()
    var integerResult = BigInteger.ZERO
    for (i in intReversed.indices) {
        integerResult += (if (intReversed[i] >= 'A') intReversed[i] - 'A' + 10 else intReversed[i].digitToInt())
            .toBigInteger() * sourceBase.pow(i)
    }

    if (fraction != null) {
        var fractionResult = BigDecimal.ZERO
        for (digit in fraction.indices) {
            fractionResult += (if (fraction[digit] >= 'A') fraction[digit] - 'A' + 10 else fraction[digit].digitToInt()).toBigDecimal()
                .setScale(sourceScale) / sourceBase.toBigDecimal().pow(digit + 1).setScale(sourceScale)
        }
        return (integerResult.toBigDecimal() + fractionResult).toString()
    }

    return integerResult.toString()
}

fun fromDecimal(targetBase: BigInteger, source: String, scale: Int = 0): String {
    var remScale = scale

    val integer: String
    val fraction: String?

    if (source.contains('.')) {
        integer = source.split('.')[0]
        fraction = source.split('.')[1]
    } else {
        integer = source
        fraction = null
    }

    var result = ""

    var quotient = integer.toBigInteger()
    do {
        val remainder = (quotient % targetBase).toInt()
        result += if (remainder >= 10) 'A' + remainder - 10 else remainder
        quotient /= targetBase
    } while (quotient > BigInteger.ZERO)
    result = result.reversed()

    if (fraction != null) {
        result += "."
        var product = "0.$fraction".toBigDecimal().setScale(scale, RoundingMode.HALF_UP)
        while (remScale-- > 0) {
            product *= targetBase.toBigDecimal()
            val intPart = product.toInt()
            product -= intPart.toBigDecimal()
            result += if (intPart >= 10) 'A' + intPart - 10 else intPart
        }
    }

    return result
}