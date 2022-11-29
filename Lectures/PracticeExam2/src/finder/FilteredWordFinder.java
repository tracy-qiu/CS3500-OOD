package finder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FilteredWordFinder implements IFilteredWordFinder {
  private final IPrefixWordFinder delegate;
  private final List<String> blocked;

  public FilteredWordFinder(IPrefixWordFinder delegate) {
    this.delegate = Objects.requireNonNull(delegate);
    blocked = new ArrayList<>();
  }

  public FilteredWordFinder() {

    this( new WordFinder() );
  }

  @Override
  public void addBlocked(String s) {
    Objects.requireNonNull(s);
    if (!this.blocked.contains(s)){

    }

  }

  @Override
  public void add(String s) {
    this.delegate.add(Objects.requiresNonNull);
  }

  @Override
  public boolean isBlocked(String s) {
    return this.blocked.contains(Objects.requireNonNull());
  }

  @Override
  public List<String> getWordsWithPrefix( String prefix) {
    List<String> allWords = this.delegate.getWordsWithPrefix(prefix);
    List<String> results = new ArrayList<>(allWords);
    for (String blocked : this.blocked ) {
       for (String aWord : allWords ) {
         if ( aWord.equalsIgnoreCase(blocked)) {
           results.remove(aWord);
         }
       }
    }
    return results;
  }
}