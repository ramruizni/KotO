package listener;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Interval;

class MainHelper {

    static String appendIfNotEmpty(ParserRuleContext ctx) {
        if (ctx != null) {
            return ctx.getText() + " ";
        }
        return "";
    }

    // Code taken in C++ form from:
    // https://stackoverflow.com/questions/50443728/context-gettext-excludes-spaces-in-antlr4
    static String sourceTextForContext(ParserRuleContext ctx) {
        CharStream cs = ctx.start.getTokenSource().getInputStream();
        int stopIndex = ctx.stop != null ? ctx.stop.getStopIndex() : -1;
        return cs.getText(new Interval(ctx.start.getStartIndex(), stopIndex));
    }

}