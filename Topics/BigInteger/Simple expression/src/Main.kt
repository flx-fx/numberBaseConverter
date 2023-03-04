fun main() {
    fun readBigInt() = readln().toBigInteger()
    println(-readBigInt() * readBigInt() + readBigInt() - readBigInt())
}