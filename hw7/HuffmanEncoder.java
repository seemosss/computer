import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanEncoder {
    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols){
        Map<Character, Integer> map = new HashMap<>();
        for (char c : inputSymbols) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }
    public static void main(String[] args){
        char[] file = FileUtils.readFile(args[0]);
        Map<Character, Integer> map = buildFrequencyTable(file);
        BinaryTrie trie = new BinaryTrie(map);
        String output = args[0] + ".huf";
        //File file1 = new File(output);
        ObjectWriter ow = new ObjectWriter(output);
        ow.writeObject(trie);
        ow.writeObject(file.length);
        Map<Character, BitSequence> lookup = trie.buildLookupTable();
        List<BitSequence> list = new ArrayList<>();
        for (char c : file) {
            BitSequence b = lookup.get(c);
            list.add(b);
        }
        BitSequence bit = BitSequence.assemble(list);
        ow.writeObject(bit);
    }
}
