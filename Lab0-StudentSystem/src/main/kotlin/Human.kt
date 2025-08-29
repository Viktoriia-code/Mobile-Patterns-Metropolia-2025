package org.example

open class Human(val name: String, var age: Int) {
    fun getOlder() {
        age += 1
    }

    override fun toString(): String {
        return "Human(name='$name', age=$age)"
    }
}