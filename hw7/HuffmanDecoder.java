import java.io.File;
import java.util.ArrayList;

public class HuffmanDecoder {
    public static void main(String[] args) {
        ObjectReader or = new ObjectReader(args[0]);
        Object x = or.readObject();
        Object y = or.readObject();
        Object z = or.readObject();
        BinaryTrie trie = (BinaryTrie) x;
        int num = (int) y;
        BitSequence bit = (BitSequence) z;
        char[] list = new char[num];
        int point = 0;
        while (bit.length() > 0) {
            Match m = trie.longestPrefixMatch(bit);
            BitSequence b = m.getSequence();
            Character c = m.getSymbol();
            //System.out.println(c);
            list[point++] = c;
            bit = bit.allButFirstNBits(b.length());
        }
        //File file2 = new File(args[1]);
        FileUtils.writeCharArray(args[1], list);
    }
}
