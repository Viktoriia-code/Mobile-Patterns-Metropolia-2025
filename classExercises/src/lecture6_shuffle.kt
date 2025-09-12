import kotlin.time.measureTime

// naive implementation, does not produce uniform distribution
fun shuffle(list: MutableList<Int>) {
    for(i in list.indices) {
        val pick = (0..<list.size).random()
        list[ i ] = list[ pick ].also { list[ pick ] = list[ i ] }
    }
}

// fisher-yates shuffle (iteration in reverse order is not relevant but the pick range is)
fun fyShuffle(list: MutableList<Int>) {
    for(i in list.indices.reversed()) {
        val pick = (0..i).random()
        list[ i ] = list[ pick ].also { list[ pick ] = list[ i ] }
    }
}

// wrapper for recursive shuffle
fun shuffle1(list: MutableList<Int>) {
    val res = fyfShuffle(list)
    for(i in res.indices) list[i] = res[i]
}

// recursive variant
fun fyfShuffle(list: List<Int>): List<Int> =
    if(list.size <= 1) {
        list
    } else {
        val c = list.indices.random() + 1
        listOf(list.drop(c - 1).first()) + fyfShuffle(list.take(c - 1) + list.drop(c))
    }

fun kotlinShuffle(list: MutableList<Int>) {
    list.shuffle()
}

fun main() {
    tryShuffle(::shuffle1)
    val runningTime = measureTime { testShuffle(::shuffle1) }
    println(runningTime)
}

fun tryShuffle(f: (MutableList<Int>) -> Unit) {
    (1..10).forEach { _ ->
        val a = (0..10).toMutableList()
        f(a)
        println(a)
    }
}

// print averages -5.0 - should be close to 0
fun testShuffle(f: (MutableList<Int>) -> Unit) {
    val sums = MutableList(10, { -5.0 })

    val upper = 100000000
    (1..upper).forEach { _ ->
        val a = (0..10).toMutableList()
        f(a)
        for(i in sums.indices) {
            sums[ i ] += a[ i ].toDouble()
        }
    }
    for(i in sums.indices) {
        sums[ i ] /= upper.toDouble()
    }
    println(sums)
}