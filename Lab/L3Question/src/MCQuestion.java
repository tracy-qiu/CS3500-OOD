import java.util.stream.IntStream;

/**
 * This multiple choice question class extends the question class.
 */
public class MCQuestion extends Question {

  String[] possibleAnswers;

  /**
   * This is the constructor for a multiple choice question.
   *
   * @param text            is the text question
   * @param enteredAnswer   is the entered answer to the question
   * @param possibleAnswers is a list of possible answers of the multiple choice
   * @throws IllegalArgumentException throws an exception when there is invalid question text
   */
  public MCQuestion(String text, String enteredAnswer, String[] possibleAnswers) throws
          IllegalArgumentException {
    super(text, enteredAnswer);
    if ((text.charAt(text.length() - 1) != '?')) {
      throw new IllegalArgumentException("Invalid question text");
    }
    this.possibleAnswers = possibleAnswers;
  }

  @Override
  String getType() {
    return "MultipleChoice";
  }

  @Override
  void answer(String enteredAnswer) {
    int n = possibleAnswers.length;
    int[] arr = IntStream.rangeClosed(1, n).toArray();
    for (int option : arr) {
      String answer = String.valueOf(option);
      if (enteredAnswer.equals(answer)) {
        this.enteredAnswer = enteredAnswer;
        return;
      }
    }

    throw new IllegalArgumentException("Invalid answer");
  }

  // Abstract factoring
  @Override
  boolean hasBeenAnswered() {
    int n = possibleAnswers.length;
    int[] arr = IntStream.rangeClosed(1, n).toArray();

    for (int option : arr) {
      if (enteredAnswer.equals(String.valueOf(option))) {
        return true;
      }
    }
    return false;
  }

  @Override
  protected Question factory(String text) {
    return new MCQuestion(text, enteredAnswer, possibleAnswers);
  }
}

