private class Lion : Any() {
    val var1: Int = 7
    public var var2: Double = 5.0
    lateinit var var3: String
    val var4 = "abc"
    fun sumVars(): Double {
        return var1 + var2
    }
}

class Cat {
    fun getDescription(name: String, age: Int = 8): String {
        val newString = name + " " + Lion().sumVars()
        return "Valuable string " + newString
    }
}

class Person(private var name: String = "Agapeto", val weight: Double = 8.45) {
    fun getStringWeight(): String {
        return name + "'s weight:  " + weight
    }
}

fun main() {
    println("Hello world!")
    println("Lion var1: " + Lion().var1)
    println("Lion var2: " + Lion().var2)

    println(Cat().getDescription("Mike", 4))
    println(Person("Michelle", 40.0).getStringWeight())
}