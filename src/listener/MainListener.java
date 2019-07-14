package listener;

import antlr.KotlinParser;
import antlr.KotlinParserBaseListener;
import typealias.TypeAliasGenerator;

import static listener.ClassHelper.onClassDeclaration;
import static listener.FunctionHelper.onFunctionDeclaration;

public class MainListener extends KotlinParserBaseListener {

    private String input;
    public String output = "";
    private TypeAliasGenerator typeAliasGenerator = TypeAliasGenerator.getInstance();

    public MainListener(String input) {
        this.input = input;
    }

    @Override
    public void enterStringLiteral(KotlinParser.StringLiteralContext ctx) {
        System.out.println(ctx.getText());
        super.enterStringLiteral(ctx);
    }

    @Override
    public void enterClassDeclaration(KotlinParser.ClassDeclarationContext ctx) {
        output += onClassDeclaration(input, ctx);
        super.enterClassDeclaration(ctx);
    }

    @Override
    public void exitClassBody(KotlinParser.ClassBodyContext ctx) {
        output += "}";
        super.exitClassBody(ctx);
    }

    @Override
    public void enterFunctionDeclaration(KotlinParser.FunctionDeclarationContext ctx) {
        output += onFunctionDeclaration(ctx);
        super.enterFunctionDeclaration(ctx);
    }

    @Override
    public void enterFunctionBody(KotlinParser.FunctionBodyContext ctx) {
        output += input.substring(ctx.start.getStartIndex(), ctx.stop.getStopIndex() + 1);
        super.enterFunctionBody(ctx);
    }
}
