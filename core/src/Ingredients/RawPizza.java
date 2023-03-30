package Ingredients;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class RawPizza extends Ingredient{
    /**
     * Constructs a new Ingredient object with the specified preparation and cooking times.
     *
     * @param prepareTime The time required to prepare the ingredient.
     * @param cookTime    The time required to cook the ingredient.
     */
    public RawPizza(float prepareTime, float cookTime) {
        super(prepareTime, cookTime);
        super.setPrepared();
        super.tex = new ArrayList<>();
        super.tex.add(new Texture("Food/raw_pizza.png"));
    }
}
