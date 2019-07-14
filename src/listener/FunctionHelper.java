package listener;

import antlr.KotlinParser;
import names.NameGenerator;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Interval;
import typealias.TypeAliasGenerator;

import static listener.MainHelper.appendIfNotEmpty;

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

    // Code taken in C++ form from:
    // https://stackoverflow.com/questions/50443728/context-gettext-excludes-spaces-in-antlr4
    static String sourceTextForContext(ParserRuleContext ctx) {
        CharStream cs = ctx.start.getTokenSource().getInputStream();
        int stopIndex = ctx.stop != null ? ctx.stop.getStopIndex() : -1;
        return cs.getText(new Interval(ctx.start.getStartIndex(), stopIndex));
    }
}
