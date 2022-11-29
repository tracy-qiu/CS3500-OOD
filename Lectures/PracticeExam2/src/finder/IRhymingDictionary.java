package finder;

import java.util.List;

/**
 * An interface for adding words and returning words that rhyme with a given word
 *
 * A word ``rhymes'' with another if they have the same suffixes. A suffix of a word
 * is a substring that ends at the last index of the original word.
 * For example, for the string "finder", valid suffixes are "", "r", "er", "der",
 * "nder", "inder" and "finder". We ignore the "" suffix for the rhyming to be meaningful.
 * On the other hand, "find" and "ind" are not suffixes.
 */
public interface IRhymingDictionary { //(* \label{line:IRhymingDictionary} *)
    /**
     * Add a word to the word finder
     * @param s the word to be added
     */
    void add(String s);

    /**
     * Return all the words in the dictionary that rhyme with the given word,
     * according to the above definition of rhyme.
     * @param word
     * @return a list of all words in dictionary that rhyme with the parameter word
     */
    List<String> getRhymingWords(String word);
}
