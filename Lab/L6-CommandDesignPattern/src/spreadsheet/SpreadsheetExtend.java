package spreadsheet;

/**
 * Spreadsheet that extends Spreadsheet.
 */
public interface SpreadsheetExtend extends SpreadSheet {

  /**
   * Execut accepts an object of a macro and executes it on the spreadsheet.
   * @param macro Macro object
   */
  public void execute(Macro macro);
}
