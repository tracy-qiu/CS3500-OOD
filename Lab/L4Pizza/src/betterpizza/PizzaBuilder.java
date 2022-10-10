package betterpizza;

import java.util.HashMap;
import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * Pizza builder.
 *
 * @param <T> pizza builder type
 */
public abstract class PizzaBuilder<T> {

  protected Crust crust;
  protected Size size;

  protected Map<ToppingName, ToppingPortion> toppings;

  /**
   * Pizza builder and all the methods to build a pizza.
   */
  public PizzaBuilder() {
    this.crust = null;
    this.size = null;
    this.toppings = new HashMap<ToppingName, ToppingPortion>();
  }

  public PizzaBuilder crust(Crust crust) {
    this.crust = crust;
    return returnBuilder();
  }

  public PizzaBuilder size(Size size) {
    this.size = size;
    return returnBuilder();
  }

  public PizzaBuilder addTopping(ToppingName toppingName, ToppingPortion toppingPortion) {
    this.toppings.put(toppingName, toppingPortion);
    return returnBuilder();
  }

  public PizzaBuilder removeTopping(ToppingName name) {
    this.toppings.remove(name);
    return returnBuilder();
  }

  public ToppingPortion hasTopping(ToppingName name) {
    return this.toppings.getOrDefault(name, null);
  }

  public abstract ObservablePizza build() throws IllegalStateException;

  protected abstract PizzaBuilder<T> returnBuilder();

  public abstract ObservablePizza noCheese();

  public abstract ObservablePizza leftHalfCheese();

  public abstract ObservablePizza rightHalfCheese();

  /**
   * Create pizza builder for pizza with no jalapeno.
   * @return pizza builder
   */
  public PizzaBuilder<T> noJalapeno() {
    if (this.hasTopping(ToppingName.Jalapeno) != null) {
      removeTopping(ToppingName.Jalapeno);
    }
    return returnBuilder();
  }

  /**
   * Create pizza builder for pizza with no tomato.
   * @return pizza builder
   */
  public PizzaBuilder<T> noTomato() {
    if (this.hasTopping(ToppingName.Tomato) != null) {
      removeTopping(ToppingName.Tomato);
    }
    return returnBuilder();
  }

  /**
   * Create pizza builder for pizza with no sauce.
   * @return pizza builder
   */
  public PizzaBuilder<T> noSauce() {
    if (this.hasTopping(ToppingName.Sauce) != null) {
      removeTopping(ToppingName.Sauce);
    }
    return returnBuilder();
  }

}
