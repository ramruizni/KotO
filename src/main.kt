import antlr.KotlinParser
import listeners.FunctionListener
import org.antlr.v4.runtime.tree.ParseTreeWalker
import utils.INPUT_FILE_NAME
import utils.OUTPUT_FILE_NAME
import utils.getTokenStream
import utils.saveKotlinFileOutput
import java.io.File

fun main() {

    for (i in 0..0) {

        val fileName = if (i == 0) {
            INPUT_FILE_NAME
        } else {
            OUTPUT_FILE_NAME
        }

        val parser = KotlinParser(getTokenStream(fileName))

        val listener = FunctionListener("")

        ParseTreeWalker().apply {
            walk(listener, parser.kotlinFile())
        }

        saveKotlinFileOutput(OUTPUT_FILE_NAME, listener.output)
    }
}