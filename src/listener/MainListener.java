package listener;

import antlr.KotlinParser;
import antlr.KotlinParserBaseListener;
import listener.morph.Class;
import listener.morph.Function;

import java.util.ArrayList;

public class MainListener extends KotlinParserBaseListener {

    private String input;
    public String output = "";
    public ArrayList<String> variables = new ArrayList<>();

    public MainListener(String input) {
        this.input = input;
    }

    @Override
    public void enterSimpleIdentifier(KotlinParser.SimpleIdentifierContext ctx) {
        String content = input.substring(ctx.start.getStartIndex(), ctx.stop.getStopIndex()+1);
        if (!variables.contains(content))
            variables.add(content);
        super.enterSimpleIdentifier(ctx);
    }

    @Override
    public void enterClassDeclaration(KotlinParser.ClassDeclarationContext ctx) {
        System.out.println("Got here omg");
        output += Class.enterClassDeclaration(input, ctx);
        super.enterClassDeclaration(ctx);
    }

    @Override
    public void exitClassBody(KotlinParser.ClassBodyContext ctx) {
        output += "}";
        super.exitClassBody(ctx);
    }

    @Override
    public void enterFunctionDeclaration(KotlinParser.FunctionDeclarationContext ctx) {
        output += Function.enterFunctionDeclaration(ctx);
        super.enterFunctionDeclaration(ctx);
    }

    @Override
    public void enterFunctionBody(KotlinParser.FunctionBodyContext ctx) {
        output += input.substring(ctx.start.getStartIndex(), ctx.stop.getStopIndex() + 1);
        super.enterFunctionBody(ctx);
    }
}
