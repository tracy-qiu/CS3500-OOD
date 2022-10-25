package spreadsheet;

import java.io.InputStreamReader;

/**
 * This class is the main program for running the spreadsheet.
 */
public class SpreadSheetProgram {
  /**
   * This is the main method that runs the spreadsheet program.
   * @param args array of string arguments
   */
  public static void main(String []args) {
    SpreadSheet model = new SparseSpreadSheet();
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;
    SpreadSheetController controller = new SpreadSheetController(model,rd,ap);
    controller.goSpreadsheet();
  }
}
