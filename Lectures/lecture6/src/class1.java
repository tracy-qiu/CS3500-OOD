import java.util.Scanner;

public class class1 {

  try {
    controller.playGame(new CalculatorImp());
    fail();
  } catch(IllegalStateException e){
    assertEquals("Couldn't write to the appendable.", e.getMessage());
  }
}

/**
 * from 3 2 to 3 3 just need them to be numbers
 * int1 = scanner.nextInt()
 * int2 = scanner.nextInt()
 * int3 = scanner.nextInt()
 * int4 = scanner.nextInt()
 * read the four int coordinates
 * scanner takes care of spaces will ignore them
 * instead of ints take them in as strings
 * while loop
 * write helper method - keep reading until it hits an integer
 * appendable object crashing - write one test showing the expected exception
 * need to create many scenarios of how the input string could fail
 *  - invalid move, end before q is sent
 */

public void playGame(ICalculator model) {
  Scanner scanner = new Scanner(myInt);
  while (scanner.hasNext()) {
    String int1 = scanner.next();
    try {
      Integer.parseInt(int1);
    } catch(NumberFormatException e) {
      // read again
      String int1 = scanner.next();
      try {
        Integer.parseInt(int1);
      }
    }
  }
}
