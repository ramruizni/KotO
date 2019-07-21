private class FirstClass: Any() {
    fun firstFunction(): Int {
        return 5
    }
}

class Cat {
    val height = 5.4
    fun getDescription(name: String, age: Int): String {
        val newString = name + FirstClass().firstFunction()
        return "Valuable string" + newString
    }
}

class Person(private var name: String = "Agapeto", val weight: Double = 8.45) {
    private fun printWeight() : String{
        return name +  "'s weight:  " + weight
    }
}