import antlr.KotlinParser
import listener.MainListener
import org.antlr.v4.runtime.tree.ParseTreeWalker
import utils.INPUT_FILE_NAME
import utils.OUTPUT_FILE_NAME
import utils.getTokenStream
import utils.saveKotlinFileOutput
import java.io.File

fun main() {

    saveKotlinFileOutput(OUTPUT_FILE_NAME, File(INPUT_FILE_NAME).readText())

    val parser = KotlinParser(getTokenStream(OUTPUT_FILE_NAME))
    val mainListener = MainListener(File(OUTPUT_FILE_NAME).readText())
    ParseTreeWalker().apply {
        walk(mainListener, parser.kotlinFile())
    }
    //println(classListener.output)
    //print(classListener.variables)
    saveKotlinFileOutput(OUTPUT_FILE_NAME, mainListener.output)

}