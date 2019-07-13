import antlr.KotlinParser
import org.antlr.v4.runtime.tree.ParseTreeWalker
import java.io.File

fun main() {

    for (i in 0..0) {

        val fileName = if (i == 0) {
            INPUT_FILE_NAME
        } else {
            OUTPUT_FILE_NAME
        }

        val parser = KotlinParser(getTokenStream(fileName))

        val listener = MyListener(File(fileName).readText())

        ParseTreeWalker().apply {
            walk(listener, parser.kotlinFile())
        }

        println(listener.variables)
        saveKotlinFileOutput(OUTPUT_FILE_NAME, listener.output)
    }
}