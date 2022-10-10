/**
 * this class represents a yes/no question.
 */
public class YesNoQuestion extends Question {

  /**
   * Yes no question constructor - a question must be non empty and should end with a question mark.
   * @param text the text of the question
   * @param enteredAnswer the entered answer string
   * @throws IllegalArgumentException throws an exception when given invalid question text
   */

  public YesNoQuestion(String text, String enteredAnswer) throws IllegalArgumentException {
    super(text, enteredAnswer);
    if ((text.charAt(text.length() - 1) != '?')) {
      throw new IllegalArgumentException("Invalid question text");
    }
  }

  @Override
  public String getType() {
    return "YesNo";
  }

  //a valid answer must be a yes or no
  @Override
  public void answer(String enteredAnswer) {
    if ((enteredAnswer.toLowerCase().equals("yes")) || (enteredAnswer.toLowerCase().equals("no"))) {
      this.enteredAnswer = enteredAnswer.toLowerCase();
    } else {
      throw new IllegalArgumentException("Invalid answer: " + enteredAnswer);
    }
  }

  @Override
  public boolean hasBeenAnswered() {
    return enteredAnswer.equals("yes") || enteredAnswer.equals("no");
  }

  protected Question factory(String text) {
    return new YesNoQuestion(text, enteredAnswer);
  }
}
