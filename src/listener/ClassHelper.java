package listener;

import antlr.KotlinParser;
import typealias.TypeAliasGenerator;

import static listener.MainHelper.*;

class ClassHelper {

    static String onClassDeclaration(String input, KotlinParser.ClassDeclarationContext ctx) {
        String output = appendIfNotEmpty(ctx.modifiers());
        if (ctx.modifiers() != null) {
            output += input.substring(ctx.modifiers().stop.getStopIndex() + 1, ctx.simpleIdentifier().start.getStartIndex() - 1);
        } else {
            output += input.substring(ctx.start.getStartIndex(), ctx.simpleIdentifier().start.getStartIndex() - 1);
        }
        output += " " + ctx.simpleIdentifier().getText();
        output += appendReturnTypeIfNotEmpty(ctx.delegationSpecifiers());

        return output + "{";
    }
}
