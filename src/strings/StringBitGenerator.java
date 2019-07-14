package strings;

import java.util.ArrayList;
import java.util.List;

public class StringBitGenerator {

    private String input;
    private List<List<Integer>> indexes = new ArrayList<>();

    public StringBitGenerator(String input) {
        this.input = input;
    }

    public void addIndexes(int start, int end) {
        List<Integer> idx = new ArrayList<>();
        idx.add(start);
        idx.add(end);
        this.indexes.add(idx);
    }

    private String transformString(String string) {
        StringBuilder builder = new StringBuilder("byteArrayOf(");
        for (byte b : string.getBytes()) builder.append(b).append(",");
        builder.deleteCharAt(builder.length()-1);

        return builder.append(").toString(Charsets.UTF_8)").toString();
    }

    public String getOutput() {
        String output = input;
        for (List<Integer> idx : indexes) {
            String prev = output.substring(0, idx.get(0));
            String next = output.substring(idx.get(1));
            String content = output.substring(idx.get(0), idx.get(1));
            output = prev + transformString(content) + next;
        }
        return output;
    }
}