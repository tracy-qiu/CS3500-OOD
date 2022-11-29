package finder;

import java.util.List;

/**
 * An interface for adding words and finding them by prefix
 *
 * A prefix of a word is a substring that begins at index 0 of the original word.
 * For example, for the string "finder", valid prefixes are "", "f", "fi", "fin",
 * "find", "finde" and "finder". On the other hand, "inder" and "ind" are not prefixes.
 */
public interface IPrefixWordFinder { // (*\label{line:IPrefixWordFinder} *)
    /**
     * Add a word to the word finder
     * @param s the word to be added
     */
    void add(String s);

    /**
     * Return all the words in the word finder that begin with the specified prefix.
     * @param prefix the specified prefix
     * @return
     */
    List<String> getWordsWithPrefix(String prefix);
}
