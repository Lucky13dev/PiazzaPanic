package Recipe;

import Ingredients.Cheese;
import Ingredients.PizzaBase;
import Ingredients.*;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class CookedPizzaRecipe extends Recipe{

    public CookedPizzaRecipe(){
        super.ingredients = new ArrayList<>();
        ingredients.add(new RawPizza(0,2));
        completedImg = new Texture("Food/cooked_pizza.png");
    }

}
