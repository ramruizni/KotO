package variables;

import java.util.*;

public class NumberInfixGenerator {

    private String input;
    private List<List<Integer>> indexes = new ArrayList<>();
    private List<Integer> rands = new ArrayList<>();

    public NumberInfixGenerator(String input) {
        this.input = input;
        Random r = new Random();
        for (int i=0; i<5; i++) {
            rands.add(1 + r.nextInt(20));
        }
    }

    public void addIndexes(int start, int end) {
        List<Integer> idx = new ArrayList<>();
        idx.add(start);
        idx.add(end);
        this.indexes.add(idx);
    }

    private String transformNumber(String number) {
        Collections.shuffle(rands);

        String output = "";
        Double sum = Double.valueOf(number);
        for (Integer i : rands) {
            sum += i;
        }

        if (number.contains(".")) {
            output += Double.valueOf(sum);
            for (Integer i : rands) {
                output += " azazaazazaDoubleMinus " + Double.valueOf(i);
                sum -= i;
            }

        } else {
            output += Math.round(sum);
            for (Integer i : rands) {
                output += " azazaazazaIntMinus " + i;
                sum -= i;
            }
        }

        return output;
    }

    public String getOutput() {
        String output = input;
        int dif = 0;
        for (List<Integer> idx : indexes) {
            int prev = dif + idx.get(0);
            int next = dif + idx.get(1);

            String prevString = output.substring(0, prev);
            String nextString = output.substring(next);

            String content = output.substring(prev, next);
            output = prevString + transformNumber(content) + nextString;

            dif += transformNumber(content).length() - (idx.get(1) - idx.get(0));
        }

        return output + "\ninfix fun Int.azazaIntPlus(x:Int):Int{return this+x}" +
                "\ninfix fun Int.azazaIntMinus(x:Int):Int{return this-x}" +
                "\ninfix fun Double.azazaDoublePlus(x:Double):Double{return this+x}" +
                "\ninfix fun Double.azazaDoubleMinus(x:Double):Double{return this-x}\n";
    }
}