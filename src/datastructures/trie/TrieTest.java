package datastructures.trie;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TrieTest {
    private final Random random = new Random();

    @Test
    public void testBasicInsertAndContains() {
        Trie t = new Trie();
        t.insert("apple");
        assertTrue(t.contains("apple"));
        assertFalse(t.contains("app"));
        assertFalse(t.contains("banana"));
    }

    @Test
    public void testPrefixes() {
        Trie t = new Trie();
        t.insert("app");
        t.insert("apple");
        assertTrue(t.contains("app"));
        assertTrue(t.contains("apple"));
        assertFalse(t.contains("appl"));
    }

    @Test
    public void testEmptyString() {
        Trie t = new Trie();
        t.insert("");
        assertTrue(t.contains(""));
        t.insert("a");
        assertTrue(t.contains("a"));
        assertFalse(t.contains("aa"));
    }

    @Test
    public void testDuplicateInsertions() {
        Trie t = new Trie();
        t.insert("test");
        assertTrue(t.contains("test"));
        assertFalse(t.insert("test"));
        assertTrue(t.contains("test"));
    }

    @Test
    public void testCaseSensitivity() {
        Trie t = new Trie();
        t.insert("Test");
        assertTrue(t.contains("Test"));
        assertFalse(t.contains("test"));
        t.insert("test");
        assertTrue(t.contains("test"));
    }

    @Test
    public void testMultipleWordsWithCommonPrefix() {
        Trie t = new Trie();
        t.insert("car");
        t.insert("cart");
        t.insert("care");
        assertTrue(t.contains("car"));
        assertTrue(t.contains("cart"));
        assertTrue(t.contains("care"));
        assertFalse(t.contains("ca"));
    }

    @Test
    public void testWordsThatAreSubstrings() {
        Trie t = new Trie();
        t.insert("testing");
        t.insert("test");
        assertTrue(t.contains("testing"));
        assertTrue(t.contains("test"));
    }

    @Test
    public void testLongWords() {
        Trie t = new Trie();
        StringBuilder longWord = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            longWord.append((char) ('a' + random.nextInt(26)));
        }
        String longString = longWord.toString();
        t.insert(longString);
        assertTrue(t.contains(longString));
        assertFalse(t.contains(longString.substring(0, longString.length() - 1)));
    }

    @Test
    public void testWordsWithSpecialCharacters() {
        Trie t = new Trie();
        t.insert("hello!");
        assertTrue(t.contains("hello!"));
        assertFalse(t.contains("hello"));
        t.insert("hello");
        assertTrue(t.contains("hello"));
        t.insert("123");
        assertTrue(t.contains("123"));
        assertFalse(t.contains("12"));
        t.insert("!@#$%^&*()");
        assertTrue(t.contains("!@#$%^&*()"));
    }

    @Test
    public void testInsertAndContainsWithLargeDataset() {
        Trie t = new Trie();
        String[] words = {"apple", "banana", "cherry", "date", "elderberry", "fig", "grape", "honeydew"};
        for (String word : words) {
            t.insert(word);
        }
        for (String word : words) {
            assertTrue(t.contains(word));
        }
        assertFalse(t.contains("ap"));
        assertFalse(t.contains("ban"));
    }

    @Test
    public void testInsertAndContainsWithRandomStrings() {
        Trie t = new Trie();
        for (int i = 0; i < 1000; i++) {
            String randomString = generateRandomString(random.nextInt(20) + 1);
            t.insert(randomString);
            assertTrue(t.contains(randomString));
        }
    }

    @Test
    public void testInsertAndContainsWithAllASCIICharacters() {
        Trie t = new Trie();
        StringBuilder sb = new StringBuilder();
        for(int i = 32; i < 127; i++){
            sb.append((char)i);
            t.insert(sb.toString());
            assertTrue(t.contains(sb.toString()));
        }

    }

    private String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + random.nextInt(26)));
        }
        return sb.toString();
    }
}
