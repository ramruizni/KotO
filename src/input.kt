private class FirstClass: Any() {
    fun firstFunction(): Int {
        return 5
    }
}

class SecondClass {
    val element = 5.4
    fun secondFunction(string: String, age: Int): String {
        val newString = string + FirstClass().firstFunction()
        return "This is a valuable string" + newString
    }
}



class ThirdClass(private var name: String, val age: Int) {
    private fun printName() : String{
        return name + age
    }
}