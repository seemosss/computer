import edu.princeton.cs.algs4.MinPQ;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BinaryTrie implements Serializable {
    private Node HaffmanTree;
    private static class Node implements Comparable<Node>, Serializable{
        private char ch;
        private int freq;
        private Node left, right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        private boolean isleaf() {
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return left == null;
        }
        @Override
        public int compareTo(Node o) {
            return this.freq - o.freq;
        }
    }

    public BinaryTrie(Map<Character, Integer> frequencyTable) {
        MinPQ<Node> pq = new MinPQ<>();
        Set<Map.Entry<Character, Integer>> entrySet = frequencyTable.entrySet();
        for (Map.Entry<Character, Integer> entry : entrySet) {
            Node n = new Node(entry.getKey(), entry.getValue(), null, null);
            pq.insert(n);
        }
        while (pq.size() > 1) {
            Node left = pq.delMin();
            Node right = pq.delMin();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.insert(parent);
        }
        HaffmanTree = pq.delMin();
    }

    public Match longestPrefixMatch(BitSequence querySequence) {
        BitSequence match = new BitSequence();
        Node t = HaffmanTree;
        if(t.isleaf())
            return new Match(match, t.ch);
        for (int i = 0; i < querySequence.length(); i++) {
            int num = querySequence.bitAt(i);
            match = match.appended(num);
            if (num == 0) t = t.left;
            else t = t.right;
            if(t.isleaf())
                return new Match(match, t.ch);
        }
        return null;
    }

    private void dfs(Node t, String s, Map<Character, BitSequence> map) {
        if (t == null) return;
        if (t.isleaf()) {
            map.put(t.ch, new BitSequence(s));
        }
        else {
            dfs(t.left, s + "0", map);
            dfs(t.right, s + "1", map);
        }
    }

    public Map<Character, BitSequence> buildLookupTable() {
        Node t = HaffmanTree;
        Map<Character, BitSequence> map = new HashMap<>();
        dfs(t, "", map);
        return map;
    }
}
