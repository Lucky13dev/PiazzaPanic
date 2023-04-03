package Recipe;

import Ingredients.Bun;
import Ingredients.Cheese;
import Ingredients.CookedPotato;
import Ingredients.Steak;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class JacketPotatoRecipe extends Recipe{

    public JacketPotatoRecipe() {
        super.ingredients = new ArrayList<>();
        ingredients.add(new CookedPotato(0, 0));
        ingredients.add(new Cheese(0, 0));
        completedImg = new Texture("Food/baked_potato.png");
    }
}
