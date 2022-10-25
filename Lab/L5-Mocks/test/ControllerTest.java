import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import spreadsheet.MockSpreadSheet;
import spreadsheet.SparseSpreadSheet;
import spreadsheet.SpreadSheet;
import spreadsheet.SpreadSheetController;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the spreadsheet controller. It tests the message transmission.
 */
public class ControllerTest {

  @Test
  public void testWelcomeTransmitMessage() throws IOException {
    SpreadSheet model = new SparseSpreadSheet();
    Readable rd = new StringReader("q");
    Appendable ap = new StringBuilder();
    SpreadSheetController controller = new SpreadSheetController(model, rd, ap);
    controller.goSpreadsheet();
    String expected = "Welcome to the spreadsheet program!\n"
            + "Supported user instructions are: \n"
            + "assign-value row-num col-num value (set a cell to a value)\n"
            + "print-value row-num col-num (print the value at a given cell)\n"
            + "menu (Print supported instruction list)\n"
            + "q or quit (quit the program) \n";
    String[] message = ap.toString().split("Type", 2);

    assertEquals(expected, message[0]);
  }

  @Test
  public void testGoodbyeTransmitMessage() throws IOException {
    SpreadSheet model = new SparseSpreadSheet();
    Readable rd = new StringReader("q");
    Appendable ap = new StringBuilder();
    SpreadSheetController controller = new SpreadSheetController(model, rd, ap);
    controller.goSpreadsheet();
    String expected = "\nType instruction: Thank you for using this program!";
    String[] message = ap.toString().split("quit the program\\) ", 2);

    assertEquals(expected, message[1]);
  }

  @Test
  public void testSetSpreadSheet() throws IOException {
    SpreadSheet model = new SparseSpreadSheet();
    Readable rd = new StringReader("assign-value A 1 1 q");
    Appendable ap = new StringBuilder();
    SpreadSheet mockModel = new MockSpreadSheet(model, ap);
    SpreadSheetController controller = new SpreadSheetController(mockModel, rd, ap);
    controller.goSpreadsheet();
    String expected = "\nType instruction: Thank you for using this program!";

    assertEquals(expected, ap);
  }
}



















