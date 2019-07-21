package listener;

import antlr.KotlinParser;
import names.NameGenerator;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
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

        return output;
    }

    static String onClassValueParameters(KotlinParser.ClassParametersContext ctx) {
        if (ctx.getChildCount() == 2) return ctx.getText();

        StringBuilder buffer = new StringBuilder();
        if (ctx.getChildCount() > 2) {
            for (int i = 0; i < ctx.getChildCount(); i++) {
                String content = ctx.getChild(i).getText();
                if (content.equals("(") || content.equals(")") || content.equals(",")) {
                    buffer.append(content);
                } else {
                    for (int j = 0; j < ctx.getChild(i).getChildCount(); j++) {
                        String element = ctx.getChild(i).getChild(j).getText();
                        if (element.equals("val") || element.equals("var") || element.equals("private") ||
                                element.equals("public") || element.equals("protected") || element.equals(":")
                        || typeAliasGenerator.getMap().containsKey(element)) {
                            buffer.append(element);
                            buffer.append(" ");
                        } else {
                            buffer.append(nameGenerator.getNewName(element));
                        }
                    }
                }
            }
        }

        return buffer.toString();
    }
}
