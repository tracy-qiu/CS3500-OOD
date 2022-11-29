package spreadsheet;

/**
 * Interface for Macro.
 */
public interface Macro {

  /**
   * Exectutes updating the spreadsheet.
   * @param speadsheet Spreadsheet object that gets changed.
   */
  public void execute(SpreadSheet speadsheet);

}
