package listeners;

import antlr.KotlinParser;
import antlr.KotlinParserBaseListener;

public class ClassListener extends KotlinParserBaseListener {

    String output;

    public ClassListener(String output) {
        this.output = output;
    }

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
}
