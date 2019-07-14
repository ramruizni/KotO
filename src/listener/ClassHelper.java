package listener;

import antlr.KotlinParser;
import names.NameGenerator;
import typealias.TypeAliasGenerator;

import static listener.MainHelper.*;

class ClassHelper {

    private static TypeAliasGenerator typeAliasGenerator = TypeAliasGenerator.getInstance();
    private static NameGenerator nameGenerator = NameGenerator.getInstance();

    static String onClassDeclaration(String input, KotlinParser.ClassDeclarationContext ctx) {
        String output = appendIfNotEmpty(ctx.modifiers());
        if (ctx.modifiers() != null) {
            output += input.substring(ctx.modifiers().stop.getStopIndex() + 1, ctx.simpleIdentifier().start.getStartIndex() - 1);
        } else {
            output += input.substring(ctx.start.getStartIndex(), ctx.simpleIdentifier().start.getStartIndex() - 1);
        }
        output += " " + nameGenerator.getNewName(ctx.simpleIdentifier().getText());

        if (ctx.delegationSpecifiers() != null && !ctx.delegationSpecifiers().isEmpty()) {
            output += ":" + typeAliasGenerator.getTypeAlias(
                    ctx.delegationSpecifiers().getText().replace("()", "")) + ("()");
        }

        return output + "{\n";
    }
}
