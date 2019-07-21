package listener;

import antlr.KotlinParser;
import antlr.KotlinParserBaseListener;
import org.antlr.v4.runtime.ParserRuleContext;

import static listener.ClassHelper.onClassDeclaration;
import static listener.ClassHelper.onClassValueParameters;
import static listener.FunctionHelper.onFunctionDeclaration;
import static listener.MainHelper.sourceTextForContext;
import static listener.VariableHelper.onVariableDeclaration;

public class MainListener extends KotlinParserBaseListener {

    private String input;
    public String output = "";
    private boolean insideFunction;

    public MainListener(String input) {
        this.input = input;
    }

    @Override
    public void enterClassDeclaration(KotlinParser.ClassDeclarationContext ctx) {
        output += onClassDeclaration(input, ctx);
        super.enterClassDeclaration(ctx);
    }

    @Override
    public void enterClassParameters(KotlinParser.ClassParametersContext ctx) {
        output += onClassValueParameters(ctx);
        super.enterClassParameters(ctx);
    }

    @Override
    public void enterClassBody(KotlinParser.ClassBodyContext ctx) {
        output += "{\n";
        super.enterClassBody(ctx);
    }

    @Override
    public void exitClassBody(KotlinParser.ClassBodyContext ctx) {
        output += "}\n";
        super.exitClassBody(ctx);
    }

    @Override
    public void enterFunctionDeclaration(KotlinParser.FunctionDeclarationContext ctx) {
        output += onFunctionDeclaration(ctx);
        super.enterFunctionDeclaration(ctx);
    }

    @Override
    public void enterFunctionBody(KotlinParser.FunctionBodyContext ctx) {
        output += sourceTextForContext(ctx);
        insideFunction = true;
        super.enterFunctionBody(ctx);
    }

    @Override
    public void exitFunctionBody(KotlinParser.FunctionBodyContext ctx) {
        insideFunction = false;
        super.exitFunctionBody(ctx);
    }

    @Override
    public void enterVariableDeclaration(KotlinParser.VariableDeclarationContext ctx) {
        if (!insideFunction) {
            output += onVariableDeclaration((ParserRuleContext) ctx.parent);
        }
        super.enterVariableDeclaration(ctx);
    }

}
