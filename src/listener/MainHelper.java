package listener;

import org.antlr.v4.runtime.ParserRuleContext;
import typealias.TypeAliasGenerator;

class MainHelper {

    private static TypeAliasGenerator typeAliasGenerator = TypeAliasGenerator.getInstance();

    static String appendIfNotEmpty(ParserRuleContext ctx) {
        if (ctx != null) {
            return " " + ctx.getText();
        } return "";
    }

}