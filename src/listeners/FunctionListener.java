package listeners;

import antlr.KotlinParser;
import antlr.KotlinParserBaseListener;

import java.util.ArrayList;

public class FunctionListener extends KotlinParserBaseListener {

    private String initialText;
    ArrayList<String> variables = new ArrayList<>();
    public String output;

    public FunctionListener(String output) {
        this.output = output;
    }

    /*@Override
    public void enterSimpleIdentifier(KotlinParser.SimpleIdentifierContext ctx) {
        String content = initialText.substring(ctx.start.getStartIndex(), ctx.stop.getStopIndex()+1);
        if (!variables.contains(content))
            variables.add(content);
        super.enterSimpleIdentifier(ctx);
    }*/

    @Override
    public void enterClassDeclaration(KotlinParser.ClassDeclarationContext ctx) {
        output += " class";
        output += " " + ctx.simpleIdentifier().getText();

        if (ctx.delegationSpecifiers() != null && !ctx.delegationSpecifiers().isEmpty()) {
            output += " : ";
            output += ctx.delegationSpecifiers().getText();
        }

        output += " {\n";
        super.enterClassDeclaration(ctx);
    }

    @Override
    public void enterFunctionDeclaration(KotlinParser.FunctionDeclarationContext ctx) {
        output += " fun";
        output += " " + ctx.simpleIdentifier().getText(); // name
        output += " " + ctx.functionValueParameters().getText(); // params

        if (ctx.type() != null && !ctx.type().isEmpty()) {
            output += " : " + ctx.type().getText();
        }
        output += " { \n";

        super.enterFunctionDeclaration(ctx);
    }

    @Override
    public void exitFunctionBody(KotlinParser.FunctionBodyContext ctx) {
        output += "}\n";
        super.exitFunctionBody(ctx);
    }

    @Override
    public void exitClassBody(KotlinParser.ClassBodyContext ctx) {
        output += "}\n";
        super.exitClassBody(ctx);
    }
}
