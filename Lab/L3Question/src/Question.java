/**
 * This interface represents a single question in an online quiz. Each instance
 * of a question is assumed to be part of a quiz assigned to a particular student.
 * In other words, one Question object per student.
 */
abstract class Question {

  //private but subclasses can access
  protected String questionText;
  protected String enteredAnswer;

  /**
   * This is the constructor of the Question object.
   *
   * @param text          is the text question
   * @param enteredAnswer is the entered answer string
   */
  Question(String text, String enteredAnswer) {
    if ((text.length() == 0)) {
      throw new IllegalArgumentException("Invalid question text");
    }
    this.questionText = text;
    this.enteredAnswer = "";
  }

  /**
   * Get the text of this question.
   *
   * @return the text of this question as a string
   */
  public String getQuestionText() {
    return questionText;
  }

  /**
   * Get a string that represents the type of this question.
   * The actual string returned depends on the implementation.
   *
   * @return the type of this question, as a string
   */
  abstract String getType();

  /**
   * Enter an answer to this question. Specific implementations may enforce constraints
   * on what a valid answer can be.
   *
   * @param enteredAnswer the answer entered for this question by a student
   */
  abstract void answer(String enteredAnswer);

  /**
   * Returns whether this question has been answered by the student.
   *
   * @return true if the question has been answered, false otherwise
   */
  abstract boolean hasBeenAnswered();

  /**
   * Return the answer entered by the student, if it has been entered.
   *
   * @return the answer entered by the student
   * @throws IllegalStateException if the question has not been answered yet
   */
  public String getEnteredAnswer() {
    if (!hasBeenAnswered()) {
      throw new IllegalStateException("solution.Question not attempted yet!");
    } else {
      return enteredAnswer;
    }
  }

  /**
   * This returns a Question object.
   *
   * @param text is the text of the question
   * @return Question object of abstract class
   */
  protected abstract Question factory(String text);
}
