import org.junit.Test;

import spreadsheet.Macro;
import spreadsheet.MacroImpl;
import spreadsheet.SparseSpreadSheet;
import spreadsheet.SpreadSheet;

import static org.junit.Assert.assertEquals;

/**
 * Test the Macro class.
 */
public class MacroTest {
  @Test
  public void testExecute() {
    Macro macro = new MacroImpl(0,0,3,4,1);
    SpreadSheet spreadsheet = new SparseSpreadSheet();
    macro.execute(spreadsheet);
    assertEquals(1, (int)spreadsheet.get(0,0));
    assertEquals(1, (int)spreadsheet.get(1,3));
    assertEquals(1, (int)spreadsheet.get(2,1));
    assertEquals(1, (int)spreadsheet.get(3,0));
  }
}
