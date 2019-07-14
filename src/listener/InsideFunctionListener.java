package listener;

import antlr.KotlinParser;
import antlr.KotlinParserBaseListener;
import names.NameGenerator;
import typealias.TypeAliasGenerator;

import java.util.Map;

public class InsideFunctionListener extends KotlinParserBaseListener {

    private static Map<String, String> typeMap = TypeAliasGenerator.getInstance().getMap();
    private static Map<String, String> nameMap = NameGenerator.getInstance().getMap();

    private String input;
    public String output = "";
    private int prev = 0;

    public InsideFunctionListener(String input) {
        this.input = input;
    }

    @Override
    public void enterSimpleIdentifier(KotlinParser.SimpleIdentifierContext ctx) {
        String key = ctx.getText();
        int next;
        if (nameMap.containsKey(key)) {
            next = ctx.start.getStartIndex();

            output += input.substring(prev, next);
            output += nameMap.get(key);
            prev = ctx.stop.getStopIndex() + 1;
        }
        if (typeMap.containsKey(key)) {
            next = ctx.start.getStartIndex();

            output += input.substring(prev, next);
            output += typeMap.get(key);
            prev = ctx.stop.getStopIndex() + 1;
        }

        super.enterSimpleIdentifier(ctx);
    }

    @Override
    public void exitKotlinFile(KotlinParser.KotlinFileContext ctx) {
        output += input.substring(prev);
        output += TypeAliasGenerator.getInstance().getEndingOutput();
        super.exitKotlinFile(ctx);
    }
}
