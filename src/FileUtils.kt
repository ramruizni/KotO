import antlr.KotlinLexer
import antlr.KotlinParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.FileWriter

fun getKotlinFileContext(fileName: String): KotlinParser.KotlinFileContext {
    val charStream = CharStreams.fromFileName(fileName)
    val lexer = KotlinLexer(charStream)
    val tokenStream = CommonTokenStream(lexer)
    val parser = KotlinParser(tokenStream)
    return parser.kotlinFile()
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