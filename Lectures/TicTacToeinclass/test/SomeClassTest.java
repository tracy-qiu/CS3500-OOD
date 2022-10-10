import org.junit.Test;

import model.SomeClass;

import static org.junit.Assert.*;

/**
 * Created by vidojemihajlovikj on 9/16/22.
 */
public class SomeClassTest {
  @Test
  public void testSomeClass(){
    assertEquals(10, SomeClass.x);
    SomeClass s1 = new SomeClass();
    SomeClass s2 = new SomeClass();
    assertEquals(10, s1.x);
    assertEquals(10, s2.x);

    SomeClass.x = 20;

    assertEquals(20, s1.x);
    assertEquals(20, s2.x);

    s2.x = 30;
    assertEquals(30, SomeClass.x);
    assertEquals(30, s1.x);
    assertEquals(30, s2.x);
  }
}