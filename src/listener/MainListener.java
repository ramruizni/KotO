package listener;

import antlr.KotlinParser;
import antlr.KotlinParserBaseListener;

import static listener.ClassHelper.onClassDeclaration;
import static listener.ClassHelper.onClassValueParameters;
import static listener.FunctionHelper.onFunctionDeclaration;
import static listener.MainHelper.sourceTextForContext;

public class MainListener extends KotlinParserBaseListener {

    private String input;
    public String output = "";

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
        super.enterFunctionBody(ctx);
    }

    /*
    @Override
    public void enterVariableDeclaration(KotlinParser.VariableDeclarationContext ctx) {
        //System.out.println(ctx.getText());
        super.enterVariableDeclaration(ctx);
    }
    */
}
