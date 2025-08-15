package com.example.compression.algorithm;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RLECompression {

    public static String compress(String text) {
        if (text == null || text.isEmpty()) return "";

        StringBuilder compressed = new StringBuilder();
        int n = text.length();

        for (int i = 0; i < n; i++) {
            int count = 1;
            while (i < n - 1 && text.charAt(i) == text.charAt(i + 1)) {
                count++;
                i++;
            }
            compressed.append(text.charAt(i)).append(count);
        }
        return compressed.toString();
    }

    public static String decompress(String compressedText) {
        if (compressedText == null || compressedText.isEmpty()) return "";

        StringBuilder decompressed = new StringBuilder();
        Pattern pattern = Pattern.compile("([a-zA-Z])([0-9]+)"); // Match "A12"
        Matcher matcher = pattern.matcher(compressedText);

        while (matcher.find()) {
            char c = matcher.group(1).charAt(0);
            int count = Integer.parseInt(matcher.group(2));
            decompressed.append(String.valueOf(c).repeat(count));
        }
        return decompressed.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter text to compress: ");
        String userInput = scanner.nextLine();

        String compressed = compress(userInput);
        System.out.println("\nCompressed Output: " + compressed);

        String decompressed = decompress(compressed);
        System.out.println("\nDecompressed Output: " + decompressed);

        if (userInput.equals(decompressed)) {
            System.out.println("\n✅ Decompression Successful! The original and decompressed text match.");
        } else {
            System.out.println("\n❌ Decompression Failed! The texts do not match.");
        }

        scanner.close();
    }
}
