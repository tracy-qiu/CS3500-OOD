import org.junit.Test;

import java.util.List;
import finder.IPrefixWordFinder;
import finder.IRhymingDictionary;
import finder.RhymingDictionary;
import finder.WordFinder;
import static org.junit.Assert.*;

/**
 * A tester for the WordFinder
 */
public class WordFinderTest {

    @Test
    public void testCaseInsensitive() {
        IPrefixWordFinder finder = new WordFinder();
        finder.add("Table");
        finder.add("TAZMANIA");
        finder.add("tablet");

        List<String> allLowercasePrefix = finder.getWordsWithPrefix("ta");
        List<String> allUppercasePrefix = finder.getWordsWithPrefix("TA");
        List<String> allMixedPrefix = finder.getWordsWithPrefix("tA");
        List<String> allMixed2Prefix = finder.getWordsWithPrefix("Ta");

        assertTrue(allLowercasePrefix.size() == 4 && allLowercasePrefix.contains("Table")
                && allLowercasePrefix.contains("TAZMANIA") && allLowercasePrefix.contains("tablet")
                && allLowercasePrefix.contains("tArget"));

        assertTrue(allUppercasePrefix.size() == 4 && allUppercasePrefix.contains("Table")
                && allUppercasePrefix.contains("TAZMANIA") && allUppercasePrefix.contains("tablet")
                && allUppercasePrefix.contains("tArget"));

        assertTrue(allMixedPrefix.size() == 4 && allMixedPrefix.contains("Table")
                && allMixedPrefix.contains("TAZMANIA") && allMixedPrefix.contains("tablet")
                && allMixedPrefix.contains("tArget"));

        assertTrue(allMixed2Prefix.size() == 4 && allMixed2Prefix.contains("Table")
                && allMixed2Prefix.contains("TAZMANIA") && allMixed2Prefix.contains("tablet")
                && allMixed2Prefix.contains("tArget"));
    }
    @Test
    public void testSimple() {
        IPrefixWordFinder finder = new WordFinder();
        String sentence = "This is a great good sentence for Thorough testing";
        String[] words = sentence.split("\\s+");
        for (String s : words)
            finder.add(s);

        List<String> w = finder.getWordsWithPrefix("Th");
        assertTrue(w.contains("This"));
        assertTrue(w.contains("Thorough"));
        assertFalse(w.contains("Gre"));

        w = finder.getWordsWithPrefix("g");
        assertEquals(2,w.size());
    }

    @Test
    public void testMystery() { 
        IPrefixWordFinder finder = new WordFinder();
        IPrefixWordFinder finder2 = new WordFinder();
        String sentence = "This is a great good sentence for Thorough testing";
        String[] words = sentence.split("\\s+");
        for (String s : words) {
            finder.add(s);
            finder2.add(s);
        }

        for (String s : finder.getWordsWithPrefix("")) {
            finder2.add(s);
        }

        assertTrue(finder.getWordsWithPrefix("sent").contains("sentence"));
        assertEquals(finder.getWordsWithPrefix("Th").size(),
                     finder2.getWordsWithPrefix("Th").size());
    }

    @Test
    public void testRhyme() {
        IRhymingDictionary rhymingDictionary = new RhymingDictionary();
        rhymingDictionary.add("cat"); //tac
        rhymingDictionary.add("hat"); //tah
        rhymingDictionary.add("mtt"); //ttm
        rhymingDictionary.add("table"); //elbat

        List<String> rhymesWith = rhymingDictionary.getRhymingWords("at");
        assertEquals(2, rhymesWith.size());
    }
}