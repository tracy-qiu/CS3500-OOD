/**
 * this class represents a 5-scale likert question.
 * the scales are strongly agree, agree, neither agree nor disagree, disagree and strongly disagree
 */
public class LikertQuestion extends Question {

  //a valid question must have text
  public LikertQuestion(String text, String enteredAnswer) throws IllegalArgumentException {
    super(text, enteredAnswer);

  }

  @Override
  public String getType() {
    return "Likert";
  }

  //a valid answer must be one of the 5 options, in a case-insensitive manner
  @Override
  public void answer(String enteredAnswer) {
    String[] options = {"strongly agree", "agree", "neither agree nor disagree", "disagree",
      "strongly disagree"};

    for (String option : options) {
      if (enteredAnswer.toLowerCase().equals(option)) {
        this.enteredAnswer = enteredAnswer.toLowerCase();
        return;
      }
    }

    throw new IllegalArgumentException("Invalid answer");
  }

  @Override
  public boolean hasBeenAnswered() {
    String[] options = {"strongly agree", "agree", "neither agree nor disagree", "disagree",
      "strongly disagree"};

    for (String option : options) {
      if (enteredAnswer.toLowerCase().equals(option)) {
        return true;
      }
    }
    return false;
  }

  protected Question factory(String text) {
    return new LikertQuestion(text, enteredAnswer);
  }
}
