package listener;

import antlr.KotlinParser;
import antlr.KotlinParserBaseListener;
import strings.StringBitGenerator;

public class StringBitListener extends KotlinParserBaseListener {

    private StringBitGenerator stringBitGenerator;
    public String output;

    public StringBitListener(String input) {
        stringBitGenerator = new StringBitGenerator(input);
    }

    @Override
    public void enterStringLiteral(KotlinParser.StringLiteralContext ctx) {
        stringBitGenerator.addIndexes(ctx.start.getStartIndex(), ctx.stop.getStopIndex() + 1);
        super.enterStringLiteral(ctx);
    }

    @Override
    public void exitKotlinFile(KotlinParser.KotlinFileContext ctx) {
        output = stringBitGenerator.getOutput();
        super.exitKotlinFile(ctx);
    }
}
