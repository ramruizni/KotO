package listener;

import antlr.KotlinParser;
import names.NameGenerator;
import typealias.TypeAliasGenerator;

import static listener.MainHelper.appendIfNotEmpty;
import static listener.MainHelper.sourceTextForContext;
import static listener.VariableHelper.fillLeftSideForSymbol;
import static listener.VariableHelper.fillRightSideForSymbol;

class FunctionHelper {

    private static TypeAliasGenerator typeAliasGenerator = TypeAliasGenerator.getInstance();
    private static NameGenerator nameGenerator = NameGenerator.getInstance();

    static String onFunctionDeclaration(KotlinParser.FunctionDeclarationContext ctx) {
        String output = appendIfNotEmpty(ctx.modifiers());
        output += "fun ";

        if (ctx.receiverType() != null) {
            output += typeAliasGenerator.getTypeAlias(ctx.receiverType().getText()) + ".";
        }

        output += nameGenerator.getNewName(ctx.simpleIdentifier().getText());

        output += " " + onFunctionValueParameters(ctx.functionValueParameters());

        if (ctx.type() != null && !ctx.type().isEmpty()) {
            output += ":" + typeAliasGenerator.getTypeAlias(ctx.type().getText());
        }
        return output;
    }

    private static String onFunctionValueParameters(KotlinParser.FunctionValueParametersContext ctx) {
        if (ctx.getChildCount() == 2) return ctx.getText();

        String content = sourceTextForContext(ctx);
        content = content.substring(0, content.length() - 1).substring(1);

        StringBuilder buffer = new StringBuilder();
        int colons = countCharInString(content, ',');
        for (int i = 0; i < colons + 1; i++) {
            String str = content.split(",")[i];

            fillLeftSideForSymbol(":", str, buffer);
            fillRightSideForSymbol(":", str, buffer);

            if (i != colons) buffer.append(",");
        }
        return "(" + buffer.toString() + ")";
    }

    private static int countCharInString(String str, char c) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c)
                count++;
        }
        return count;
    }
}
