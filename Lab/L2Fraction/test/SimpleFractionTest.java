import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Simple fraction test to test all the methods of the class.
 */
public class SimpleFractionTest {

  static Fraction f1 = ndFraction(3, 4);
  static Fraction f2 = ndFraction(1, 8);
  static Fraction f3 = ndFraction(4,9);
  static Fraction f4 = ndFraction(-5,-7);

  static Fraction f5 = ndFraction(1, 1);

  static Fraction f6 = ndFraction(1, 1);

  @Test(expected = IllegalArgumentException.class)
  public void fractionConstructorDisablesNegativeNumerator() {
    new SimpleFraction(-5, 11);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fractionConstructorDisablesNegativeDenominator() {
    new SimpleFraction(5, -11);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fractionConstructorDisablesZeroDenominator() {
    new SimpleFraction(5, 0);
  }

  @Test
  public void add() {
    assertEquals(ndFraction(28,32), f1.add(f2));
    assertEquals(ndFraction(13,9), f3.add(f5));
    assertEquals(ndFraction(2,1), f5.add(f6));
  }

  @Test
  public void testAdd() {
    assertEquals(ndFraction(28,32), f1.add(1,8));
    assertEquals(ndFraction(13,9), f3.add(1,1));
    assertEquals(ndFraction(2,1), f5.add(1,1));
  }

  @Test
  public void getDecimalValue() {
    assertEquals(0.75, f1.getDecimalValue(5), 0.001);
    assertEquals(0.13, f2.getDecimalValue(2), 0.001);
    assertEquals(0.125, f2.getDecimalValue(3), 0.001);
    assertEquals(1.0, f6.getDecimalValue(2), 0.001);
  }

  @Test
  public void testAsNd() {
    assertEquals("3/4", f1.asNd());
    assertEquals("1/8", f2.asNd());
    assertEquals("4/9", f3.asNd());
    assertEquals("5/7", f4.asNd());
  }

  static Fraction ndFraction(int numerator, int denominator) {
    return new SimpleFraction(numerator, denominator);
  }
}