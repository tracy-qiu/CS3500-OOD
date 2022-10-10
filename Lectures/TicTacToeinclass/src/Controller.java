import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.Scanner;

public class Controller {
  private final InputStream in;
  private final OutputStream out;
  public Controller(InputStream in, OutputStream out){
    this.in = Objects.requireNonNull(in);
    this.out = Objects.requireNonNull(out);

  }

  public void run(ICalc model) {
    Scanner scanner = new Scanner(System.in);
    int int1 = scanner.nextInt();
    int int2 = scanner.nextInt();
    String result = "The result is: " + model.add(int1, int2));
//    System.out.println("The result is: " + model.add(int1, int2));
    try {
      this.out.write(result.getBytes());
    } catch (IOException e) {
      throw new IllegalStateException("Couldn't write to output stream.");
    }
  }
}
