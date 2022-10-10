import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.Assert.*;

public class ControllerTest {
  @Test
  public void testControllerRun(){
    InputStream input = new ByteArrayInputStream("3,2".getBytes());
    OutputStream output = new ByteArrayOutputStream();

//    Controller controller = new Controller(System.in);
    Controller controller = new Controller(input, output);
    controller.run(new CalcImpl());
    assertEquals("The result is: 6", output.toString());
  }
}
