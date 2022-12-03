import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Test taking a file input.
 */
public class ImageProcessorRunnerTest {

  @Test
  public void testGetInput() {
    String[] arguments = new String[2];
    arguments[0] = "-file";
    arguments[1] = "res/scriptTest.txt";
    String expected = "load res/giyu.png art\n" +
            "greyscale art red-vert\n" +
            "save res/newman1.png red-vert";
    Scanner inputString = new Scanner(ImageProcessorRunner.getReadable(arguments));
    StringBuilder actualIn = new StringBuilder();
    actualIn.append(inputString.nextLine());
    actualIn.append("\n");
    actualIn.append(inputString.nextLine());
    actualIn.append("\n");
    actualIn.append(inputString.nextLine());
    assertEquals(expected, actualIn.toString());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFile() {
    String[] arguments = new String[2];
    arguments[0] = "-file";
    arguments[1] = "oogabooga.txt";
    ImageProcessorRunner.getReadable(arguments);
  }
}