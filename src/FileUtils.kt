import antlr.KotlinLexer
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.FileWriter

fun getTokenStream(fileName: String): CommonTokenStream {
    val charStream = CharStreams.fromFileName(fileName)
    val lexer = KotlinLexer(charStream)
    return CommonTokenStream(lexer)
}

fun saveKotlinFileOutput(fineName: String, output: String) {
    try {
        val fw = FileWriter(fineName)
        fw.write(output)
        fw.close()
    } catch (e: Exception) {
        println(e)
    }
}