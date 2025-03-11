package datastructures.trie;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;


public class Trie {
    private static class Node {
        char data;
        HashMap<Character, Node> children = new HashMap<>();
        private boolean eow = false;

        Node(char c) {
            data = c;
        }

        Node addChild(char c) {
            Node child= new Node(c);
            children.put(c, child);
            return child;
        }

        void setEOW() {
            this.eow = true;
        }

        boolean isEOW() {
            return eow;
        }

        boolean isLeaf() {
            return children.isEmpty();
        }
    }

    private final Node root = new Node('\0');

    public boolean insert(@NotNull String s) {
        Node n = root;
        int ptr = 0;

        while (ptr < s.length() && !n.isLeaf() && n.children.containsKey(s.charAt(ptr))) {
            n = n.children.get(s.charAt(ptr++));
        }

        if (ptr == s.length() && n.isLeaf() && n.isEOW()) {
            return false;
        }
        if (ptr == s.length()) {
            n.setEOW();
            return true;
        }

        while (ptr < s.length()) {
            n = n.addChild(s.charAt(ptr++));
        }
        n.setEOW();
        return true;
    }

    public boolean contains(String s) {
        Node n = root;
        int ptr = 0;
        while (ptr < s.length()) {
            if (n.children.containsKey(s.charAt(ptr))) {
                n = n.children.get(s.charAt(ptr++));
            } else
                return false;
        }

        return n.isEOW();
    }
}