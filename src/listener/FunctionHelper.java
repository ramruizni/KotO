package listener;

import antlr.KotlinParser;
import names.NameGenerator;
import typealias.TypeAliasGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static listener.MainHelper.appendIfNotEmpty;

class FunctionHelper {

    private static TypeAliasGenerator typeAliasGenerator = TypeAliasGenerator.getInstance();
    private static NameGenerator nameGenerator = NameGenerator.getInstance();

    static String onFunctionDeclaration(KotlinParser.FunctionDeclarationContext ctx) {
        String output = appendIfNotEmpty(ctx.modifiers());
        output += " fun";
        output += " " + nameGenerator.getNewName(ctx.simpleIdentifier().getText());

        output += " " + onFunctionValueParameters(ctx.functionValueParameters());

        if (ctx.type() != null && !ctx.type().isEmpty()) {
            output += ":" + typeAliasGenerator.getTypeAlias(ctx.type().getText());
        }

        return output;
    }

    private static String onFunctionValueParameters(KotlinParser.FunctionValueParametersContext ctx) {
        if (ctx.getChildCount() == 2) return ctx.getText();

        List<String> defComponents = new ArrayList<>(Arrays.asList("(", ")", ","));

        StringBuilder buffer = new StringBuilder();
        if (ctx.getChildCount() > 2) {
            for (int i = 0; i < ctx.getChildCount(); i++) {
                String content = ctx.getChild(i).getText();
                if (defComponents.contains(content)) {
                    buffer.append(content);
                } else {
                    String[] elements = content.split(":");
                    buffer.append(nameGenerator.getNewName(elements[0]));
                    buffer.append(":");
                    buffer.append(elements[1]);
                }
            }
        }
        return buffer.toString();
    }
}
