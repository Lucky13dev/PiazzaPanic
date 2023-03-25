package Recipe;

import Ingredients.Bun;
import Ingredients.*;
import Ingredients.Steak;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class RawPizzaRecipe extends Recipe{

    public RawPizzaRecipe(){
        super.ingredients = new ArrayList<>();
        ingredients.add(new PizzaBase(0,0));
        ingredients.add(new Tomato(0,0));
        ingredients.add(new Cheese(0,0));
        completedImg = new Texture("Food/raw_pizza.png");
    }

}
