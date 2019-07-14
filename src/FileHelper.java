import antlr.KotlinLexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class FileHelper {

    static String readFileText() {
        try {
            return new String(Files.readAllBytes(Paths.get(Constants.INPUT_FILE_NAME)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    static CommonTokenStream getTokenStream() {
        CharStream charStream = null;
        try {
            charStream = CharStreams.fromFileName(Constants.OUTPUT_FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
        KotlinLexer lexer = new KotlinLexer(charStream);
        return new CommonTokenStream(lexer);
    }

    static void saveKotlinFileOutput(String output) {
        try {
            FileWriter fw = new FileWriter(Constants.OUTPUT_FILE_NAME);
            fw.write(output);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
