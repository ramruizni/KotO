package listener;

import antlr.KotlinParser;

import static listener.MainHelper.*;

class FunctionHelper {

    static String onFunctionDeclaration(KotlinParser.FunctionDeclarationContext ctx) {
        String output = appendIfNotEmpty(ctx.modifiers());
        output += " fun";
        output += " " + ctx.simpleIdentifier().getText();
        output += " " + ctx.functionValueParameters().getText();
        return output + appendReturnTypeIfNotEmpty(ctx.type());
    }
}
