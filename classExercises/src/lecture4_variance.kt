import java.time.LocalDate

open class Person(val name: String, val yearOfBirth: Int = 2000): Comparable<Person> {
    override fun compareTo(other: Person) = other.yearOfBirth - this.yearOfBirth

    override fun toString() = "$name($yearOfBirth)"
}
class Student1(name: String, yearOfBirth: Int = 2000, var credits: Int = 0): Person(name, yearOfBirth) {
    override fun toString() = "${super.toString()}:$credits"
}

class Pair1<T>(val first: T, val second: T) {
    override fun toString() = "$first : $second"
    fun mix(newSecond: T): Pair1<T> = Pair1(first, newSecond) // does work with class Pair1<T> but not class Pair1<out T>
}

fun <T: Comparable<T>>older(pair: Pair1<T>): T = if(pair.first > pair.second) pair.first else pair.second
fun <T: Comparable<T>>older(pair: Pair2<T>): T = if(pair.first > pair.second) pair.first else pair.second

class Pair2<out T>(val first: T, val second: T) {
    override fun toString() = "$first : $second"
    //fun mix(newSecond: T): Pair2<T> = Pair2(first, newSecond) // does work with class Pair1<T> but not class Pair2<out T>
}

fun countOverAge(people: List<Person>, age: Int) = people.count { LocalDate.now().year - it.yearOfBirth > age }
fun countOverAge(people: Pair1<Person>, age: Int) = countOverAge(listOf(people.first, people.second), age)
fun countOverAge(people: Pair2<Person>, age: Int) = countOverAge(listOf(people.first, people.second), age)

class OutputChannel<in T> {
    fun write(x: T) = println(x)
}

// Student1 is subtype of Person
fun makeFriends(p: Pair1<Person>): String = "${p.first} + ${p.second}"

fun main() {
    val personPair1 = Pair1(Person("John"), Person("Jill"))
    val studentPair1 = Pair1(Student1("Bob"), Student1("Barbara"))

    println(countOverAge(personPair1, 10))
    // println(countOverAge(studentPair1, 10)) // no can do

    val personPair2 = Pair2(Person("John"), Person("Jill"))
    val studentPair2 = Pair2(Student1("Bob"), Student1("Barbara"))

    println(countOverAge(personPair2, 10))
    println(countOverAge(studentPair2, 10)) // now this works

    println(older(Pair1(Person("John", 1950), Student1("Jill", 1975))))
    println(older(Pair2(Person("John", 1950), Student1("Jill", 1975))))

    val personList = listOf(Person("John"), Person("Jill"))
    val studentList = listOf(Student1("Bob"), Student1("Barbara"))

    println(countOverAge(personList, 10))
    println(countOverAge(studentList, 10)) // what is the type declaration for List?

    var soc = OutputChannel<String>()
    var aoc = OutputChannel<Any>()

    soc.write("Hello")
    aoc.write("Goodbye")
    aoc.write(listOf("Hello", "world"))

    soc = aoc
}

