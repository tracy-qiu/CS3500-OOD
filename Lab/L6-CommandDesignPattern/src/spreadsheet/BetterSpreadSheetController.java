package spreadsheet;

import java.util.Scanner;

/**
 * Better controller that implements bulk-assign-value.
 */
public class BetterSpreadSheetController extends SpreadSheetController {

  /**
   * Create a controller to work with the specified sheet (model),
   * readable (to take inputs) and appendable (to transmit output).
   *
   * @param sheet      the sheet to work with (the model)
   * @param readable   the Readable object for inputs
   * @param appendable the Appendable objects to transmit any output
   */
  public BetterSpreadSheetController(SpreadSheet sheet, Readable readable, Appendable appendable) {
    super(sheet, readable, appendable);
  }

  @Override
  protected void processCommand(String userInstruction, Scanner sc, SpreadSheet sheet) {
    int row;
    int col;
    double value;

    switch (userInstruction) {
      case "assign-value": //assign a value to a cell
        try {
          row = getRowNum(sc.next()); //get in the row string
          col = sc.nextInt(); //get in the column number, starting with 1
          value = sc.nextDouble();
          System.out.println("Setting cell (" + row + "," + (col - 1));
          sheet.set(row, col - 1, value); //use the spreadsheet
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      case "bulk-assign-value":
        try {
          int minRow = getRowNum(sc.next());
          int minCol = sc.nextInt();
          int maxRow = getRowNum(sc.next());
          int maxCol = sc.nextInt();
          value = sc.nextDouble();
          System.out.println(String.format("Setting cells (%d, %d) to (%d, %d)", minRow, minCol,
                  maxRow, maxCol));
          for (int r = minRow; r <= maxRow; r++) {
            for (int c = minCol; c <= maxCol; c++) {
              sheet.set(r, c, value);
            }
          }
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      case "print-value": //print a value from the cell
        try {
          row = getRowNum(sc.next()); //get the row string
          col = sc.nextInt(); //get the column number, starting with 1
          writeMessage("Value: " + sheet.get(row, col - 1) + System.lineSeparator());
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      case "menu": //print the menu of supported instructions
        welcomeMessage();
        break;
      default: //error due to unrecognized instruction
        writeMessage("Undefined instruction: " + userInstruction + System.lineSeparator());
    }
  }

  @Override
  protected void printMenu() throws IllegalStateException {
    writeMessage("Supported user instructions are: " + System.lineSeparator());
    writeMessage("assign-value row-num col-num value (set a cell to a value)"
            + System.lineSeparator());
    writeMessage("bulk-assign-value min-row-num min-col-num max-row-num max-col-num value "
            + "(set a cell to a value)"
            + System.lineSeparator());
    writeMessage("print-value row-num col-num (print the value at a given cell)"
            + System.lineSeparator());
    writeMessage("menu (Print supported instruction list)" + System.lineSeparator());
    writeMessage("q or quit (quit the program) " + System.lineSeparator());
  }
}
