package listener;

import names.NameGenerator;
import org.antlr.v4.runtime.ParserRuleContext;
import typealias.TypeAliasGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static listener.MainHelper.sourceTextForContext;

class VariableHelper {

    private static TypeAliasGenerator typeAliasGenerator = TypeAliasGenerator.getInstance();
    private static NameGenerator nameGenerator = NameGenerator.getInstance();

    static String onVariableDeclaration(ParserRuleContext ctx) {
        String str = sourceTextForContext(ctx);
        if (str.endsWith("\n")) str = str.replace("\n", "");

        StringBuilder buffer = new StringBuilder();
        if (str.contains(":")) {
            fillLeftSideForSymbol(":", str, buffer);

            List<String> right = new ArrayList<>(Arrays.asList(str.split(":")[1].split(" ")));
            List<String> filterRight = new ArrayList<>();
            for (String s : right) if (!s.equals("")) filterRight.add(s);

            buffer.append(typeAliasGenerator.getTypeAlias(filterRight.get(0)));
            for (int i = 1; i < filterRight.size(); i++) {
                buffer.append(" ");
                buffer.append(filterRight.get(i));
            }
        } else {
            fillLeftSideForSymbol("=", str, buffer);

            List<String> right = new ArrayList<>(Arrays.asList(str.split("=")[1].split(" ")));
            List<String> filterRight = new ArrayList<>();
            for (String s : right) if (!s.equals("")) filterRight.add(s);

            buffer.append(filterRight.get(0));
        }

        String output = buffer.toString();
        if (!output.endsWith("\n")) output += "\n";

        return output;
    }

    private static void fillLeftSideForSymbol(String symbol, String str, StringBuilder buffer) {
        List<String> left = new ArrayList<>(Arrays.asList(str.split(symbol)[0].split(" ")));
        List<String> filterLeft = new ArrayList<>();
        for (String s : left) if (!s.equals("")) filterLeft.add(s);

        for (int i = 0; i < filterLeft.size() - 1; i++) {
            buffer.append(filterLeft.get(i));
            buffer.append(" ");
        }
        buffer.append(nameGenerator.getNewName(filterLeft.get(filterLeft.size() - 1)));
        buffer.append(symbol);
    }

}
