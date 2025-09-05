package org.example

import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.withLock

class Lotto {
    private val correct: Set<Int> = (1..40).shuffled().take(7).toSet()
    // I chose an array of longs to practice locks
    private val stats = LongArray(8)
    private val lock = ReentrantReadWriteLock()
    private val writeLock = lock.writeLock()
    private val readLock = lock.readLock()

    fun check(numbers: Set<Int>) {
        val hits = numbers.count { it in correct }
        writeLock.withLock {
            stats[hits]++
        }
    }

    fun stats(): LongArray {
        readLock.withLock {
            return LongArray(8) { stats[it] }
        }
    }

    fun numbers(): Set<Int> = correct
}

fun randomGuess(): Set<Int> {
    val rnd = ThreadLocalRandom.current()
    val nums = mutableSetOf<Int>()
    while (nums.size < 7) {
        nums.add(rnd.nextInt(1, 41))
    }
    return nums
}

fun runMultiThread(totalGuesses: Int, threads: Int) {
    val lotto = Lotto()

    val perThread = totalGuesses / threads
    val remainder = totalGuesses % threads

    val workers = (0 until threads).map { t ->
        val myCount = perThread + if (t < remainder) 1 else 0
        Thread {
            println("Thread ${t+1}/$threads starting for $myCount guesses")
            repeat(myCount) {
                lotto.check(randomGuess())
            }
        }
    }

    val start = System.currentTimeMillis()
    workers.forEach { it.start() }
    workers.forEach { it.join() }
    val end = System.currentTimeMillis()

    println("All joined")
    println("Running time ${end - start} ms")
    val stats = lotto.stats()
    val checksum = stats.sum()
    println("Checksum: $checksum should be $totalGuesses")
    for (i in 0..7) {
        println("$i: ${stats[i]}")
    }
}


fun main() {
    val totalGuesses = 13500000
    val threads = Runtime.getRuntime().availableProcessors()
    runMultiThread(totalGuesses, threads)
}