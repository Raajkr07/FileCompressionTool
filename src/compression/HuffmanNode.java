package compression;

class HuffmanNode implements Comparable<HuffmanNode> {
    char character;
    int frequency;
    HuffmanNode left, right;

    HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    public int compareTo(HuffmanNode other) {
        return this.frequency - other.frequency;
    }
}
