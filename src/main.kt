import java.io.File

fun main() {
    //parser.addParseListener(MyListener())
    //parser.kotlinFile()

    val visitor = MyVisitor()
    for (i in 0..0) {

        val inputFileName = if (i == 0) {
            INPUT_FILE_NAME
        } else {
            OUTPUT_FILE_NAME
        }

        visitor.setInitialText(File(inputFileName).readText())
        visitor.visit(getKotlinFileContext(inputFileName))

        println(visitor.variables)
        saveKotlinFileOutput(OUTPUT_FILE_NAME, visitor.obfuscateInput())
    }
}