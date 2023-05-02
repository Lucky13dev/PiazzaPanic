package Recipe;

import Ingredients.Bun;
import Ingredients.Cheese;
import Ingredients.CookedPotato;
import Ingredients.Steak;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/**

 The JacketPotatoRecipe class is a subclass of the Recipe class and represents a jacket potato in the kitchen game.
 It holds an ArrayList of ingredients needed to make a jacket potato and a Texture of the completed dish image.
 The ingredients in the ArrayList consist of a {@link Ingredients.CookedPotato} and a {@link Ingredients.Cheese}.
 */
public class JacketPotatoRecipe extends Recipe{

    public JacketPotatoRecipe() {
        super.ingredients = new ArrayList<>();
        ingredients.add(new CookedPotato(0, 0,0));
        ingredients.add(new Cheese(0, 0,0));
        completedImg = new Texture("Food/baked_potato.png");
    }
}
