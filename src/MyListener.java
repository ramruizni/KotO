import antlr.KotlinParser;
import antlr.KotlinParserBaseListener;

import java.util.ArrayList;

public class MyListener extends KotlinParserBaseListener {

    private String initialText;
    ArrayList<String> variables = new ArrayList<>();
    String output = "";

    public MyListener(String initialText) {
        this.initialText = initialText;
    }

    @Override
    public void enterClassDeclaration(KotlinParser.ClassDeclarationContext ctx) {
        output += " class";
        output += " " + ctx.getChild(1).getText();
        output += " {\n";
        super.enterClassDeclaration(ctx);
    }

    @Override
    public void exitClassBody(KotlinParser.ClassBodyContext ctx) {
        output += "\n }\n";
        super.exitClassBody(ctx);
    }

    @Override
    public void enterSimpleIdentifier(KotlinParser.SimpleIdentifierContext ctx) {
        String content = initialText.substring(ctx.start.getStartIndex(), ctx.stop.getStopIndex()+1);
        if (!variables.contains(content))
            variables.add(content);
        super.enterSimpleIdentifier(ctx);
    }

    @Override
    public void enterFunctionDeclaration(KotlinParser.FunctionDeclarationContext ctx) {
        output += " fun";
        output += " " + ctx.getChild(1).getText(); // name
        output += " " + ctx.getChild(2).getText(); // params

        if (ctx.type() != null && !ctx.type().isEmpty()) {
            output += " " + ctx.children.get(3).getText();
            output += " " + ctx.children.get(4).getText();
        }
        output += " { \n";

        String functionContent = "este es el nuevo contenido papu :D";

        output += functionContent;

        super.enterFunctionDeclaration(ctx);
    }

    @Override
    public void exitFunctionBody(KotlinParser.FunctionBodyContext ctx) {
        output += "\n }\n";
        super.exitFunctionBody(ctx);
    }
}
