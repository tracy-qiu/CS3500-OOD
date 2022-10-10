import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This is the test for the multiple choice question class.
 */
public class MCQuestionTest {
  private String longRandom;

  public MCQuestionTest() {
    longRandom = "aosdifjaso oifhas;ldihv;al skdfha;osidghv;osiadhvbasdjkhvn";
  }

  @Test
  public void testCreateValidMCQuestion() {
    Random r = new Random(200);
    String[] possibleAnswers = new String[]{"cat", "dog", "rabbit", "mouse"};
    for (int i = 0; i < 1000; i++) {
      int start = r.nextInt(longRandom.length() - 1);
      int end = start + r.nextInt(longRandom.length() - start - 1) + 1;
      String questionText = longRandom.substring(start, end);
      Question q = new MCQuestion(questionText + "?", "", possibleAnswers);
      assertEquals(questionText + "?", q.getQuestionText());
      assertEquals("MultipleChoice", q.getType());
    }

  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateMCQuestionNoText() {
    new MCQuestion("", "", new String[]{"cat", "dog"});
  }


  @Test
  public void testAnswerCorrectly() {
    String[] possibleAnswers = {"cat", "dog", "rabbit", "mouse"};
    for (int i = 1; i <= possibleAnswers.length; i++) {
      Question q = new MCQuestion("Is this a trick question?", "", possibleAnswers);
      assertFalse(q.hasBeenAnswered());

      String answer = String.valueOf(i);
      q.answer(answer);
      assertEquals(answer, q.getEnteredAnswer());
      assertTrue(q.hasBeenAnswered());
    }
  }

  @Test
  public void testAnswerInCorrectly() {
    String[] possibleAnswers = {"cat", "dog", "rabbit", "mouse"};
    //incorrect because outside of correct range of answers 10-14 instead of 1-4
    for (int i = 10; i <= possibleAnswers.length + 10; i++) {
      Question q = new MCQuestion("Is this a trick question?", "", possibleAnswers);
      assertFalse(q.hasBeenAnswered());

      try {
        q.answer(String.valueOf(i));
        fail("MC question accepted an invalid answer");
      } catch (IllegalArgumentException e) {
        assertFalse(q.hasBeenAnswered());
      }
    }
  }

}
