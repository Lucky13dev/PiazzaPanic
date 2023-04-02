package Ingredients;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class CookedPotato extends Ingredient{


    /**
     * Constructs a new Ingredient object with the specified preparation and cooking times.
     *
     * @param prepareTime The time required to prepare the ingredient.
     * @param cookTime    The time required to cook the ingredient.
     */
    public CookedPotato(float prepareTime, float cookTime) {
        super(prepareTime, cookTime);
        super.tex = new ArrayList<>();
        super.tex.add(new Texture("Food/cooked_potato.png"));
        super.tex.add(new Texture("Food/cooked_potato.png"));
        super.tex.add(new Texture("Food/cooked_potato.png"));
    }
}
