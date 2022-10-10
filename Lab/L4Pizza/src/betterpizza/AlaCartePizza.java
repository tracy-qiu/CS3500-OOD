package betterpizza;

import java.util.HashMap;
import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * This class represents an ala carte pizza (i.e. a pizza that can
 * have an arbitrary number of ingredients.
 */
public class AlaCartePizza implements ObservablePizza {
  protected Crust crust;
  protected Size size;
  protected Map<ToppingName, ToppingPortion> toppings;

  /**
   * Create a pizza given its crust type, size and toppings.
   */
  public AlaCartePizza(Size size, Crust crust) {
    this.crust = crust;
    this.size = size;
    this.toppings = new HashMap<ToppingName, ToppingPortion>();
  }

  protected AlaCartePizza(Size size, Crust crust, Map<ToppingName, ToppingPortion> toppings) throws
          IllegalArgumentException {
    if (crust == null) {
      throw new IllegalArgumentException(String.format("Pizza size not specified"));
    }
    this.crust = crust;
    if (size == null) {
      throw new IllegalArgumentException(String.format("Pizza crust type not specified"));
    }
    this.size = size;
    this.toppings = toppings;
  }

  @Override
  public void setCrust(Crust crust) {
    this.crust = crust;
  }

  @Override
  public void setSize(Size size) {
    this.size = size;
  }

  @Override
  public void addTopping(ToppingName name, ToppingPortion portion) {
    this.toppings.put(name, portion);
  }

  @Override
  public void removeTopping(ToppingName name) {
    this.toppings.remove(name);
  }

  @Override
  public ToppingPortion hasTopping(ToppingName name) {
    return this.toppings.getOrDefault(name, null);
  }

  @Override
  public double cost() {
    double cost = 0.0;
    for (Map.Entry<ToppingName, ToppingPortion> item : this.toppings.entrySet()) {
      cost += item.getKey().getCost() * item.getValue().getCostMultiplier();
    }
    return cost + this.size.getBaseCost();
  }

  public ObservablePizza build() {
    return new AlaCartePizza.AlaCartePizzaBuilder().build();
  }

  public ObservablePizza noSauce() {
    return this.noSauce().build();
  }

  public ObservablePizza noTomato() {
    return this.noTomato().build();
  }

  public ObservablePizza noJalapeno() {
    return this.noJalapeno().build();
  }

  /**
   * Alacarte pizza builder to build alacarte pizzas.
   */
  static public class AlaCartePizzaBuilder extends PizzaBuilder<AlaCartePizzaBuilder> {
    @Override
    public ObservablePizza build() throws IllegalStateException {
      if (size == null || crust == null ) {
        throw new IllegalStateException(String.format("Pizza size or crust type not specified"));
      }
      return new AlaCartePizza(size, crust, toppings);
    }

    @Override
    protected PizzaBuilder<AlaCartePizzaBuilder> returnBuilder() {
      return this;
    }

    @Override
    public ObservablePizza noCheese() {
      this.toppings.put(ToppingName.Sauce, ToppingPortion.Full);
      return new CheesePizza(size, crust, toppings);
    }

    @Override
    public ObservablePizza leftHalfCheese() {
      this.addTopping(ToppingName.Sauce, ToppingPortion.Full);
      this.addTopping(ToppingName.Cheese, ToppingPortion.LeftHalf);
      return new CheesePizza(size, crust, toppings);
    }

    @Override
    public ObservablePizza rightHalfCheese() {
      this.addTopping(ToppingName.Sauce, ToppingPortion.Full);
      this.addTopping(ToppingName.Cheese, ToppingPortion.RightHalf);
      return new CheesePizza(size, crust, toppings);
    }
  }
}
