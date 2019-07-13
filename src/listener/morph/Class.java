package listener.morph;

import antlr.KotlinParser;

import static listener.morph.Main.appendIfNotEmpty;
import static listener.morph.Main.appendReturnIfNotEmpty;

public class Class {

    public static String enterClassDeclaration(String input, KotlinParser.ClassDeclarationContext ctx) {
        String output = appendIfNotEmpty(ctx.modifiers());
        if (ctx.modifiers() != null) {
            output += input.substring(ctx.modifiers().stop.getStopIndex() + 1, ctx.simpleIdentifier().start.getStartIndex() - 1);
        } else {
            output += input.substring(ctx.start.getStartIndex(), ctx.simpleIdentifier().start.getStartIndex() - 1);
        }
        output += " " + ctx.simpleIdentifier().getText();
        output += appendReturnIfNotEmpty(ctx.delegationSpecifiers());

        return output + "{";
    }
}
