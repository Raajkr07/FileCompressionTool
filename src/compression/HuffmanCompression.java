package compression;

import java.util.*;

public class HuffmanCompression {
    private static Map<Character, String> huffmanCodes = new HashMap<>();
    public static HuffmanNode root;

    public static HuffmanNode buildHuffmanTree(Map<Character, Integer> freqMap) {
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();
        for (var entry : freqMap.entrySet()) {
            queue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();
            HuffmanNode merged = new HuffmanNode('\0', left.frequency + right.frequency);
            merged.left = left;
            merged.right = right;
            queue.add(merged);
        }
        return queue.poll();
    }

    public static void generateCodes(HuffmanNode node, String code) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            huffmanCodes.put(node.character, code);
        }
        generateCodes(node.left, code + "0");
        generateCodes(node.right, code + "1");
    }

    public static String compress(String text) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        root = buildHuffmanTree(freqMap);
        generateCodes(root, "");

        StringBuilder encodedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            encodedText.append(huffmanCodes.get(c));
        }
        return encodedText.toString();
    }

    public static String decompress(String encodedText) {
        StringBuilder decodedText = new StringBuilder();
        HuffmanNode current = root;

        if (root == null) {
            throw new IllegalStateException("Huffman tree is not built!");
        }

        for (char bit : encodedText.toCharArray()) {
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
