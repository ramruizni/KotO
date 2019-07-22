package listener;

import antlr.KotlinParser;
import antlr.KotlinParserBaseListener;
import variables.NumberInfixGenerator;
import variables.StringBitGenerator;

public class NumberInfixListener extends KotlinParserBaseListener {

    private NumberInfixGenerator numberInfixGenerator;
    public String output;
    private boolean insideClassParams;

    public NumberInfixListener(String input) {
        numberInfixGenerator = new NumberInfixGenerator(input);
    }

    @Override
    public void enterClassParameters(KotlinParser.ClassParametersContext ctx) {
        insideClassParams = true;
        super.enterClassParameters(ctx);
    }

    @Override
    public void exitClassParameters(KotlinParser.ClassParametersContext ctx) {
        insideClassParams = false;
        super.exitClassParameters(ctx);
    }

    @Override
    public void enterLiteralConstant(KotlinParser.LiteralConstantContext ctx) {
         if (!insideClassParams) {
            numberInfixGenerator.addIndexes(ctx.start.getStartIndex(), ctx.stop.getStopIndex() + 1);
        }
        super.enterLiteralConstant(ctx);
    }

    @Override
    public void exitKotlinFile(KotlinParser.KotlinFileContext ctx) {
        output = numberInfixGenerator.getOutput();
        super.exitKotlinFile(ctx);
    }
}
