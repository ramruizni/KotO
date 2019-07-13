package listener.morph;

import antlr.KotlinParser;

import static listener.morph.Main.appendIfNotEmpty;
import static listener.morph.Main.appendReturnIfNotEmpty;

public class Function {

    public static String enterFunctionDeclaration(KotlinParser.FunctionDeclarationContext ctx) {
        String output = appendIfNotEmpty(ctx.modifiers());
        output += " fun";
        output += " " + ctx.simpleIdentifier().getText();
        output += " " + ctx.functionValueParameters().getText();
        return output + appendReturnIfNotEmpty(ctx.type());
    }
}
