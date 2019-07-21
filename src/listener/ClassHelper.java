package listener;

import antlr.KotlinParser;
import names.NameGenerator;
import typealias.TypeAliasGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static listener.MainHelper.appendIfNotEmpty;

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

        return output;
    }

    static String onClassValueParameters(KotlinParser.ClassParametersContext ctx) {
        List<String> defComponents = new ArrayList<>(Arrays.asList("(", ")", ","));

        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < ctx.getChildCount(); i++) {
            if (defComponents.contains(ctx.getChild(i).getText())) {
                buffer.append(ctx.getChild(i).getText());
            } else {
                List<String> arr = new ArrayList<>();
                for (int j = 0; j < ctx.getChild(i).getChildCount(); j++) {
                    arr.add(ctx.getChild(i).getChild(j).getText());
                }
                int middleIndex = arr.indexOf(":");

                for (int j = 0; j < arr.size(); j++) {
                    String element = ctx.getChild(i).getChild(j).getText();
                    if (j == middleIndex - 1) {
                        buffer.append(nameGenerator.getNewName(element));
                    } else if (j == middleIndex + 1) {
                        buffer.append(typeAliasGenerator.getTypeAlias(element));
                    } else {
                        buffer.append(element);
                    }
                    buffer.append(" ");
                }
            }
        }
        return buffer.toString();
    }

}
