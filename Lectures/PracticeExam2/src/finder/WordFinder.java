package finder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * This class represents a storage and retrieval mechanism for words,
 * for applications such as auto-complete while typing. A user can add
 * to this object one word at a time. Additionally the user can ask
 * for all words that have the specified prefix. This query can be used
 * as a simple way to auto-complete partially typed words.
 *
 * This data structure is a tree. Each node stores many children in
 * a map, each corresponding to words that begin with a letter. A node
 * also stores all the words that have the prefix equal to the letters
 * along the path from the root to this node.
 *
 * For example, if the words "at", "ate", "atm" and "bat" are added,
 * the tree will look something like this:
 *                          'a'                         'b'
 *                  +-----------------Node (root)[] -----------------+
 *                  |                                                |
 *                  |                                                |
 *                 Node[]                                           Node[]
 *                  |                                                |
 *              't' |                                                | 'a'
 *                  |                                                |
 *                 Node [ "at" ] -------+                           Node[]
 *                  |                   | 'm'                        | 't'
 *              'e' |                   |                            |
 *                  |               Node [ "atm" ]                  Node [ "bat" ]
 *                 Node [ "ate" ]
 */
public final class WordFinder implements IPrefixWordFinder {

    private WordNode root;

    public WordFinder() {
        root = new WordNode();
    }

    /**
     * Add a word to the finder. This traverses the tree using the
     * word's letters and adds it to the correct node
     *
     * @param s the word to be added
     */
    @Override
    public void add(String s) {
        Objects.requireNonNull(s);

        WordNode current = root;

        for (int i = 0; i < s.length(); i++) {
            if (!current.children.containsKey(s.charAt(i))) {
                current.children.put(s.charAt(i),new WordNode());
            }
            current = current.children.get(s.charAt(i));
        }
        current.words.add(s);
    }

    /**
     * This finds all the words in the finder that begin with the specified prefix.
     * First, it finds the node 'n' where this prefix would be added. All the words
     * that begin at this prefix are then all the words contained in the subtree
     * rooted at 'n'.
     * @param prefix the specified prefix
     * @return
     */
    @Override
    public List<String> getWordsWithPrefix(String prefix) { 
        Objects.requireNonNull(prefix);

        int i = 0;
        WordNode current = root;
        while ((i < prefix.length()) &&
               (current.children.containsKey(prefix.charAt(i)))) {
            current = current.children.get(prefix.charAt(i));
            i++;
        }

        if (i < prefix.length()) { return new ArrayList<String>(); }

//        return new ArrayList<String>(new HashSet<String>(current.getAllWords()));
        return removeDuplicates(current.getAllWords);
    }

    private List<String> removeDuplicates(List<String> duiplicatesList) {
        List<String> = result = new ArrayList<>();
        for (String s : duplicatesList) {
            if !result.contains(s)
        }
    }

    /**
     * Node class for the finder
     */
    private class WordNode { 
        /**
         * All the children are stored in a map. That way only those children exist
         * that are used.
         */
        public Map<Character, WordNode> children;
        /**
         * A list of words that end at this node
         */
        public List<String> words;

        public WordNode() {
            children = new HashMap<Character, WordNode>();
            words = new ArrayList<String>();
        }

        /**
         * Find all the words added to the tree rooted at this node
         * This first adds the words in itself, and then recurses to all its existing
         * children
         * @return
         */
        public List<String> getAllWords() {
            List<String> words = new ArrayList<String>();

            words.addAll(this.words);
            for (Map.Entry<Character, WordNode> e : children.entrySet()) {
                words.addAll(e.getValue().getAllWords());
            }
            return words;
        }
    }
}
