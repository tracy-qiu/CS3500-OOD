package finder;

import java.util.ArrayList;
import java.util.List;

public class RhymingDictionary implements IRhymingDictionary {

  // invariants
  // what does this test do
  // design question - after done with design, show me how to use the design such as write a test
  // even if didnt finish design - still write the test example state how it would work
  // similar to exam 1 question 1
  /*
  do not edit given code
  give some issue with code - redesign it
  change minimal things
  how to
  what the function signature (parameters and what it returns), what the function does, how it works,
   whos going to hold it and use it,
   command pattern doesnt take anything - usually changes model
   - takes the model
   - usually lives in the controller
   - typically doesnt return anything
   strategy pattern - model takes function object such as transform - model applies strategy to object
   in the model
   - usually lives in the model
   For strategy pattern it can be replaced with extending and creating a new class for each function
   - have to overwrite protected method in each class

   */
  private final IPrefixWordFinder delegate;

  public RhymingDictionary() {
    this(new WordFinder());
  }
  private String reverse(String s) {
    String result = "";
    for (char c: s.toCharArray()) {
      result = c + result;
    }
    return result;
  }
  @Override
  public void add(String s) {
    this.delegate.add(reverse(s));
  }

  @Override
  public List<String> getRhymingWords(String suffix) {
    List<String> results = this.delegate.getWordsWithPrefix(reverse(suffix));
    List<String> reversedResult = new ArrayList<>();
    for (String s : results ) {
      reversedResult.add(this.reverse(s));
    }
    return reversedResult;
  }
}
