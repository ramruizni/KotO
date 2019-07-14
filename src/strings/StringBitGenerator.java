package strings;

import kotlin.text.Charsets;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

public class StringBitGenerator {

    private String input;
    private List<List<Integer>> indexes = new ArrayList<>();
    private String output = "";

    public StringBitGenerator(String input) {
        this.input = input;
    }

    public void addIndexes(int start, int end) {
        List<Integer> idx = new ArrayList<>();
        idx.add(start);
        idx.add(end);
        this.indexes.add(idx);
    }

    private String transformString(String s) {
        byte[] bytes = s.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            //   binary.append(' ');
        }
        System.out.println("'" + s + "' to binary: " + binary);


        return "[72, 101, 108, 108, 111].toString(Charsets.UTF_8)";
    }

    public String getOutput() {
        output = input;
        for (List<Integer> idx : indexes) {
            String prev = output.substring(0, idx.get(0));
            String next = output.substring(idx.get(1));
            String content = output.substring(idx.get(0), idx.get(1));


            output = prev + transformString(content) + next;
        }
        return output;
    }
}