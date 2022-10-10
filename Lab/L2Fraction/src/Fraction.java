/**
 * Fraction interface with simple methods.
 */
public interface Fraction {
  /**
   * adds two fractions.
   * @param other other fraction being added to this fraction.
   * @return returns a fraction
   */
  Fraction add(Fraction other);

  /**
   * adds this fraction and the given fraction.
   * @param numerator numerator of other fraction being added to this fraction
   * @param denominator denominator of other fraction being added to this fraction
   * @return returns a fraction
   */
  Fraction add(int numerator, int denominator);

  /**
   * gets the decimal value of the fraction.
   * @param places number of place values the decimal rounds to
   * @return return a decimal value of the fraction rounded to the specified number of places
   */
  double getDecimalValue(int places);

  /**
   * creates string of this fraction.
   * @return returns a string of the fraction in "%d/%d format
   */
  String asNd();
}
