package datastructures.trie;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * Classic implementation of a Trie, that exposes the root so that it can be extended
 * later on for more algorithm implementations.
 *
 */
public class Trie {

    /**
     * Node for the Trie
     */
    public static class Node {
        public int data;
        public HashMap<Integer, Node> children = new HashMap<>();
        private boolean eow = false;


        /**
         * Constructs a {@code Node} with the given int as data
         * @param c the data of the node
         */
        Node(int c) {
            data = c;
        }

        /**
         * Constructs a new {@code Node} with the given data
         * @param c the data of the new Node
         * @return a reference to the new Node
         */
        @NotNull Node addChild( int c) {
            Node child= new Node(c);
            children.put(c, child);
            return child;
        }

        /**
         * Sets node as End of Word
         */
        void setEOW() {
            this.eow = true;
        }

        /**
         * @return whether node is End of Word
         */
        boolean isEOW() {
            return eow;
        }

        /**
         * Leaf implies that node has no children left to explore
         * @return whether the node is a leaf or not.
         */
        boolean isLeaf() {
            return children.isEmpty();
        }
    }

    private final Node root = new Node('\0');

    /**
     * Inserts a new String s, into the Trie
     * @param s the String to insert
     * @return {@code true} if a new word was inserted, {@code false} if it already existed as a word
     */
    public boolean insert(@NotNull String s) {
        Node n = root;
        int ptr = 0;

        while (ptr < s.length() && !n.isLeaf() && n.children.containsKey((int)s.charAt(ptr))) {
            n = n.children.get((int)s.charAt(ptr++));
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

    /**
     * @return  a reference to the root that is a {@code final} data member of the class a reference to the root that is a {@code final} data member of the class
     */
    public Node getRoot() {
        return root;
    }
}