private class FirstClass: Object() {
    fun firstFunction(): Int {
        return 5
    }
}

class SecondClass {
    fun secondFunction(string: String): String {
        val newString = string //+ FirstClass().firstFunction()
        return "This is a valuable string" + newString
    }
}