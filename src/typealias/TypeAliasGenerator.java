package typealias;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TypeAliasGenerator {

    private Map<String, String> map = new HashMap<>();
    private static TypeAliasGenerator shared;

    private TypeAliasGenerator() {
    }

    public static TypeAliasGenerator getInstance() {
        if (shared == null) {
            shared = new TypeAliasGenerator();
        }
        return shared;
    }

    private String generateNewId() {
        char[] chars = Constants.TYPEALIAS_CHARS;
        Random r = new Random();

        StringBuilder id = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            id.append(chars[r.nextInt(chars.length)]);
        }
        return "`" + id.toString() + "`";
    }

    public String getTypeAlias(String type) {
        if (!map.containsKey(type)) {
            map.put(type, generateNewId());
        }
        return map.get(type);
    }

    public String getEndingOutput() {
        StringBuilder output = new StringBuilder();
        for (String key : map.keySet()) {
            output.append(" typealias ").append(map.get(key)).append("=").append(key);
        }
        return output.toString();
    }

    public Map<String, String> getMap() {
        return map;
    }
}
