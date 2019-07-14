package listener;

import antlr.KotlinParser;
import antlr.KotlinParserBaseListener;
import typealias.TypeAliasGenerator;

import java.util.ArrayList;

import static listener.ClassHelper.onClassDeclaration;
import static listener.FunctionHelper.onFunctionDeclaration;

public class MainListener extends KotlinParserBaseListener {

    private String input;
    public String output = "";
    public ArrayList<String> variables = new ArrayList<>();
    private TypeAliasGenerator typeAliasGenerator = TypeAliasGenerator.getInstance();

    public MainListener(String input) {
        this.input = input;
    }

    @Override
    public void enterSimpleIdentifier(KotlinParser.SimpleIdentifierContext ctx) {
        String content = input.substring(ctx.start.getStartIndex(), ctx.stop.getStopIndex() + 1);
        if (!variables.contains(content))
            variables.add(content);
        super.enterSimpleIdentifier(ctx);
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

    @Override
    public void exitKotlinFile(KotlinParser.KotlinFileContext ctx) {
        output += typeAliasGenerator.getEndingOutput();
        super.exitKotlinFile(ctx);
    }
}
