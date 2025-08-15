package com.example.compression.algorithm;

import java.util.*;

public class HuffmanCompression {
    private static Map<Character, String> huffmanCodes = new HashMap<>();
    public static HuffmanNode root;

    // Result record for better type-safety
    public record HuffmanResult(String bitString, Map<Character,Integer> freqMap) {}

    public static HuffmanNode buildHuffmanTree(Map<Character, Integer> freqMap) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        freqMap.forEach((ch, fr) -> priorityQueue.add(new HuffmanNode(ch, fr)));

        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode merged = new HuffmanNode('\0', left.frequency + right.frequency);
            merged.left = left;
            merged.right = right;
            priorityQueue.add(merged);
        }
        return priorityQueue.poll();
    }

    public static void generateCodes(HuffmanNode node, String code, Map<Character, String> codes) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            huffmanCodes.put(node.character, code);
        }
        generateCodes(node.left, code + "0", codes);
        generateCodes(node.right, code + "1", codes);
    }

    public static HuffmanResult compress(String text) {

        if (text == null || text.isEmpty()) throw new IllegalArgumentException("Text is empty");

        // This is for frequency map
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            freqMap.merge(c,1, Integer::sum);
        }

        HuffmanNode root = buildHuffmanTree(freqMap);

        Map<Character, String> codes = new HashMap<>();
        generateCodes(root, "", codes);

        StringBuilder encodedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            encodedText.append(codes.get(c));
        }
        return new HuffmanResult(codes.toString(), freqMap);
    }

    public static String decompress(String bitString, Map<Character, Integer> freqMap) {

        if (bitString == null || freqMap == null)
            throw new IllegalArgumentException("Data is missing for decompression");

        HuffmanNode root = buildHuffmanTree(freqMap);

        StringBuilder decodedText = new StringBuilder();
        HuffmanNode current = root;

        if (root == null) {
            throw new IllegalStateException("Huffman tree is not built!");
        }

        for (char bit : bitString.toCharArray()) {
            if (current == null) {
                throw new NullPointerException("Huffman tree node is null! Check if the tree is properly reconstructed.");
            }

            current = (bit == '0') ? current.left : current.right;

            if (current == null) {
                throw new NullPointerException("Invalid Huffman tree traversal! Compressed data might be corrupted.");
            }

            if (current.left == null && current.right == null) {
                decodedText.append(current.character);
                current = root;
            }
        }

        return decodedText.toString();
    }
}
