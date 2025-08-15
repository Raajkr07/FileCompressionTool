package com.example.compression.algorithm;

import java.util.*;

public class LZWCompression {

    public static List<Integer> compress(String input) {

        if (input == null || input.isEmpty())
            throw new IllegalArgumentException(" input is empty");

        Map<String, Integer> dictionary = new HashMap<>();
        int dictSize = 256;

        for (int i = 0; i < 256; i++) {
            dictionary.put("" + (char) i, i);
        }

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

        if (!prefix.isEmpty()) {
            compressed.add(dictionary.get(prefix));
        }

        return compressed;
    }

    public static String decompress(List<Integer> compressed) {

        if (compressed == null || compressed.isEmpty())
            throw new IllegalArgumentException("Compressed data is empty");

        Map<Integer, String> dictionary = new HashMap<>();
        int dictSize = 256;

        for (int i = 0; i < 256; i++) {
            dictionary.put(i, "" + (char) i);
        }

        String prefix = "" + (char) (int) compressed.remove(0);
        StringBuilder result = new StringBuilder(prefix);

        for (int code : compressed) {
            String entry;

            if (dictionary.containsKey(code)) {
                entry = dictionary.get(code);
            } else if (code == dictSize) {
                entry = prefix + prefix.charAt(0);
            } else {
                throw new IllegalArgumentException("Invalid compressed code: " + code);
            }

            result.append(entry);

            dictionary.put(dictSize++, prefix + entry.charAt(0));

            prefix = entry;
        }

        return result.toString();
    }
}
