package compression;

public class RLECompression {
    public static String compress(String text) {
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
        StringBuilder decompressed = new StringBuilder();
        for (int i = 0; i < compressedText.length(); i += 2) {
            char c = compressedText.charAt(i);
            int count = Character.getNumericValue(compressedText.charAt(i + 1));
            decompressed.append(String.valueOf(c).repeat(count));
        }
        return decompressed.toString();
    }
}
