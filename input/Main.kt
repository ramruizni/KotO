private class FirstClass : Any() {
    private val var1: Int = 7
    public var var2: Double = 5.4
    lateinit var var3: String
    val var4 = "abc"
    fun firstFunction(): Int {
        return 5
    }
}

class Cat {
    fun getDescription(name: String, age: Int): String {
        val newString = name + FirstClass().firstFunction()
        return "Valuable string" + newString
    }
}

class Person(private var name: String = "Agapeto", val weight: Double = 8.45) {
    private fun printWeight(): String {
        return name + "'s weight:  " + weight
    }
}

fun main() {
    println("Hello world!")
}