import antlr.KotlinParser;
import antlr.KotlinParserBaseVisitor;

import java.util.ArrayList;

public class MyVisitor extends KotlinParserBaseVisitor<Object> {

    private String initialText;
    private int seed = 97;
    ArrayList<String> variables;

    @Override
    public Object visitDeclaration(KotlinParser.DeclarationContext ctx) {
        return super.visitDeclaration(ctx);
    }

    @Override
    public Object visitSimpleIdentifier(KotlinParser.SimpleIdentifierContext ctx) {
        String content = initialText.substring(ctx.start.getStartIndex(), ctx.stop.getStopIndex()+1);
        if (!variables.contains(content))
        variables.add(content);
        return super.visitSimpleIdentifier(ctx);
    }

    @Override
    public Object visitFunctionDeclaration(KotlinParser.FunctionDeclarationContext ctx) {
        return super.visitFunctionDeclaration(ctx);
    }

    @Override
    public Object visitFunctionBody(KotlinParser.FunctionBodyContext ctx) {
        return super.visitFunctionBody(ctx);
    }

    public void setInitialText(String initialText) {
        this.initialText = initialText;
        variables = new ArrayList<>();
    }

    public String obfuscateInput() {
        String output = initialText;
        for (String var : variables) {
            output = output.replaceAll(var, (Character.toString((char)seed++)));
        }
        return output;
    }
}
