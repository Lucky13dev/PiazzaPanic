package Recipe;

import Ingredients.Bun;
import Ingredients.*;
import Ingredients.Steak;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/**

 The RawPizzaRecipe class is a subclass of the Recipe class and represents a raw pizza in the kitchen game.
 It holds an ArrayList of ingredients needed to make a raw pizza and a Texture of the completed dish image.
 The ingredients in the ArrayList consist of a {@link Ingredients.PizzaBase} and a {@link Ingredients.Cheese}
 and a {@link Ingredients.Tomato}.
 */
public class RawPizzaRecipe extends Recipe{

    public RawPizzaRecipe(){
        super.ingredients = new ArrayList<>();
        ingredients.add(new PizzaBase(0,0,0));
        ingredients.add(new Tomato(0,0,0));
        ingredients.add(new Cheese(0,0,0));
        completedImg = new Texture("Food/raw_pizza.png");
    }

}
