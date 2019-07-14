package listener;

import antlr.KotlinParser;
import names.NameGenerator;
import typealias.TypeAliasGenerator;

import static listener.MainHelper.*;

class FunctionHelper {

    private static TypeAliasGenerator typeAliasGenerator = TypeAliasGenerator.getInstance();
    private static NameGenerator nameGenerator = NameGenerator.getInstance();

    static String onFunctionDeclaration(KotlinParser.FunctionDeclarationContext ctx) {
        String output = appendIfNotEmpty(ctx.modifiers());
        output += " fun";
        output += " " + nameGenerator.getNewName(ctx.simpleIdentifier().getText());
        output += " " + ctx.functionValueParameters().getText();

        if (ctx.type() != null && !ctx.type().isEmpty()) {
            output += ":" + typeAliasGenerator.getTypeAlias(ctx.type().getText());
        }

        return output;
    }
}
