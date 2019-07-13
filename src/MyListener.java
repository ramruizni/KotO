import antlr.KotlinParser;
import antlr.KotlinParserBaseListener;

public class MyListener extends KotlinParserBaseListener {

    @Override
    public void enterVariableDeclaration(KotlinParser.VariableDeclarationContext ctx) {
        System.out.println("Declaring variable: ");
        super.enterVariableDeclaration(ctx);
    }
}
