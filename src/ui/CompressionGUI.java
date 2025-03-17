package ui;

import compression.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CompressionGUI extends JFrame {
    private JTextField filePathField;
    private JButton compressButton, decompressButton, browseButton;

    public CompressionGUI() {
        setTitle("File Compression Tool");
        setSize(600, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        filePathField = new JTextField(40);
        compressButton = new JButton("Compress");
        decompressButton = new JButton("Decompress");
        browseButton = new JButton("Browse");

        browseButton.addActionListener(e -> selectFile());
        compressButton.addActionListener(e -> compressFile());
        decompressButton.addActionListener(e -> decompressFile());

        add(filePathField);
        add(browseButton);
        add(compressButton);
        add(decompressButton);
    }

    private void selectFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            filePathField.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void compressFile() {
        new Thread(() -> {
            String filePath = filePathField.getText();
            File inputFile = new File(filePath);

            if (!inputFile.exists()) {
                JOptionPane.showMessageDialog(this, "Error: File not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                String text = new String(Files.readAllBytes(inputFile.toPath()));

                Map<Character, Integer> freqMap = new HashMap<>();
                for (char c : text.toCharArray()) {
                    freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
                }

                HuffmanCompression.root = HuffmanCompression.buildHuffmanTree(freqMap);
                HuffmanCompression.generateCodes(HuffmanCompression.root, "");

                String compressed = HuffmanCompression.compress(text);

                String compressedFilePath = filePath.endsWith(".huff") ? filePath : filePath + ".huff";
                try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(compressedFilePath))) {
                    out.writeObject(freqMap);
                    out.writeUTF(compressed);
                }

                JOptionPane.showMessageDialog(this, "File Compressed Successfully!\nSaved as: " + compressedFilePath);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Compression Failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }).start();
    }

    private void decompressFile() {
        new Thread(() -> {
            String filePath = filePathField.getText();
            File inputFile = new File(filePath);

            if (!inputFile.exists()) {
                JOptionPane.showMessageDialog(this, "Error: File not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!filePath.endsWith(".huff")) {
                JOptionPane.showMessageDialog(this, "Error: Not a valid compressed file (.huff)!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(inputFile))) {
                    Map<Character, Integer> freqMap = (Map<Character, Integer>) in.readObject();
                    String compressedText = in.readUTF();

                    HuffmanCompression.root = HuffmanCompression.buildHuffmanTree(freqMap);
                    HuffmanCompression.generateCodes(HuffmanCompression.root, "");

                    String decompressed = HuffmanCompression.decompress(compressedText);
                    String decompressedFilePath = filePath.replace(".huff", "_decompressed.txt");

                    Files.write(Paths.get(decompressedFilePath), decompressed.getBytes());

                    JOptionPane.showMessageDialog(this, "File Decompressed Successfully!\nSaved as: " + decompressedFilePath);
                }
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(this, "Decompression Failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CompressionGUI().setVisible(true));
    }
}
