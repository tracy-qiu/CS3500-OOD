import java.util.Objects;

/**
 * Simple fraction class that implements fraction.
 */
public class SimpleFraction implements Fraction {
  int numerator;
  int denominator;

  /**
   * constructor for a simple fraction.
   * @param numerator non negative numerator of fraction
   * @param denominator non negative or non zero denominator of fraction
   */
  public SimpleFraction(int numerator, int denominator) {
    if (numerator < 0 || denominator < 0) {
      if (!(numerator < 0 && denominator < 0)) {
        throw new IllegalArgumentException("must be non-negative fraction");
      }
    }
    if (denominator == 0) {
      throw new IllegalArgumentException("denominator must be non-zero");
    }

    this.numerator = numerator;
    this.denominator = denominator;
  }

  @Override
  public Fraction add(Fraction other) {
    return other.add(this.numerator, this.denominator);
  }

  @Override
  public Fraction add(int numerator, int denominator) {
    if (numerator < 0 || denominator < 0) {
      if (!(numerator < 0 && denominator < 0)) {
        throw new IllegalArgumentException("must be non-negative fraction");
      }
    }
    if (denominator == 0) {
      throw new IllegalArgumentException("denominator must be non-zero");
    }
    return new SimpleFraction((this.numerator * denominator + numerator * this.denominator),
            this.denominator * denominator);
  }

  @Override
  public double getDecimalValue(int places) {
    double rounder = Math.pow(10, places);
    return Math.round(this.numerator * rounder / this.denominator) / rounder;
  }

  @Override
  public String asNd() {
    if (this.numerator < 0 && this.denominator < 0) {
      return String.format("%d/%d", -1 * this.numerator, -1 * this.denominator);
    }
    else {
      return String.format("%d/%d", this.numerator, this.denominator);
    }
  }

  @Override
  public String toString() {
    return asNd();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SimpleFraction)) {
      return false;
    }
    SimpleFraction sf = (SimpleFraction) o;
    return (this.numerator == sf.numerator && this.denominator == sf.denominator);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.numerator, this.denominator);
  }
}













