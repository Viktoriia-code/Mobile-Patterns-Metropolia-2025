
// This example is from Odersky et al: “Programming in Scala, 3rd Edition”

// this fifo implementation has amortised O(1) complexity for append() and tail() (dequeue())
class Fifo<T>(private val leading: List<T> = listOf(), private val trailing: List<T> = listOf()) {
    private fun mirror() = if(leading.isEmpty()) Fifo(trailing.reversed(), listOf()) else this

    fun head() = mirror().leading.first()
    fun tail(): Fifo<T> {
        val q = mirror()
        return Fifo(q.leading.drop(1), q.trailing)
    }
    fun append(e: T) = Fifo(leading, listOf(e) + trailing)

    override fun toString() = "leading: $leading, trailing: $trailing"
}

fun main() {
    var f = Fifo<String>()
    f = f.append("In")
    f = f.append("Xanadu")
    f = f.append("did")
    f = f.append("Kublai")
    f = f.append("Khan")
    println(f)
    println(f.head())
    println(f)
    f = f.tail()
    println(f.head())
    println(f)
    f = f.append("a")
    f = f.append("stately")
    println(f)
    f = f.tail()
    println(f)
    f = f.append("pleasure")
    println(f)
}