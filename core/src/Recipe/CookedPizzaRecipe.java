package Recipe;

import Ingredients.Cheese;
import Ingredients.PizzaBase;
import Ingredients.*;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/**

 The CookedPizzaRecipe class is a subclass of the Recipe class and represents a cooked pizza in the kitchen game.
 It holds an ArrayList of ingredients needed to make a cooked pizza and a Texture of the completed dish image.
 The ingredients in the ArrayList consist of a {@link Ingredients.RawPizza}.
 */
public class CookedPizzaRecipe extends Recipe{

    public CookedPizzaRecipe(){
        super.ingredients = new ArrayList<>();
        ingredients.add(new RawPizza(0,0,0));
        completedImg = new Texture("Food/cooked_pizza.png");
    }

}
