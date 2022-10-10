package pizza.betterpizza;

import betterpizza.AlaCartePizza;
import betterpizza.CheesePizza;
import betterpizza.ObservablePizza;
import betterpizza.VeggiePizza;
import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * Observable pizza test.
 */
public class ObservablePizzaTest {

  ObservablePizza alacarte = new AlaCartePizza.AlaCartePizzaBuilder()
          .crust(Crust.Classic)
          .size(Size.Medium)
          .addTopping(ToppingName.Cheese, ToppingPortion.Full)
          .addTopping(ToppingName.Sauce, ToppingPortion.Full)
          .addTopping(ToppingName.GreenPepper, ToppingPortion.Full)
          .addTopping(ToppingName.Onion, ToppingPortion.Full)
          .addTopping(ToppingName.Jalapeno, ToppingPortion.LeftHalf)
          .build();

  ObservablePizza cheese = new CheesePizza.CheesePizzaBuilder()
          .crust(Crust.Thin)
          .size(Size.Large)
          .build();

  ObservablePizza veggie = new VeggiePizza.VeggiePizzaBuilder()
          .crust(Crust.Stuffed)
          .size(Size.Medium)
          .build();

}