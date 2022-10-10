package betterpizza;

import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * Cheese pizza class that extends alacarte pizza.
 */
public class CheesePizza extends AlaCartePizza {

  protected CheesePizza(Size size, Crust crust, Map<ToppingName, ToppingPortion> toppings) throws
          IllegalArgumentException {
    super(size, crust);
    this.toppings = toppings;
  }

  @Override
  public ToppingPortion hasTopping(ToppingName name) {
    return null;
  }

  @Override
  public ObservablePizza build() {
    return new CheesePizza.CheesePizzaBuilder().build();
  }

  /**
   * Cheese pizza builder for building cheese pizzas.
   */
  public static class CheesePizzaBuilder extends PizzaBuilder<CheesePizzaBuilder> {

    @Override
    public ObservablePizza build() throws IllegalStateException {
      this.toppings.put(ToppingName.Sauce, ToppingPortion.Full);
      this.toppings.put(ToppingName.Cheese, ToppingPortion.Full);
      return new CheesePizza(size, crust, toppings);
    }

    @Override
    protected PizzaBuilder<CheesePizzaBuilder> returnBuilder() {
      return this;
    }

    @Override
    public ObservablePizza noCheese() {
      this.toppings.put(ToppingName.Sauce, ToppingPortion.Full);
      return new CheesePizza(size, crust, toppings);
    }

    @Override
    public ObservablePizza leftHalfCheese() {
      this.toppings.put(ToppingName.Sauce, ToppingPortion.Full);
      this.toppings.put(ToppingName.Cheese, ToppingPortion.LeftHalf);
      return new CheesePizza(size, crust, toppings);
    }

    @Override
    public ObservablePizza rightHalfCheese() {
      this.toppings.put(ToppingName.Sauce, ToppingPortion.Full);
      this.toppings.put(ToppingName.Cheese, ToppingPortion.RightHalf);
      return new CheesePizza(size, crust, toppings);
    }

  }
}
