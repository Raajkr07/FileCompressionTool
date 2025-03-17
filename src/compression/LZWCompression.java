package compression;

import java.util.*;

public class LZWCompression {
    public static List<Integer> compress(String input) {
        Map<String, Integer> dictionary = new HashMap<>();
        int dictSize = 256;
        for (int i = 0; i < 256; i++) dictionary.put("" + (char) i, i);

        List<Integer> compressed = new ArrayList<>();
        String prefix = "";

        for (char c : input.toCharArray()) {
            String combined = prefix + c;
            if (dictionary.containsKey(combined)) {
                prefix = combined;
            } else {
                compressed.add(dictionary.get(prefix));
                dictionary.put(combined, dictSize++);
                prefix = "" + c;
            }
        }

        if (!prefix.isEmpty()) compressed.add(dictionary.get(prefix));
        return compressed;
    }
}
