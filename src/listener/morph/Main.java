package listener.morph;

import org.antlr.v4.runtime.ParserRuleContext;

public class Main {

    public static String appendIfNotEmpty(ParserRuleContext ctx) {
        if (ctx != null) {
            return " " + ctx.getText();
        } return "";
    }

    public static String appendReturnIfNotEmpty(ParserRuleContext ctx) {
        if (ctx != null && !ctx.isEmpty()) {
            return ":" + ctx.getText();
        } return "";
    }

}