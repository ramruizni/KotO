import antlr.KotlinParser;
import listener.MainListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Main {

    public static void main(String[] args) {

        FileHelper.saveKotlinFileOutput(FileHelper.readFileText());

        KotlinParser parser = new KotlinParser(FileHelper.getTokenStream());

        MainListener mainListener = new MainListener(FileHelper.readFileText());

        new ParseTreeWalker().walk(mainListener, parser.kotlinFile());

        System.out.println(mainListener.variables);
        FileHelper.saveKotlinFileOutput(mainListener.output);
    }
}
