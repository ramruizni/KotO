import antlr.KotlinParser;
import listener.InsideFunctionListener;
import listener.MainListener;
import listener.StringBitListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Main {

    public static void main(String[] args) {

        String original = FileHelper.readFileText(Constants.INPUT_FILE_NAME);
        FileHelper.saveKotlinFileOutput(original);

        KotlinParser parser = new KotlinParser(FileHelper.getTokenStream());
        MainListener mainListener =
                new MainListener(FileHelper.readFileText(Constants.OUTPUT_FILE_NAME));
        new ParseTreeWalker().walk(mainListener, parser.kotlinFile());
        FileHelper.saveKotlinFileOutput(mainListener.output);

        parser = new KotlinParser(FileHelper.getTokenStream());
        InsideFunctionListener insideFunctionListener =
                new InsideFunctionListener(FileHelper.readFileText(Constants.OUTPUT_FILE_NAME));
        new ParseTreeWalker().walk(insideFunctionListener, parser.kotlinFile());
        FileHelper.saveKotlinFileOutput(insideFunctionListener.output);

        parser = new KotlinParser(FileHelper.getTokenStream());
        StringBitListener stringBitListener =
                new StringBitListener(FileHelper.readFileText(Constants.OUTPUT_FILE_NAME));
        new ParseTreeWalker().walk(stringBitListener, parser.kotlinFile());
        FileHelper.saveKotlinFileOutput(stringBitListener.output);
    }
}
