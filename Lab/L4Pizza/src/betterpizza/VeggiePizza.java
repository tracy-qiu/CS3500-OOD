package betterpizza;

import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * Veggie pizza class that extends the alacarte pizza class.
 */
public class VeggiePizza extends AlaCartePizza {

  protected VeggiePizza(Size size, Crust crust, Map<ToppingName, ToppingPortion> toppings) throws
          IllegalArgumentException {
    super(size, crust);
    this.toppings = toppings;
  }

  @Override
  public ToppingPortion hasTopping(ToppingName name) {
    return null;
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
  public ObservablePizza build() {
    return new VeggiePizza.VeggiePizzaBuilder().build();
  }

  /**
   * Veggie pizza builder that extends the pizza builder class.
   */
  public static class VeggiePizzaBuilder extends PizzaBuilder<VeggiePizzaBuilder> {

    @Override
    public ObservablePizza build() throws IllegalStateException {
      this.addTopping(ToppingName.Cheese, ToppingPortion.Full);
      this.addTopping(ToppingName.Sauce, ToppingPortion.Full);
      this.addTopping(ToppingName.BlackOlive, ToppingPortion.Full);
      this.addTopping(ToppingName.GreenPepper, ToppingPortion.Full);
      this.addTopping(ToppingName.Onion, ToppingPortion.Full);
      this.addTopping(ToppingName.Jalapeno, ToppingPortion.Full);
      this.addTopping(ToppingName.Tomato, ToppingPortion.Full);
      return new VeggiePizza(size, crust, toppings);
    }

    @Override
    protected PizzaBuilder<VeggiePizzaBuilder> returnBuilder() {
      return this;
    }

    @Override
    public ObservablePizza noCheese() {
      this.addTopping(ToppingName.Sauce, ToppingPortion.Full);
      this.addTopping(ToppingName.BlackOlive, ToppingPortion.Full);
      this.addTopping(ToppingName.GreenPepper, ToppingPortion.Full);
      this.addTopping(ToppingName.Onion, ToppingPortion.Full);
      this.addTopping(ToppingName.Jalapeno, ToppingPortion.Full);
      this.addTopping(ToppingName.Tomato, ToppingPortion.Full);
      return new VeggiePizza(size, crust, toppings);
    }

    @Override
    public ObservablePizza leftHalfCheese() {
      this.addTopping(ToppingName.Sauce, ToppingPortion.Full);
      this.addTopping(ToppingName.Cheese, ToppingPortion.LeftHalf);
      this.addTopping(ToppingName.BlackOlive, ToppingPortion.Full);
      this.addTopping(ToppingName.GreenPepper, ToppingPortion.Full);
      this.addTopping(ToppingName.Onion, ToppingPortion.Full);
      this.addTopping(ToppingName.Jalapeno, ToppingPortion.Full);
      this.addTopping(ToppingName.Tomato, ToppingPortion.Full);
      return new VeggiePizza(size, crust, toppings);
    }

    @Override
    public ObservablePizza rightHalfCheese() {
      this.toppings.put(ToppingName.Sauce, ToppingPortion.Full);
      this.toppings.put(ToppingName.Cheese, ToppingPortion.RightHalf);
      this.toppings.put(ToppingName.BlackOlive, ToppingPortion.Full);
      this.toppings.put(ToppingName.GreenPepper, ToppingPortion.Full);
      this.toppings.put(ToppingName.Onion, ToppingPortion.Full);
      this.toppings.put(ToppingName.Jalapeno, ToppingPortion.Full);
      this.toppings.put(ToppingName.Tomato, ToppingPortion.Full);
      return new VeggiePizza(size, crust, toppings);
    }
  }
}
